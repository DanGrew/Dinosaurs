
package uk.dangrew.dinosaurs.game.model.greenery;

import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class Tree {
   
   private final WorldLocation worldLocation;
   
   public Tree(WorldLocation worldLocation){
      this.worldLocation = worldLocation;
   }

   public WorldLocation getWorldLocation() {
      return worldLocation;
   }
}