

package uk.dangrew.dinosaurs.ui.widgets;

import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;

/**
 * Ui representation of {@link Water}.
 */
public class WaterWidget extends Pane implements AssetWidget {
   
   private final DinosaursConfiguration dinosaursConfiguration;
   private final Water water;
   private final WorldViewport worldViewport;
   
   public WaterWidget(DinosaursConfiguration dinosaursConfiguration, Water water, WorldViewport worldViewport) {
      this.dinosaursConfiguration = dinosaursConfiguration;
      this.water = water;
      this.worldViewport = worldViewport;
      
      dinosaursConfiguration.currentWorld().addListener((s, o, n) -> redraw());
      worldViewport.topLeftProperty().addListener((s, o, n) -> redraw());
   }

   @Override
   public Node getGraphicalComponent() {
      return this;
   }

   @Override
   public void redraw() {
      getChildren().clear();
      
      Collection<WorldLocation> locationsInView = worldViewport.getLocationsInView();
      water.getCoverage().stream()
            .filter(locationsInView::contains)
            .map(this::createWidgetAt)
            .forEach(getChildren()::add);
   }

   @Override
   public void destroy() {
      getChildren().clear();
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
