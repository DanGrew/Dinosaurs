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

import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.world.UiWorld;

/**
 * Ui representation of {@link Water}.
 */
public class WaterWidget extends Pane {
   
   private final DinosaursConfiguration dinosaursConfiguration;
   private final Water water;
   
   public WaterWidget(DinosaursConfiguration dinosaursConfiguration, Water water) {
      this.dinosaursConfiguration = dinosaursConfiguration;
      this.water = water;
   }
   
   public void redraw(UiWorld uiWorld) {
      getChildren().clear();
      
      Collection<WorldLocation> locationsInView = uiWorld.getViewport().getLocationsInView();
      water.getCoverage().stream()
            .filter(locationsInView::contains)
            .map(location -> createWidgetAt(location, uiWorld))
            .forEach(getChildren()::add);
   }
   
   private Node createWidgetAt(WorldLocation worldLocation, UiWorld uiWorld) {
      WorldLocation worldLocationToDisplayAt = uiWorld.getViewport().translateToScreen(worldLocation);
      
      int worldCellDimension = dinosaursConfiguration.worldCellDimension().get();
      
      int horizontalLocation = worldLocationToDisplayAt.getHorizontal() * worldCellDimension;
      int verticalLocation = worldLocationToDisplayAt.getVertical() * worldCellDimension;
      
      ImageView imageView = water.getLocationPropertiesFor(worldLocation).getTileType().buildRawImageView();
      imageView.setFitWidth(worldCellDimension);
      imageView.setFitHeight(worldCellDimension);
      imageView.setX(horizontalLocation);
      imageView.setY(verticalLocation);
      return imageView;
   }
}
