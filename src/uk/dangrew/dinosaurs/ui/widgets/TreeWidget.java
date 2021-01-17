
package uk.dangrew.dinosaurs.ui.widgets;

import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.resources.DinosaurImages;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;

public class TreeWidget extends Pane implements AssetWidget {

   private static final double WIDGET_SCALE_FACTOR = 1.5;
   
   private final GameState gameState;
   private final WorldViewport worldViewport;
   private final Tree tree;
   
   public TreeWidget(GameState gameState, Tree tree, WorldViewport worldViewport) {
      this.gameState = gameState;
      this.worldViewport = worldViewport;
      this.tree = tree;

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
      createWidgetAt(tree.getWorldLocation());
   }

   @Override
   public void destroy() {
      getChildren().clear();
   }

   private void createWidgetAt(WorldLocation worldLocation){
      Optional<WorldLocation> worldLocationToDisplayAt = worldViewport.translateToScreen(worldLocation);
      if (!worldLocationToDisplayAt.isPresent()) {
         return;
      }
      int worldCellDimension = gameState.worldCellDimension().get();

      int horizontalLocation = worldLocationToDisplayAt.get().getHorizontal() * worldCellDimension;
      int verticalLocation = worldLocationToDisplayAt.get().getVertical() * worldCellDimension;

      ImageView imageView = new ImageView(new DinosaurImages().tree());
      imageView.setFitWidth(worldCellDimension * WIDGET_SCALE_FACTOR);
      imageView.setFitHeight(worldCellDimension * WIDGET_SCALE_FACTOR);
      imageView.setX(horizontalLocation);
      imageView.setY(verticalLocation);
      getChildren().add(imageView);
   }
}