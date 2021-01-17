
package uk.dangrew.dinosaurs.game.building;

import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.SURROUNDED;

import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.model.water.WaterDepth;
import uk.dangrew.dinosaurs.game.model.water.WaterTileType;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Builder for water assets, quickly populating locations and associated properties.
 */
public class WaterBuilder {
   
   private final Water water;
   private final World world;
   private final WaterTileCalculator waterTileCalculator;
   
   public WaterBuilder(Water water, World world) {
      this(new WaterTileCalculator(water, world), water, world);
   }
   
   WaterBuilder(WaterTileCalculator waterTileCalculator, Water water, World world) {
      this.water = water;
      this.world = world;
      this.waterTileCalculator = waterTileCalculator;
   }
   
   public WaterBuilder coverRectangle(WorldLocation topLeft, int width, int height) {
      for (int w = 0; w < width; w++) {
         for (int h = 0; h < height; h++) {
            water.cover(topLeft.translate(w, h, world));
         }
      }
      return this;
   }
   
   public WaterBuilder append(WorldLocation worldLocation) {
      water.cover(worldLocation);
      return this;
   }
   
   public WaterBuilder subtract(WorldLocation worldLocation) {
      water.remove(worldLocation);
      return this;
   }
   
   public void build(){
      populateTileTypes();
      populateDepths();
   }
   
   private WaterBuilder populateTileTypes() {
      for (WorldLocation location : water.getCoverage()) {
         WaterTileType tileType = waterTileCalculator.calculateTileType(location);
         water.getLocationPropertiesFor(location).setTileType(tileType);
      }
      return this;
   }

   private WaterBuilder populateDepths() {
      for (WorldLocation location : water.getCoverage()) {
         if (water.getLocationPropertiesFor(location).getTileType() != SURROUNDED) {
            continue;
         }
         
         water.getLocationPropertiesFor(location).setDepth(
               new WaterDepth(calculateDepth(location)));
      }
      return this;
   }

   private int calculateDepth(WorldLocation worldLocation) {
      WorldLocation topLeft = worldLocation.translate(-1, -1, world);
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            WorldLocation toCheck = topLeft.translate(i, j, world);
            if (water.getLocationPropertiesFor(toCheck).getTileType() != SURROUNDED) {
               return 1;
            }
         }
      }
      return 2;
   }
   
}
