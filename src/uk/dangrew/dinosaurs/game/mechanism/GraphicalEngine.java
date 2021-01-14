package uk.dangrew.dinosaurs.game.mechanism;

import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.widgets.DinosaurWidget;
import uk.dangrew.dinosaurs.ui.widgets.WaterWidget;
import uk.dangrew.dinosaurs.ui.world.UiWorld;

/**
 * Core object for the ui.
 */
public class GraphicalEngine {

   private static final int WINDOW_DIMENSION = 600;
   
   private final CameraController cameraController;
   private final UiWorld uiWorld;
   
   public GraphicalEngine(DinosaursConfiguration dinosaursConfiguration, AssetManager assetManager){
      this.uiWorld = new UiWorld(dinosaursConfiguration, assetManager.getWorld());
      uiWorld.setPrefSize(WINDOW_DIMENSION, WINDOW_DIMENSION);
      uiWorld.setMinSize(WINDOW_DIMENSION, WINDOW_DIMENSION);
      uiWorld.setMaxSize(WINDOW_DIMENSION, WINDOW_DIMENSION);

      this.cameraController = new CameraController(uiWorld.getViewport());

      WaterWidget waterWidget = new WaterWidget(dinosaursConfiguration, assetManager.getWater(), uiWorld.getViewport());
      waterWidget.redraw();
      uiWorld.getChildren().add(waterWidget);
      
      DinosaurWidget dinosaurWidget = new DinosaurWidget(dinosaursConfiguration, assetManager.getDinosaur(), uiWorld.getViewport());
      dinosaurWidget.redraw();
      uiWorld.getChildren().add(dinosaurWidget);
   }

   public UiWorld getUiWorld() {
      return uiWorld;
   }

   public CameraController getCameraController() {
      return cameraController;
   }
}