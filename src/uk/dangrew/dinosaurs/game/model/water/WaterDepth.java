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
package uk.dangrew.dinosaurs.game.model.water;

import java.util.Objects;

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