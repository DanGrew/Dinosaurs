
package uk.dangrew.dinosaurs.game.mechanism;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.CameraController;
import uk.dangrew.dinosaurs.ui.view.GameContent;
import uk.dangrew.dinosaurs.ui.view.PlayerFocusController;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;
import uk.dangrew.dinosaurs.ui.widgets.DinosaurWidget;
import uk.dangrew.dinosaurs.ui.widgets.TreeWidget;
import uk.dangrew.dinosaurs.ui.widgets.WaterWidget;
import uk.dangrew.dinosaurs.ui.widgets.WidgetManager;
import uk.dangrew.dinosaurs.ui.widgets.WorldWidget;

/**
 * Core object for the ui.
 */
public class GraphicalEngine {
   
   private final GameState gameState;
   private final AssetManager assetManager;
   
   private final WorldViewport worldViewport;
   private final CameraController cameraController;
   
   private final WidgetManager<World, WorldWidget> worldWidgets;
   private final WidgetManager<Dinosaur, DinosaurWidget> dinosaurWidgets;
   private final WidgetManager<Water, WaterWidget> waterWidgets;
   private final WidgetManager<Tree, TreeWidget> treeWidgets;
   
   private final GameContent gameContent;
   
   public GraphicalEngine(GameContent gameContent, GameState gameState, AssetManager assetManager) {
      this.gameContent = gameContent;
      this.gameState = gameState;
      this.assetManager = assetManager;
      
      this.worldViewport = new WorldViewport(gameState);
      new PlayerFocusController(gameState, worldViewport);
      this.cameraController = new CameraController(worldViewport);
      
      this.worldWidgets = new WidgetManager<>(gameContent, gameState, worldViewport, assetManager.getWorldStore());
      this.dinosaurWidgets = new WidgetManager<>(gameContent, gameState, worldViewport, assetManager.getDinosaurStore());
      this.waterWidgets = new WidgetManager<>(gameContent, gameState, worldViewport, assetManager.getWaterStore());
      this.treeWidgets = new WidgetManager<>(gameContent, gameState, worldViewport, assetManager.getTreeStore());
   }
   
   public CameraController getCameraController() {
      return cameraController;
   }
   
}
