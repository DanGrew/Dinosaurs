
package uk.dangrew.dinosaurs.ui.view;

import java.util.ArrayList;
import java.util.Collection;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;

/**
 * Viewport for the world as the whole definition is not always viewable. This refines the world locations based on what is viewable.
 */
public class WorldViewport {
   
   private static final int NUMBER_OF_CELLS_IN_VIEW = 20;
   
   private final ObjectProperty<World> world;
   private final ObjectProperty<WorldLocation> topLeft;
   
   public WorldViewport(DinosaursConfiguration dinosaursConfiguration){
      this.world = new SimpleObjectProperty<>();
      this.topLeft = new SimpleObjectProperty<>();
      
      dinosaursConfiguration.currentWorld().addListener((s, o, n) -> resetWorld(n));
   }
   
   private void resetWorld(World world){
      this.world.set(world);
      this.topLeft.set(new WorldLocation(0, 0));  
   }

   public ObjectProperty<WorldLocation> topLeftProperty() {
      return topLeft;
   }
   
   private World unwrapWorld(){
      World currentWorld = this.world.get();
      if ( currentWorld == null){
         throw new IllegalStateException("World expected but not available.");
      }
      return currentWorld;
   }

   public Collection<WorldLocation> getLocationsInView(){
      World currentWorld = unwrapWorld();
      
      Collection<WorldLocation> viewableLocations = new ArrayList<>();
      for(int horizontal = 0; horizontal < NUMBER_OF_CELLS_IN_VIEW; horizontal++) {
         for(int vertical = 0; vertical < NUMBER_OF_CELLS_IN_VIEW; vertical++) {
            int candidateHorizontal = topLeft.get().getHorizontal() + horizontal;
            candidateHorizontal %= currentWorld.getHorizontalCellCount();

            int candidateVertical = topLeft.get().getVertical() + vertical;
            candidateVertical %= currentWorld.getVerticalCellCount();
            
            viewableLocations.add(new WorldLocation(candidateHorizontal, candidateVertical));
         }
      }
      return viewableLocations;
   }
   
   public WorldLocation translateToScreen(WorldLocation worldLocation){
      World currentWorld = unwrapWorld();
      return worldLocation.difference(
            topLeftProperty().get(), currentWorld.getHorizontalCellCount(), currentWorld.getVerticalCellCount());
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
      World currentWorld = unwrapWorld();
      WorldLocation worldLocation = topLeft.get();
      int horizontal = (worldLocation.getHorizontal() + horizontalAdjustment) % currentWorld.getHorizontalCellCount();
      int vertical = (worldLocation.getVertical() + verticalAdjustment) % currentWorld.getVerticalCellCount();
      topLeft.set(new WorldLocation(horizontal, vertical));
   }
}