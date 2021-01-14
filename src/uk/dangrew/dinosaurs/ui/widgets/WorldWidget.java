

package uk.dangrew.dinosaurs.ui.widgets;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.resources.DinosaurImages;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;

/**
 * Ui representation of the {@link World}.
 */
public class WorldWidget extends Pane implements AssetWidget {

   private final DinosaursConfiguration dinosaursConfiguration;
   
   private final World world;
   private final WorldViewport worldViewport;
   
   public WorldWidget(DinosaursConfiguration dinosaursConfiguration, World world, WorldViewport worldViewport) {
      this.dinosaursConfiguration = dinosaursConfiguration;
      this.world = world;
      this.worldViewport = worldViewport;

      dinosaursConfiguration.currentWorld().addListener((s, o, n) -> redraw());
   }

   @Override
   public Node getGraphicalComponent() {
      return this;
   }

   public WorldViewport getViewport() {
      return worldViewport;
   }

   public World getWorld() {
      return world;
   }

   @Override
   public void redraw() {
      getChildren().clear();
      
      int worldCellDimension = dinosaursConfiguration.worldCellDimension().get();
      
      for (WorldLocation location : getViewport().getLocationsInView()) {
         int horizontalLocation = (location.getHorizontal() - getViewport().topLeftProperty().get().getHorizontal()) * worldCellDimension;
         int verticalLocation = (location.getVertical() - getViewport().topLeftProperty().get().getVertical()) * worldCellDimension;
         
         ImageView imageView = new ImageView(new DinosaurImages().grass());
         imageView.setFitWidth(worldCellDimension);
         imageView.setFitHeight(worldCellDimension);
         imageView.setX(horizontalLocation);
         imageView.setY(verticalLocation);
         getChildren().add(imageView);
      }
   }

   @Override
   public void destroy() {
      getChildren().clear();
   }
}
