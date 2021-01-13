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

package uk.dangrew.dinosaurs.ui.controls;

import javafx.scene.input.KeyEvent;
import uk.dangrew.dinosaurs.ui.main.Dinosaurs;
import uk.dangrew.dinosaurs.ui.world.WorldViewport;
import uk.dangrew.kode.javafx.controls.DirectionControlType;
import uk.dangrew.kode.javafx.keyboard.KeyBoardCapture;

public class PanningControls extends AbstractGameControls {
   
   private final WorldViewport worldViewport;

   public PanningControls(WorldViewport worldViewport) {
      this(Dinosaurs.keyBoard(), worldViewport);
   }
   
   public PanningControls(KeyBoardCapture keyBoardCapture, WorldViewport worldViewport) {
      super(keyBoardCapture);
      this.worldViewport = worldViewport;
      
      setActionListener(this::pan);
   }
   
   private void pan(DirectionControlType type){
      switch (type){
         case Up:
            worldViewport.moveUp();
            break;
         case Down:
            worldViewport.moveDown();
            break;
         case Left:
            worldViewport.moveLeft();
            break;
         case Right:
            worldViewport.moveRight();
            break;
      }
   }

   @Override
   protected void customKeyHandler(KeyEvent event) {
      if ( !isShiftHeld()){
         return;
      }
      
      DirectionControlType.of(event.getCode()).ifPresent(this::pan);
   }
}
