
package uk.dangrew.dinosaurs.ui.stats;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.controls.PanningControls;
import uk.dangrew.dinosaurs.ui.view.CameraController;
import uk.dangrew.kode.javafx.custom.BoundIntegerAsTextProperty;
import uk.dangrew.kode.javafx.custom.BoundTextProperty;
import uk.dangrew.kode.javafx.custom.PropertiesPane;
import uk.dangrew.kode.javafx.style.PropertyRowBuilder;

/**
 * Ui component providing stats about the player.
 */
public class StatPanel {
   
   private final BorderPane content;
   
   private final ChangeListener<WorldLocation> locationUpdater;
   private final ObjectProperty<String> locationProperty;
   
   public StatPanel(GameState gameState, CameraController cameraController) {
      this(new PanningControls(cameraController), gameState);
   }
   
   StatPanel(PanningControls panningControls, GameState gameState) {
      this.locationUpdater = this::locationUpdater;
      this.locationProperty = new SimpleObjectProperty<>("Unknown");
      this.content = new BorderPane();
      
      content.setTop(panningControls);
      
      gameState.currentPlayer().addListener((s, o, n) -> resetStats(o, n));
   }
   
   public Node getGraphicalComponent(){
      return content;
   }
   
   private void resetStats(Dinosaur oldPlayer, Dinosaur player) {
      if (oldPlayer != null) {
         oldPlayer.getWorldLocation().removeListener(locationUpdater);
      }
      
      player.getWorldLocation().addListener(locationUpdater);
      locationProperty.set(player.expectLocation().wrapedCoordinates());
      
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
                  .withBinding(new BoundIntegerAsTextProperty(player.hunger(), false)),
            new PropertyRowBuilder()
                  .withLabelName("Thirst:")
                  .withBinding(new BoundIntegerAsTextProperty(player.thirst(), false))));
   }
   
   private void locationUpdater(ObservableValue<? extends WorldLocation> source, WorldLocation old, WorldLocation updated) {
      if (updated == null) {
         locationProperty.set("Unknown");
      }
      locationProperty.set(updated.wrapedCoordinates());
   }
   
   ReadOnlyObjectProperty<String> locationProperty() {
      return locationProperty;
   }
}
