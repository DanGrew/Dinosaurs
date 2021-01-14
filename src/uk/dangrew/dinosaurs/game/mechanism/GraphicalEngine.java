
package uk.dangrew.dinosaurs.game.mechanism;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.storage.WidgetManager;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.widgets.DinosaurWidget;
import uk.dangrew.dinosaurs.ui.widgets.WaterWidget;
import uk.dangrew.dinosaurs.ui.world.GameContent;
import uk.dangrew.dinosaurs.ui.world.WorldViewport;
import uk.dangrew.dinosaurs.ui.world.WorldWidget;

/**
 * Core object for the ui.
 */
public class GraphicalEngine {
   
   private final DinosaursConfiguration dinosaursConfiguration;
   private final AssetManager assetManager;
   
   private final WorldViewport worldViewport;
   private final CameraController cameraController;
   
   private final WidgetManager<World, WorldWidget> worldWidgets;
   private final WidgetManager<Dinosaur, DinosaurWidget> dinosaurWidgets;
   private final WidgetManager<Water, WaterWidget> waterWidgets;
   
   private final GameContent gameContent;
   
   public GraphicalEngine(GameContent gameContent, DinosaursConfiguration dinosaursConfiguration, AssetManager assetManager) {
      this.gameContent = gameContent;
      this.dinosaursConfiguration = dinosaursConfiguration;
      this.assetManager = assetManager;
      
      this.worldViewport = new WorldViewport(dinosaursConfiguration);
      this.cameraController = new CameraController(worldViewport);
      
      this.worldWidgets = new WidgetManager<>(gameContent, dinosaursConfiguration, worldViewport, assetManager.getWorldStore());
      this.dinosaurWidgets = new WidgetManager<>(gameContent, dinosaursConfiguration, worldViewport, assetManager.getDinosaurStore());
      this.waterWidgets = new WidgetManager<>(gameContent, dinosaursConfiguration, worldViewport, assetManager.getWaterStore());
   }
   
   public CameraController getCameraController() {
      return cameraController;
   }
   
}
