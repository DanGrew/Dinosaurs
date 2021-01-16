
package uk.dangrew.dinosaurs.ui.widgets;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;

public class TreeWidget extends Pane {
   
   private final GameState gameState;
   private final Tree tree;
   
   public TreeWidget(GameState gameState, Tree tree) {
      this.gameState = gameState;
      this.tree = tree;
   }
   
   public void redraw(WorldWidget worldWidget){
   }
   
   private Circle createWidgetAt(WorldLocation worldLocation){
      int worldCellDimension = gameState.worldCellDimension().get();
      int worldCellRadius = gameState.worldCellRadius().get();
      
      Circle widget = new Circle(gameState.worldCellRadius().get());
      int horizontalLocation = worldLocation.getHorizontal() * worldCellDimension + worldCellRadius;
      int verticalLocation = worldLocation.getVertical() * worldCellDimension + worldCellRadius;
      widget.setCenterX(horizontalLocation);
      widget.setCenterY(verticalLocation);
      
      return widget;
   }
}