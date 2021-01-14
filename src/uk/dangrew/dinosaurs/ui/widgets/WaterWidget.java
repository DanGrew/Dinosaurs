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
import uk.dangrew.dinosaurs.ui.world.WorldViewport;

/**
 * Ui representation of {@link Water}.
 */
public class WaterWidget extends Pane {
   
   private final DinosaursConfiguration dinosaursConfiguration;
   private final Water water;
   private final WorldViewport worldViewport;
   
   public WaterWidget(DinosaursConfiguration dinosaursConfiguration, Water water, WorldViewport worldViewport) {
      this.dinosaursConfiguration = dinosaursConfiguration;
      this.water = water;
      this.worldViewport = worldViewport;
      
      worldViewport.topLeftProperty().addListener((s, o, n) -> redraw());
   }
   
   public void redraw() {
      getChildren().clear();
      
      Collection<WorldLocation> locationsInView = worldViewport.getLocationsInView();
      water.getCoverage().stream()
            .filter(locationsInView::contains)
            .map(this::createWidgetAt)
            .forEach(getChildren()::add);
   }
   
   private Node createWidgetAt(WorldLocation worldLocation) {
      WorldLocation worldLocationToDisplayAt = worldViewport.translateToScreen(worldLocation);
      
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
