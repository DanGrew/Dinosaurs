package uk.dangrew.dinosaurs.game.mechanism;

/**
 * Interface for providing actions the player can make per turn.
 */
public interface GameAction {
   
   public String describe();
   
   public void performAction();
   
}