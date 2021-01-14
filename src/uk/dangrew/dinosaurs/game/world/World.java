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

import uk.dangrew.dinosaurs.game.mechanism.Movement;

/**
 * Definition of the world, and the associated grid behind it.
 */
public class World {
   
   private static final int WORLD_DIMENSION = 100;
   
   private final WorldLocation[][] worldLocations;
   
   public World() {
      this.worldLocations = new WorldLocation[WORLD_DIMENSION][WORLD_DIMENSION];
      for (int horizontal = 0; horizontal < WORLD_DIMENSION; horizontal++) {
         for (int vertical = 0; vertical < WORLD_DIMENSION; vertical++) {
            worldLocations[horizontal][vertical] = new WorldLocation(horizontal, vertical);
         }
      }
   }
   
   public WorldLocation locationForMovement(WorldLocation worldLocation, Movement movement) {
      WorldLocation rawMove = movement.rawMove(worldLocation);
      return new WorldLocation(
            rawMove.getHorizontal() % getHorizontalCellCount(),
            rawMove.getVertical() % getVerticalCellCount());
   }
   
   public int getHorizontalCellCount() {
      return WORLD_DIMENSION;
   }
   
   public int getVerticalCellCount() {
      return WORLD_DIMENSION;
   }
}
