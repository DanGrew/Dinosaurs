
package uk.dangrew.dinosaurs.game.mechanism;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.dangrew.dinosaurs.game.actions.mechanism.ActionGenerator;
import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.actions.mechanism.UnnavailableAction;
import uk.dangrew.dinosaurs.game.actions.movement.MoveAction;
import uk.dangrew.dinosaurs.game.actions.movement.Movement;
import uk.dangrew.dinosaurs.game.actions.movement.RestAction;
import uk.dangrew.dinosaurs.game.collision.Collision;
import uk.dangrew.dinosaurs.game.collision.CollisionDetection;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.model.rubble.Rock;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;

@ExtendWith(MockitoExtension.class)
public class ActionProcessorTest {
   
   private Dinosaur player;
   private World world;
   
   private GameState gameState;
   private AssetManager assetManager;
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
      
      assetManager = new AssetManager();
      systemUnderTest = new ActionProcessor(gameState, assetManager, collisionDetection);
   }
   
   @Test
   public void shouldProvideMovementForEachDirection() {
      List<GameAction> gameActions = new ArrayList<>(systemUnderTest.calculateAvailableActions());
      GameAction firstAction = gameActions.get(0);
      assertThat(firstAction, instanceOf(RestAction.class));
      for (int i = 0; i < Movement.values().length; i++) {
         MoveAction moveAction = (MoveAction) gameActions.get(i + 1);
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
   
   @Nested
   public class ActionGeneratorTests {
      
      @Mock private GameAction gameAction1;
      @Mock private GameAction gameAction2;
      @Mock private ActionGenerator actionGenerator;
      
      @BeforeEach
      public void initialiseTestData() {
         when(actionGenerator.generateActions(player)).thenReturn(asList(gameAction1, gameAction2));
      }
      
      @Test
      public void shouldGenerateActionsFromWaterStores() {
         Water water = spy(new Water("Water"));
         assetManager.getWaterStore().store(water);
         when(water.getActionGenerator()).thenReturn(actionGenerator);
         
         Collection<GameAction> result = systemUnderTest.calculateAvailableActions();
         assertThat(result.contains(gameAction1), equalTo(true));
         assertThat(result.contains(gameAction2), equalTo(true));
      }
      
      @Test
      public void shouldGenerateActionsFromRockStores() {
         Rock rock = spy(new Rock("Rock"));
         assetManager.getRockStore().store(rock);
         
         when(rock.getActionGenerator()).thenReturn(actionGenerator);
         
         Collection<GameAction> result = systemUnderTest.calculateAvailableActions();
         assertThat(result.contains(gameAction1), equalTo(true));
         assertThat(result.contains(gameAction2), equalTo(true));
      }
      
      @Test
      public void shouldGenerateActionsFromTreeStores() {
         Tree tree = spy(new Tree("Tree"));
         assetManager.getTreeStore().store(tree);
         
         when(tree.getActionGenerator()).thenReturn(actionGenerator);
         
         Collection<GameAction> result = systemUnderTest.calculateAvailableActions();
         assertThat(result.contains(gameAction1), equalTo(true));
         assertThat(result.contains(gameAction2), equalTo(true));
      }
   }
}
