
package uk.dangrew.dinosaurs.game.mechanism;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.actions.mechanism.UnnavailableAction;
import uk.dangrew.dinosaurs.game.actions.movement.MoveAction;
import uk.dangrew.dinosaurs.game.actions.movement.Movement;
import uk.dangrew.dinosaurs.game.actions.movement.RestAction;
import uk.dangrew.dinosaurs.game.collision.Collision;
import uk.dangrew.dinosaurs.game.collision.CollisionDetection;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.storage.Asset;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.kode.concept.ConceptStore;

/**
 * Calculates all possible actions available to the player per turn.
 */
public class ActionProcessor {
   
   private final GameState gameState;
   private final AssetManager assetManager;
   private final CollisionDetection collisionDetection;
   
   public ActionProcessor(GameState gameState, AssetManager assetManager) {
      this(gameState, assetManager, assetManager.getCollisionDetection());
   }
   
   ActionProcessor(GameState gameState, AssetManager assetManager, CollisionDetection collisionDetection) {
      this.gameState = gameState;
      this.assetManager = assetManager;
      this.collisionDetection = collisionDetection;
   }
   
   public Collection<GameAction> calculateAvailableActions() {
      Dinosaur player = gameState.expectPlayer();
      Set<GameAction> possibleActions = new LinkedHashSet<>();

      possibleActions.add(new RestAction(player.expectLocation()));
      for (Movement movement : Movement.values()) {
         GameAction moveAction = calculateActionForMovement(player, movement);
         possibleActions.add(moveAction);
      }
      
      assetManager.getCollidableAssetStores().stream()
            .map(ConceptStore::objectList)
            .flatMap(List::stream)
            .map(Asset::getActionGenerator)
            .map(generator -> generator.generateActions(player))
            .flatMap(Collection::stream)
            .forEach(possibleActions::add);
      
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
