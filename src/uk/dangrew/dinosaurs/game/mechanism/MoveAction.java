package uk.dangrew.dinosaurs.game.mechanism;

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
   
   public MoveAction(World world, Dinosaur dinosaur, Movement movement) {
      this.world = world;
      this.dinosaur = dinosaur;
      this.movement = movement;
   }

   @Override
   public String describe() {
      return String.format("Move: %s.", movement.name());
   }

   @Override
   public void performAction() {
      WorldLocation movementAfterMove = world.locationForMovement(dinosaur.getWorldLocation().get(), movement);
      dinosaur.getWorldLocation().set(movementAfterMove);
   }
}