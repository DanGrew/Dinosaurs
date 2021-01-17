
package uk.dangrew.dinosaurs.game.mechanism;

import static java.util.Collections.singleton;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.dangrew.dinosaurs.game.actions.GameAction;
import uk.dangrew.dinosaurs.game.actions.MoveAction;
import uk.dangrew.dinosaurs.game.actions.Movement;
import uk.dangrew.dinosaurs.game.actions.UnnavailableAction;
import uk.dangrew.dinosaurs.game.collision.Collision;
import uk.dangrew.dinosaurs.game.collision.CollisionDetection;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;

@ExtendWith(MockitoExtension.class)
public class ActionProcessorTest {
   
   private Dinosaur player;
   private World world;
   
   private GameState gameState;
   @Mock private CollisionDetection collisionDetection;
   private ActionProcessor systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      world = new World("World");
      world.setDimension(10, 10);
      player = new Dinosaur("Dino!");
      player.getWorldLocation().set(new WorldLocation(5, 5, world));

      gameState = new GameState();
      gameState.currentWorld().set(world);
      gameState.currentPlayer().set(player);
      
      systemUnderTest = new ActionProcessor(gameState, collisionDetection);
   }
   
   @Test
   public void shouldProvideMovementForEachDirection() {
      List<GameAction> gameActions = new ArrayList<>(systemUnderTest.calculateAvailableActions());
      for (int i = 0; i < gameActions.size(); i++) {
         MoveAction moveAction = (MoveAction) gameActions.get(i);
         assertThat(moveAction.movement(), equalTo(Movement.values()[i]));
      }
   }
   
   @ParameterizedTest
   @EnumSource(value = Movement.class)
   public void shouldProvideUnavailableMovementWhenColliding(Movement movement) {
      lenient().when(collisionDetection.calculateCollisions(player, movement.move(player.expectLocation(), world)))
            .thenReturn(singleton(new Collision("")));
      
      UnnavailableAction unavailable = systemUnderTest.calculateAvailableActions().stream()
            .filter(gameAction -> !gameAction.isAvailable())
            .map(UnnavailableAction.class::cast)
            .findAny().get();
      MoveAction moveAction = (MoveAction) unavailable.action();
      assertThat(moveAction.movement(), equalTo(movement));
   }
}
