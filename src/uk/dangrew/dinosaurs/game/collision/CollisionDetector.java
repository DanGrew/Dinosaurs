package uk.dangrew.dinosaurs.game.collision;

import java.util.Optional;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Interface for collision detection logic associated with a particular asset.
 */
public interface CollisionDetector {

   public Optional<Collision> canLocationBeOccupied(Dinosaur dinosaur, WorldLocation newLocation);
}
