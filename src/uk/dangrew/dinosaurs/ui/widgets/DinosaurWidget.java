
package uk.dangrew.dinosaurs.ui.widgets;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.world.UiWorld;
import uk.dangrew.dinosaurs.ui.world.WorldViewport;

public class DinosaurWidget extends Pane {
   
   private final DinosaursConfiguration dinosaursConfiguration;
   private final Dinosaur dinosaur;
   private final WorldViewport worldViewport;
   
   public DinosaurWidget(DinosaursConfiguration dinosaursConfiguration, Dinosaur dinosaur, WorldViewport worldViewport) {
      this.dinosaursConfiguration = dinosaursConfiguration;
      this.dinosaur = dinosaur;
      this.worldViewport = worldViewport;

      dinosaur.getWorldLocation().addListener((s, o, n) -> redraw());
      worldViewport.topLeftProperty().addListener((s, o, n) -> redraw());
   }
   
   public void redraw(){
      getChildren().clear();
      
      WorldLocation worldLocation = worldViewport.translateToScreen(dinosaur.getWorldLocation().get());

      Circle dinosaurWidget = createWidgetAt(worldLocation);
      Circle sightWidget = createWidgetAt(worldLocation);
      sightWidget.setOpacity(0.3);
      sightWidget.setFill(Color.GREEN);
      sightWidget.setRadius(dinosaur.getSightRadius() * dinosaursConfiguration.worldCellDimension().get() + dinosaursConfiguration.worldCellRadius().get());

      getChildren().add(sightWidget);
      getChildren().add(dinosaurWidget);
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