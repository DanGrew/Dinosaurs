package uk.dangrew.dinosaurs.game.actions.mechanism;

/**
 * Interface for providing actions the player can make per turn.
 */
public interface GameAction {
   
   public boolean isAvailable();
   
   public String describe();
   
   public default String describe(String location, String description){
      return String.format("%s:\n%s", location, description);
   }
   
   public void performAction();
   
}