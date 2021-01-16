
package uk.dangrew.dinosaurs.ui.widgets;

import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;

public class DinosaurWidget extends Pane implements AssetWidget {
   
   private final GameState gameState;
   private final Dinosaur dinosaur;
   private final WorldViewport worldViewport;
   
   public DinosaurWidget(GameState gameState, Dinosaur dinosaur, WorldViewport worldViewport) {
      this.gameState = gameState;
      this.dinosaur = dinosaur;
      this.worldViewport = worldViewport;
      
      dinosaur.getWorldLocation().addListener((s, o, n) -> redraw());
      worldViewport.topLeftProperty().addListener((s, o, n) -> redraw());
      gameState.currentWorld().addListener((s, o, n) -> redraw());
   }
   
   @Override
   public Node getGraphicalComponent() {
      return this;
   }
   
   @Override
   public void redraw() {
      getChildren().clear();
      
      if (gameState.currentWorld().get() == null) {
         return;
      }
      
      worldViewport.translateToScreen(dinosaur.expectLocation())
            .ifPresent(this::createDinosaurWidget);
      
      dinosaur.getInteractionZone().calculateInteractionZone(gameState.expectWorld())
            .forEach(this::createInteractionWidget);
   }
   
   @Override
   public void destroy() {
      getChildren().clear();
   }
   
   private void createDinosaurWidget(WorldLocation worldLocation) {
      int worldCellDimension = gameState.worldCellDimension().get();
      int worldCellRadius = gameState.worldCellRadius().get();
      
      Circle widget = new Circle(gameState.worldCellRadius().get());
      int horizontalLocation = worldLocation.getHorizontal() * worldCellDimension + worldCellRadius;
      int verticalLocation = worldLocation.getVertical() * worldCellDimension + worldCellRadius;
      widget.setCenterX(horizontalLocation);
      widget.setCenterY(verticalLocation);
      
      getChildren().add(widget);
   }
   
   private void createInteractionWidget(WorldLocation worldLocation) {
      int worldCellDimension = gameState.worldCellDimension().get();
      
      Optional<WorldLocation> translatedLocation = worldViewport.translateToScreen(worldLocation);
      if (!translatedLocation.isPresent()) {
         return;
      }
      Rectangle rectangle = new Rectangle(
            translatedLocation.get().getHorizontal() * worldCellDimension,
            translatedLocation.get().getVertical() * worldCellDimension,
            worldCellDimension,
            worldCellDimension);
      rectangle.setFill(Color.GREEN);
      rectangle.setOpacity(0.3);
      getChildren().add(rectangle);
   }
}
