
package uk.dangrew.dinosaurs.game.mechanism;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import uk.dangrew.dinosaurs.game.actions.GameAction;
import uk.dangrew.dinosaurs.game.actions.MoveAction;
import uk.dangrew.dinosaurs.game.actions.Movement;
import uk.dangrew.dinosaurs.game.actions.RestAction;
import uk.dangrew.dinosaurs.game.actions.UnnavailableAction;
import uk.dangrew.dinosaurs.game.collision.Collision;
import uk.dangrew.dinosaurs.game.collision.CollisionDetection;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;

/**
 * Calculates all possible actions available to the player per turn.
 */
public class ActionProcessor {
   
   private final GameState gameState;
   private final CollisionDetection collisionDetection;
   
   public ActionProcessor(GameState gameState, AssetManager assetManager) {
      this(gameState, new CollisionDetection(assetManager));
   }
   
   ActionProcessor(GameState gameState, CollisionDetection collisionDetection) {
      this.gameState = gameState;
      this.collisionDetection = collisionDetection;
   }
   
   public Collection<GameAction> calculateAvailableActions() {
      Set<GameAction> possibleActions = new LinkedHashSet<>();
      
      possibleActions.add(new RestAction());
      for (Movement movement : Movement.values()) {
         GameAction moveAction = calculateActionForMovement(gameState.expectPlayer(), movement);
         possibleActions.add(moveAction);
      }
      
      return possibleActions;
   }
   
   private GameAction calculateActionForMovement(Dinosaur dinosaur, Movement movement) {
      WorldLocation newLocation = movement.move(dinosaur.expectLocation(), gameState.expectWorld());
      Collection<Collision> collisions = collisionDetection.calculateCollisions(dinosaur, newLocation);
      MoveAction moveAction = new MoveAction(gameState.expectWorld(), dinosaur, movement);
      if (collisions.isEmpty()) {
         return moveAction;
      }
      
      String collisionDescription = collisions.stream()
            .map(Collision::getDescription)
            .collect(Collectors.joining("\n"));
      return new UnnavailableAction(moveAction, collisionDescription);
   }
}
