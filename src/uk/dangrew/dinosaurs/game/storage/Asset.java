package uk.dangrew.dinosaurs.game.storage;

import uk.dangrew.dinosaurs.game.actions.mechanism.ActionGenerator;
import uk.dangrew.dinosaurs.game.collision.CollisionDetector;
import uk.dangrew.kode.concept.Concept;

/**
 * Represents an asset in the game, defining common properties and shared behaviour.
 */
public interface Asset extends Concept {
   
   public CollisionDetector getCollisionDetector();
   
   public ActionGenerator getActionGenerator();
}
