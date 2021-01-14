package uk.dangrew.dinosaurs.game.mechanism;

import uk.dangrew.dinosaurs.ui.world.WorldViewport;

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