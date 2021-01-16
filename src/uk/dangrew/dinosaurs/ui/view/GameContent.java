package uk.dangrew.dinosaurs.ui.view;

import javafx.scene.layout.Pane;
import uk.dangrew.dinosaurs.ui.widgets.AssetWidget;

/**
 * Container for the game's graphical content in terms of assets, that will contain everything related to the game and its world.
 */
public class GameContent extends Pane {
   
   private static final int WINDOW_DIMENSION = 800;
   
   public GameContent(){
      setPrefSize(WINDOW_DIMENSION, WINDOW_DIMENSION);
      setMinSize(WINDOW_DIMENSION, WINDOW_DIMENSION);
      setMaxSize(WINDOW_DIMENSION, WINDOW_DIMENSION);
   }
   
   public void addWidget(AssetWidget assetWidget){
      getChildren().add(assetWidget.getGraphicalComponent());
   }

   public void removeWidget(AssetWidget widget) {
      getChildren().remove(widget);
   }
}