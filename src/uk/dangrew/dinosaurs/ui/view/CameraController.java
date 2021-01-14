package uk.dangrew.dinosaurs.ui.view;

/**
 * Interface for controlling camera related behaviour.
 */
public class CameraController {
   
   private final WorldViewport worldViewport;
   
   public CameraController(WorldViewport worldViewport){
      this.worldViewport = worldViewport;
   }
   
   public void panUp() {
      worldViewport.panUp();
   }

   public void panDown() {
      worldViewport.panDown();
   }

   public void panLeft() {
      worldViewport.panLeft();
   }

   public void panRight() {
      worldViewport.panRight();
   }
}