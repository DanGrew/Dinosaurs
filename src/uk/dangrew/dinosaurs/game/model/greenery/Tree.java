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