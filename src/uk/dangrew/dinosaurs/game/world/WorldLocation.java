// *************************************************************
//                         H*E*R*M*E*S
//                   Holistic Environment for
//         Railway Modelling, Evaluation and Simulation
//        Built on Graffica System Development Kit: GSDK
//
//       Copyright: (c) Graffica Ltd (www.graffica.co.uk)
//
// This software is made available under binary licence. Holding 
// source code without the express permission of Graffica Ltd is 
//           not permitted under any circumstances. 
// *************************************************************
package uk.dangrew.dinosaurs.game.world;

import java.util.Objects;

/**
 * Represents a specific location in the world, which is split into a grid.
 */
public class WorldLocation {
   
   private final int horizontal;
   private final int vertical;
   
   public WorldLocation(int horizontal, int vertical){
      this.horizontal = horizontal;
      this.vertical = vertical;
   }

   public int getHorizontal() {
      return horizontal;
   }

   public int getVertical() {
      return vertical;
   }

   @Override
   public boolean equals(Object o) {
      if(this == o) {
         return true;
      }
      if(o == null || getClass() != o.getClass()) {
         return false;
      }
      WorldLocation that = (WorldLocation) o;
      return getHorizontal() == that.getHorizontal() && getVertical() == that.getVertical();
   }

   @Override
   public int hashCode() {
      return Objects.hash(getHorizontal(), getVertical());
   }

   public WorldLocation difference(WorldLocation worldLocation, int horizontalCellCount, int verticalCellCount) {
      int horizontal = (getHorizontal() - worldLocation.getHorizontal()) % horizontalCellCount;
      int vertical = (getVertical() - worldLocation.getVertical()) % verticalCellCount;
      return new WorldLocation(horizontal, vertical);
   }
}