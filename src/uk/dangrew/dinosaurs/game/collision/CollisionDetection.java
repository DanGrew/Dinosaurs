
package uk.dangrew.dinosaurs.game.collision;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import uk.dangrew.dinosaurs.game.mechanism.AssetManager;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Object responsible for detecting and reporting collisions detected as a result of movement.
 */
public class CollisionDetection {
   
   private final AssetManager assetManager;
   
   public CollisionDetection(AssetManager assetManager) {
      this.assetManager = assetManager;
   }
   
   public Collection<Collision> calculateCollisions(Dinosaur dinosaur, WorldLocation newLocation) {
      return assetManager.getWaterStore().objectList().stream()
            .map(Water::getCollisionDetector)
            .map(collisionDetector -> collisionDetector.canLocationBeOccupied(dinosaur, newLocation))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
   }
}
