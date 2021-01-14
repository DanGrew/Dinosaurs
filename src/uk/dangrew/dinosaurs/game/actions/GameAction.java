package uk.dangrew.dinosaurs.game.actions;

/**
 * Interface for providing actions the player can make per turn.
 */
public interface GameAction {
   
   public String describe();
   
   public void performAction();
   
}