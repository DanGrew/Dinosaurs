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
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.ui.main.Dinosaurs;
import uk.dangrew.kode.javafx.controls.DirectionControlType;
import uk.dangrew.kode.javafx.keyboard.KeyBoardCapture;

/**
 * Provides ui controls for moving the player.
 */
public class MovementControls extends AbstractGameControls {
   
   private final World world;
   private final Dinosaur dinosaur;

   public MovementControls(Dinosaur dinosaur, World world) {
      this(Dinosaurs.keyBoard(), dinosaur, world);
   }
   
   public MovementControls(KeyBoardCapture keyBoardCapture, Dinosaur dinosaur, World world) {
      super(keyBoardCapture);
      this.world = world;
      this.dinosaur = dinosaur;
      
      setActionListener(this::handleMovement);
   }
   
   private void handleMovement(DirectionControlType type) {
      switch (type) {
         case Up:
            dinosaur.moveUp(world);
            break;
         case Down:
            dinosaur.moveDown(world);
            break;
         case Left:
            dinosaur.moveLeft(world);
            break;
         case Right:
            dinosaur.moveRight(world);
            break;
      }
   }
   
   @Override
   protected void customKeyHandler(KeyEvent event) {
      if ( isShiftHeld()){
         return;
      }
      DirectionControlType.of(event.getCode()).ifPresent(this::handleMovement);
   }
}
