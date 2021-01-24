package uk.dangrew.dinosaurs.game.actions.movement;

import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class RestAction implements GameAction {
   
   private final WorldLocation location;
   
   public RestAction(WorldLocation location){
      this.location = location;
   }
   
   @Override
   public boolean isAvailable() {
      return true;
   }

   @Override
   public String describe() {
      return location.wrapedCoordinates() + ": Linger... Ponder... Have a rest...";
   }

   @Override
   public void performAction() {
      //do nothing
   }
}