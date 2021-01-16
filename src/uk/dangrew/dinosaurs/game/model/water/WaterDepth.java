
package uk.dangrew.dinosaurs.game.model.water;

import java.util.Objects;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;

/**
 * Behaviour associated with the depth of water.
 */
public class WaterDepth {
   
   private final int depth;
   
   public WaterDepth(int depth){
      this.depth = depth;
   }

   public int getDepth() {
      return depth;
   }

   public boolean dinosaurCanSurvive(Dinosaur dinosaur) {
      return dinosaur.getHeight() > depth;
   }

   @Override
   public boolean equals(Object o) {
      if(this == o) {
         return true;
      }
      if(o == null || getClass() != o.getClass()) {
         return false;
      }
      WaterDepth that = (WaterDepth) o;
      return depth == that.depth;
   }

   @Override
   public int hashCode() {
      return Objects.hash(depth);
   }
}