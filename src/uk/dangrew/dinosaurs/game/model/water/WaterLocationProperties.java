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

/**
 * Individual properties of water at a specific world location.
 */
public class WaterLocationProperties {
   
   private final WaterDepth depth;
   private final WaterTileType tileType;
   
   public WaterLocationProperties(int depth, WaterTileType tileType){
      this.depth = new WaterDepth(depth);
      this.tileType = tileType;
   }

   public WaterDepth getDepth() {
      return depth;
   }

   public WaterTileType getTileType() {
      return tileType;
   }
}