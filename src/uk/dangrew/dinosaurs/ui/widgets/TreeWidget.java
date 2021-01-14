
package uk.dangrew.dinosaurs.ui.widgets;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.world.WorldWidget;

public class TreeWidget extends Pane {
   
   private final DinosaursConfiguration dinosaursConfiguration;
   private final Tree tree;
   
   public TreeWidget(DinosaursConfiguration dinosaursConfiguration, Tree tree) {
      this.dinosaursConfiguration = dinosaursConfiguration;
      this.tree = tree;
   }
   
   public void redraw(WorldWidget worldWidget){
      WorldLocation worldLocation = worldWidget.getViewport().translateToScreen(tree.getWorldLocation());

      Circle treeWidget = createWidgetAt(worldLocation);
      treeWidget.setFill(Color.GREEN);

      getChildren().add(treeWidget);
   }
   
   private Circle createWidgetAt(WorldLocation worldLocation){
      int worldCellDimension = dinosaursConfiguration.worldCellDimension().get();
      int worldCellRadius = dinosaursConfiguration.worldCellRadius().get();
      
      Circle widget = new Circle(dinosaursConfiguration.worldCellRadius().get());
      int horizontalLocation = worldLocation.getHorizontal() * worldCellDimension + worldCellRadius;
      int verticalLocation = worldLocation.getVertical() * worldCellDimension + worldCellRadius;
      widget.setCenterX(horizontalLocation);
      widget.setCenterY(verticalLocation);
      
      return widget;
   }
}