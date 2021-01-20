
package uk.dangrew.dinosaurs.ui.view;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;

/**
 * Viewport for the world as the whole definition is not always viewable. This refines the world locations based on what is viewable.
 */
public class WorldViewport {
   
   private static final int NUMBER_OF_CELLS_IN_VIEW = 20;
   
   private final GameState gameState;
   private final ObjectProperty<World> world;
   private final ObjectProperty<WorldLocation> topLeft;
   private final IntegerProperty numberOfCellsInViewDimension;
   
   public WorldViewport(GameState gameState) {
      this.gameState = gameState;
      this.world = new SimpleObjectProperty<>();
      this.topLeft = new SimpleObjectProperty<>();
      this.numberOfCellsInViewDimension = new SimpleIntegerProperty(20);
      
      gameState.currentWorld().addListener((s, o, n) -> resetWorld(n));
   }
   
   public ObjectProperty<WorldLocation> topLeftProperty() {
      return topLeft;
   }
   
   public WorldLocation topLeft() {
      return topLeftProperty().get();
   }
   
   public int numberOfCellsInViewDimension() {
      return numberOfCellsInViewDimension.get();
   }
   
   public Collection<WorldLocation> getLocationsInView() {
      World currentWorld = gameState.expectWorld();
      
      Collection<WorldLocation> viewableLocations = new HashSet<>();
      for (int horizontal = 0; horizontal < NUMBER_OF_CELLS_IN_VIEW; horizontal++) {
         for (int vertical = 0; vertical < NUMBER_OF_CELLS_IN_VIEW; vertical++) {
            int candidateHorizontal = topLeft.get().getHorizontal() + horizontal;
            int candidateVertical = topLeft.get().getVertical() + vertical;
            
            viewableLocations.add(new WorldLocation(candidateHorizontal, candidateVertical, currentWorld));
         }
      }
      return viewableLocations;
   }
   
   public boolean withinView(WorldLocation worldLocation) {
      return getLocationsInView().contains(worldLocation);
   }
   
   public Optional<WorldLocation> translateToScreen(WorldLocation worldLocation) {
      World currentWorld = gameState.expectWorld();
      
      if (withinView(worldLocation)) {
         WorldLocation newLocation = worldLocation.difference(topLeftProperty().get(), currentWorld);
         return Optional.of(newLocation);
      } else {
         return Optional.empty();
      }
   }
   
   public void panUp() {
      updateLocation(0, -1);
   }
   
   public void panDown() {
      updateLocation(0, 1);
   }
   
   public void panLeft() {
      updateLocation(-1, 0);
   }
   
   public void panRight() {
      updateLocation(1, 0);
   }
   
   private void resetWorld(World world) {
      this.world.set(world);
      this.topLeft.set(new WorldLocation(0, 0, world));
   }
   
   private void updateLocation(int horizontalAdjustment, int verticalAdjustment) {
      World currentWorld = gameState.expectWorld();
      WorldLocation worldLocation = topLeft.get();
      topLeft.set(worldLocation.translate(horizontalAdjustment, verticalAdjustment, currentWorld));
   }
}
