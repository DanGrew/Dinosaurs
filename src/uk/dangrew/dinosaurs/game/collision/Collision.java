package uk.dangrew.dinosaurs.game.collision;

/**
 * Simple implementation of a collision in the game, preventing movement or action.
 */
public class Collision {
   
   private final String description;
   
   public Collision(String description){
      this.description = description;
   }

   public String getDescription() {
      return "Collision: " + description;
   }
}