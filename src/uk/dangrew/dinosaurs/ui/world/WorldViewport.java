
package uk.dangrew.dinosaurs.ui.world;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Viewport for the world as the whole definition is not always viewable. This refines the world locations based on what is viewable.
 */
public class WorldViewport {
   
   private static final int NUMBER_OF_CELLS_IN_VIEW = 20;
   
   private final World world;
   
   private ObjectProperty<WorldLocation> topLeft;
   
   public WorldViewport(World world){
      this.world = world;
      this.topLeft = new SimpleObjectProperty<>(new WorldLocation(0, 0));
   }

   public ObjectProperty<WorldLocation> topLeftProperty() {
      return topLeft;
   }

   public Collection<WorldLocation> getLocationsInView(){
      Collection<WorldLocation> viewableLocations = new ArrayList<>();
      for(int horizontal = 0; horizontal < NUMBER_OF_CELLS_IN_VIEW; horizontal++) {
         for(int vertical = 0; vertical < NUMBER_OF_CELLS_IN_VIEW; vertical++) {
            int candidateHorizontal = topLeft.get().getHorizontal() + horizontal;
            candidateHorizontal %= world.getHorizontalCellCount();

            int candidateVertical = topLeft.get().getVertical() + vertical;
            candidateVertical %= world.getVerticalCellCount();
            
            viewableLocations.add(new WorldLocation(candidateHorizontal, candidateVertical));
         }
      }
      return viewableLocations;
   }
   
   public WorldLocation translateToScreen(WorldLocation worldLocation){
      return worldLocation.difference(
            topLeftProperty().get(), world.getHorizontalCellCount(), world.getVerticalCellCount());
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
   
   private void updateLocation(int horizontalAdjustment, int verticalAdjustment){
      WorldLocation worldLocation = topLeft.get();
      int horizontal = (worldLocation.getHorizontal() + horizontalAdjustment) % world.getHorizontalCellCount();
      int vertical = (worldLocation.getVertical() + verticalAdjustment) % world.getVerticalCellCount();
      topLeft.set(new WorldLocation(horizontal, vertical));
   }
}