
package uk.dangrew.dinosaurs.game.actions.mechanism;

public class UnnavailableAction implements GameAction {
   
   private final GameAction actionThatIsNotAvailable;
   private final String cause;
   
   public UnnavailableAction(GameAction actionThatIsNotAvailable, String cause) {
      this.actionThatIsNotAvailable = actionThatIsNotAvailable;
      this.cause = cause;
   }

   @Override
   public boolean isAvailable() {
      return false;
   }

   @Override
   public String describe() {
      return String.format("%s\n%s", actionThatIsNotAvailable.describe(), cause);
   }
   
   public GameAction action(){
      return actionThatIsNotAvailable;
   }
   
   @Override
   public void performAction() {
      throw new IllegalStateException("Should not be accessed.");
   }
}
