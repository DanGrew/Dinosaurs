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
package uk.dangrew.dinosaurs.ui.widgets;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.world.UiWorld;

public class TreeWidget extends Pane {
   
   private final DinosaursConfiguration dinosaursConfiguration;
   private final Tree tree;
   
   public TreeWidget(DinosaursConfiguration dinosaursConfiguration, Tree tree) {
      this.dinosaursConfiguration = dinosaursConfiguration;
      this.tree = tree;
   }
   
   public void redraw(UiWorld uiWorld){
      WorldLocation worldLocation = uiWorld.getViewport().translateToScreen(tree.getWorldLocation());

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