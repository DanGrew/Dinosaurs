
package uk.dangrew.dinosaurs.ui.stats;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import uk.dangrew.dinosaurs.game.behaviour.consumption.ConsumptionWarnings;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.controls.PanningControls;
import uk.dangrew.dinosaurs.ui.view.CameraController;
import uk.dangrew.kode.javafx.custom.BoundIntegerAsTextProperty;
import uk.dangrew.kode.javafx.custom.BoundTextProperty;
import uk.dangrew.kode.javafx.custom.PropertiesPane;
import uk.dangrew.kode.javafx.style.JavaFxStyle;
import uk.dangrew.kode.javafx.style.PropertyRowBuilder;

/**
 * Ui component providing stats about the player.
 */
public class StatPanel {
   
   private final BorderPane content;
   
   private final ChangeListener<WorldLocation> locationUpdater;
   private final ChangeListener<Integer> hungerUpdater;
   private final ChangeListener<Integer> thirstUpdater;
   
   private final ObjectProperty<String> locationProperty;
   private final ObjectProperty<String> hungerProperty;
   private final ObjectProperty<String> thirstProperty;
   
   private BoundTextProperty hungerRegion;
   private BoundTextProperty thirstRegion;
   
   public StatPanel(GameState gameState, CameraController cameraController) {
      this(new PanningControls(cameraController), gameState);
   }
   
   StatPanel(PanningControls panningControls, GameState gameState) {
      this.locationUpdater = this::locationUpdater;
      this.hungerUpdater = this::hungerUpdater;
      this.thirstUpdater = this::thirstUpdater;
      this.locationProperty = new SimpleObjectProperty<>("Unknown");
      this.hungerProperty = new SimpleObjectProperty<>("Unknown");
      this.thirstProperty = new SimpleObjectProperty<>("Unknown");
      this.content = new BorderPane();
      
      content.setTop(panningControls);
      
      gameState.currentPlayer().addListener((s, o, n) -> resetStats(o, n));
   }
   
   public Node getGraphicalComponent() {
      return content;
   }
   
   private void resetStats(Dinosaur oldPlayer, Dinosaur player) {
      if (oldPlayer != null) {
         oldPlayer.getWorldLocation().removeListener(locationUpdater);
         oldPlayer.hunger().removeListener(hungerUpdater);
         oldPlayer.thirst().removeListener(thirstUpdater);
      }
      
      player.getWorldLocation().addListener(locationUpdater);
      player.hunger().addListener(hungerUpdater);
      player.thirst().addListener(thirstUpdater);

      content.setCenter(new PropertiesPane("Stats",
            new PropertyRowBuilder()
                  .withLabelName("Name:")
                  .withBinding(new BoundTextProperty(player.properties().nameProperty(), false)),
            new PropertyRowBuilder()
                  .withLabelName("Location:")
                  .withBinding(new BoundTextProperty(locationProperty, false)),
            new PropertyRowBuilder()
                  .withLabelName("Height:")
                  .withBinding(new BoundIntegerAsTextProperty(player.height(), false)),
            new PropertyRowBuilder()
                  .withLabelName("Hunger:")
                  .withBinding(hungerRegion = new BoundTextProperty(hungerProperty, false)),
            new PropertyRowBuilder()
                  .withLabelName("Thirst:")
                  .withBinding(thirstRegion = new BoundTextProperty(thirstProperty, false))));
      
      locationUpdater(null, null, player.expectLocation());
      hungerUpdater(null, null, player.hunger().get());
      thirstUpdater(null, null, player.thirst().get());
   }
   
   private void locationUpdater(ObservableValue<? extends WorldLocation> source, WorldLocation old, WorldLocation updated) {
      if (updated == null) {
         locationProperty.set("Unknown");
      }
      locationProperty.set(updated.wrapedCoordinates());
   }
   
   private void hungerUpdater(ObservableValue<? extends Integer> source, Integer old, Integer updated) {
      if (updated == null) {
         updated = 0;
      }
      
      ConsumptionWarnings warning = ConsumptionWarnings.of(updated);
      hungerProperty.set(warning.hungerName());
      hungerRegion.region().setBackground(new JavaFxStyle().backgroundFor(warning.colour()));
   }
   
   private void thirstUpdater(ObservableValue<? extends Integer> source, Integer old, Integer updated) {
      if (updated == null) {
         updated = 0;
      }
      
      ConsumptionWarnings warning = ConsumptionWarnings.of(updated);
      thirstProperty.set(warning.thirstName());
      thirstRegion.region().setBackground(new JavaFxStyle().backgroundFor(warning.colour()));
   }
   
   ReadOnlyObjectProperty<String> locationProperty() {
      return locationProperty;
   }
   
   ReadOnlyObjectProperty<String> hungerProperty() {
      return hungerProperty;
   }
   
   ReadOnlyObjectProperty<String> thirstProperty() {
      return thirstProperty;
   }
}
