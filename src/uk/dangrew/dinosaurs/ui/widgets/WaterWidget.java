
package uk.dangrew.dinosaurs.ui.widgets;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;

/**
 * Ui representation of {@link Water}.
 */
public class WaterWidget extends Pane implements AssetWidget {
   
   private final GameState gameState;
   private final Water water;
   private final WorldViewport worldViewport;
   
   public WaterWidget(GameState gameState, Water water, WorldViewport worldViewport) {
      this.gameState = gameState;
      this.water = water;
      this.worldViewport = worldViewport;
      
      gameState.currentWorld().addListener((s, o, n) -> redraw());
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
            .filter(Objects::nonNull)
            .forEach(getChildren()::add);
   }
   
   @Override
   public void destroy() {
      getChildren().clear();
   }
   
   private Node createWidgetAt(WorldLocation worldLocation) {
      Optional<WorldLocation> worldLocationToDisplayAt = worldViewport.translateToScreen(worldLocation);
      if (!worldLocationToDisplayAt.isPresent()) {
         return null;
      }
      int worldCellDimension = gameState.worldCellDimension().get();
      
      int horizontalLocation = worldLocationToDisplayAt.get().getHorizontal() * worldCellDimension;
      int verticalLocation = worldLocationToDisplayAt.get().getVertical() * worldCellDimension;
      
      ImageView imageView = water.getLocationPropertiesFor(worldLocation).getTileType().buildRawImageView();
      imageView.setFitWidth(worldCellDimension);
      imageView.setFitHeight(worldCellDimension);
      imageView.setX(horizontalLocation);
      imageView.setY(verticalLocation);
      return imageView;
   }
}
