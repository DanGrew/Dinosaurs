
package uk.dangrew.dinosaurs.game.collision;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.storage.Asset;
import uk.dangrew.dinosaurs.game.storage.AssetStore;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Object responsible for detecting and reporting collisions detected as a result of movement.
 */
public class CollisionDetection {
   
   private final Collection<AssetStore<?, ?>> assetStoresProvidingCollidables;
   
   public CollisionDetection(Collection<AssetStore<? extends Asset, ?>> assetStoresProvidingCollidables) {
      this.assetStoresProvidingCollidables = assetStoresProvidingCollidables;
   }
   
   public Collection<Collision> calculateCollisions(Dinosaur dinosaur, WorldLocation newLocation) {
      return assetStoresProvidingCollidables.stream()
            .map(AssetStore::objectList)
            .flatMap(List::stream)
            .map(Asset::getCollisionDetector)
            .map(collisionDetector -> collisionDetector.canLocationBeOccupied(dinosaur, newLocation))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
   }
}
