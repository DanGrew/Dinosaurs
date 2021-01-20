
package uk.dangrew.dinosaurs.game.algorithm.shortestpath;

import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Node used by the A* algorithm.
 */
public class AyeStahNowd {
   
   private final WorldLocation location;
   
   private AyeStahNowd parent;
   
   private int distanceBetweenStartAndCurrent;
   private int estimateDistanceFromCurrentToEnd;
   
   public AyeStahNowd(WorldLocation location) {
      this.location = location;
   }
   
   public WorldLocation location() {
      return location;
   }
   
   public void changeParent(AyeStahNowd newParent) {
      this.parent = newParent;
   }

   public int distanceBetweenStartAndCurrent() {
      return distanceBetweenStartAndCurrent;
   }

   public int estimateDistanceFromCurrentToEnd() {
      return estimateDistanceFromCurrentToEnd;
   }

   public void calculate(WorldLocation destination, World world) {
      if (parent == null) {
         distanceBetweenStartAndCurrent = 1;
      } else {
         distanceBetweenStartAndCurrent = parent.distanceBetweenStartAndCurrent + 1;
      }
      
      int horizontalDifference = calculateMinimumDistanceToTraverse(
            location.getHorizontal(), destination.getHorizontal(), world.getHorizontalCellCount());
      int verticalDifference = calculateMinimumDistanceToTraverse(
            location.getVertical(), destination.getVertical(), world.getVerticalCellCount());
      estimateDistanceFromCurrentToEnd = horizontalDifference * horizontalDifference + verticalDifference * verticalDifference;
   }
   
   private int calculateMinimumDistanceToTraverse(int a, int b, int dimension) {
      //the positive result will be within the world bounds, the negative will be wrapping around
      int differenceA = Math.floorMod(a - b, dimension);
      int differenceB = Math.floorMod(b - a, dimension);
      return Math.min(differenceA, differenceB);
   }
   
   @Override
   public String toString() {
      return "Node: " + location;
   }
}
