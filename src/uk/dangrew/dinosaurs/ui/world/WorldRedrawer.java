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
package uk.dangrew.dinosaurs.ui.world;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;

/**
 * Handler for changes in the world that should cause it to redraw.
 */
public class WorldRedrawer {
   
   private final UiWorld uiWorld;
   
   private final Dinosaur dinosaur;
   private final Tree tree;
   
   public WorldRedrawer(UiWorld uiWorld, Dinosaur dinosaur, Tree tree){
      this.uiWorld = uiWorld;
      this.dinosaur = dinosaur;
      this.tree = tree;

      uiWorld.getViewport().topLeftProperty().addListener( (s, o, n) -> uiWorld.refresh());
      dinosaur.getWorldLocation().addListener((s, o, n) -> uiWorld.refresh());
   }
   
}