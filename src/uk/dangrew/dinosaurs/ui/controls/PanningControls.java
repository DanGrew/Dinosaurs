

package uk.dangrew.dinosaurs.ui.controls;

import javafx.scene.input.KeyEvent;
import uk.dangrew.dinosaurs.ui.main.Dinosaurs;
import uk.dangrew.dinosaurs.ui.view.CameraController;
import uk.dangrew.kode.javafx.controls.DirectionControlType;
import uk.dangrew.kode.javafx.keyboard.KeyBoardCapture;

public class PanningControls extends AbstractGameControls {
   
   private final CameraController cameraController;

   public PanningControls(CameraController cameraController) {
      this(Dinosaurs.keyBoard(), cameraController);
   }
   
   public PanningControls(KeyBoardCapture keyBoardCapture, CameraController cameraController) {
      super(keyBoardCapture);
      this.cameraController = cameraController;
      
      setActionListener(this::pan);
   }
   
   private void pan(DirectionControlType type){
      switch (type){
         case Up:
            cameraController.panUp();
            break;
         case Down:
            cameraController.panDown();
            break;
         case Left:
            cameraController.panLeft();
            break;
         case Right:
            cameraController.panRight();
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
