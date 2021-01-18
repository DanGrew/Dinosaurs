
package uk.dangrew.dinosaurs.ui.widgets;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uk.dangrew.dinosaurs.game.model.rubble.Rock;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;

public class RockWidget extends Pane implements AssetWidget {

   private static final double WIDGET_SCALE_FACTOR = 1.3;
         
   private final GameState gameState;
   private final WorldViewport worldViewport;
   private final Rock rock;
   
   public RockWidget(GameState gameState, Rock rock, WorldViewport worldViewport) {
      this.gameState = gameState;
      this.worldViewport = worldViewport;
      this.rock = rock;

      gameState.currentWorld().addListener((s, o, n) -> redraw());
      worldViewport.topLeftProperty().addListener((s, o, n) -> redraw());
   }

   @Override
   public Node getGraphicalComponent() {
      return this;
   }

   @Override
   public void redraw(){
      getChildren().clear();

      Collection<WorldLocation> locationsInView = worldViewport.getLocationsInView();
      rock.getCoverage().stream()
           .filter(locationsInView::contains)
           .map(this::createWidgetAt)
           .filter(Objects::nonNull)
           .forEach(getChildren()::add);
   }

   @Override
   public void destroy() {
      getChildren().clear();
   }

   private Node createWidgetAt(WorldLocation worldLocation){
      Optional<WorldLocation> worldLocationToDisplayAt = worldViewport.translateToScreen(worldLocation);
      if (!worldLocationToDisplayAt.isPresent()) {
         return null;
      }
      int worldCellDimension = gameState.worldCellDimension().get();

      int horizontalLocation = worldLocationToDisplayAt.get().getHorizontal() * worldCellDimension;
      int verticalLocation = worldLocationToDisplayAt.get().getVertical() * worldCellDimension;

      ImageView imageView = rock.getLocationPropertiesFor(worldLocation).getTileType().buildImageView();
      imageView.setFitWidth(worldCellDimension * WIDGET_SCALE_FACTOR);
      imageView.setFitHeight(worldCellDimension * WIDGET_SCALE_FACTOR);
      imageView.setX(horizontalLocation);
      imageView.setY(verticalLocation);
      return imageView;
   }
}