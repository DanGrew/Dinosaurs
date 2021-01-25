
package uk.dangrew.dinosaurs.game.actions.movement;

import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Implementation of a game action for movement.
 */
public class MoveAction implements GameAction {
   
   private final World world;
   private final Dinosaur dinosaur;
   private final Movement movement;
   private final WorldLocation locationToMoveTo;
   
   public MoveAction(World world, Dinosaur dinosaur, Movement movement) {
      this.world = world;
      this.dinosaur = dinosaur;
      this.movement = movement;
      this.locationToMoveTo = movement.move(dinosaur.getWorldLocation().get(), world);
   }
   
   @Override
   public boolean isAvailable() {
      return true;
   }
   
   @Override
   public String describe() {
      return describe(locationToMoveTo.wrapedCoordinates(), " Move " + movement.displayName());
   }
   
   public Movement movement() {
      return movement;
   }
   
   @Override
   public void performAction() {
      dinosaur.getWorldLocation().set(locationToMoveTo);
   }
}
