
package uk.dangrew.dinosaurs.game.collision;

import java.util.Optional;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class NoCollisions implements CollisionDetector {
   
   @Override
   public Optional<Collision> canLocationBeOccupied(
         Dinosaur dinosaur, WorldLocation newLocation) {
      return Optional.empty();
   }
}
