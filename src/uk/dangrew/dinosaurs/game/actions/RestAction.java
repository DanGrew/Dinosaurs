package uk.dangrew.dinosaurs.game.actions;

public class RestAction implements GameAction {
   @Override
   public boolean isAvailable() {
      return true;
   }

   @Override
   public String describe() {
      return "Linger... Ponder... Have a rest...";
   }

   @Override
   public void performAction() {
      //do nothing
   }
}