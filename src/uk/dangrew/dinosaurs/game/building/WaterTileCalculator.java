
package uk.dangrew.dinosaurs.game.building;

import static java.util.Arrays.asList;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.GRASS_BOTTOM_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.GRASS_BOTTOM_RIGHT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.GRASS_TOP_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.GRASS_TOP_RIGHT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.HORIZONTAL_GRASS_BOTTOM;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.HORIZONTAL_GRASS_TOP;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.SURROUNDED;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.VERTICAL_GRASS_LEFT;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.VERTICAL_GRASS_RIGHT;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_BOTTOM_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_BOTTOM_RIGHT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_DIAGONAL_BOTTOM_LEFT_TOP_RIGHT;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_DIAGONAL_TOP_LEFT_BOTTOM_RIGHT;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_TOP_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_TOP_RIGHT_CORNER;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.model.water.WaterTileType;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Object to figure out what tile type should be used based on surrounding water tiles.
 */
public class WaterTileCalculator {
   
   private final Water water;
   private final World world;
   
   private final Map<List<Boolean>, WaterTileType> tileTypes;
   
   public WaterTileCalculator(Water water, World world) {
      this.water = water;
      this.world = world;
      this.tileTypes = new HashMap<>();
      
      tileTypes.put(asList(true, true, true, true, true, true, true, true), SURROUNDED);
      tileTypes.put(asList(true, false, true, true, true, false, true, true), WATER_DIAGONAL_TOP_LEFT_BOTTOM_RIGHT);
      tileTypes.put(asList(true, true, true, false, true, true, true, false), WATER_DIAGONAL_BOTTOM_LEFT_TOP_RIGHT);
      
      tileTypes.put(asList(true, true, true, false, false, false, true, true), HORIZONTAL_GRASS_BOTTOM);
      tileTypes.put(asList(true, true, true, true, false, false, true, true), HORIZONTAL_GRASS_BOTTOM);
      tileTypes.put(asList(true, true, true, false, false, true, true, true), HORIZONTAL_GRASS_BOTTOM);
      tileTypes.put(asList(true, true, true, true, false, true, true, true), HORIZONTAL_GRASS_BOTTOM);
      
      tileTypes.put(asList(false, false, true, true, true, true, true, false), HORIZONTAL_GRASS_TOP);
      tileTypes.put(asList(false, false, true, true, true, true, true, true), HORIZONTAL_GRASS_TOP);
      tileTypes.put(asList(false, true, true, true, true, true, true, false), HORIZONTAL_GRASS_TOP);
      tileTypes.put(asList(false, true, true, true, true, true, true, true), HORIZONTAL_GRASS_TOP);
      
      tileTypes.put(asList(true, true, true, true, true, false, false, false), VERTICAL_GRASS_LEFT);
      tileTypes.put(asList(true, true, true, true, true, true, false, false), VERTICAL_GRASS_LEFT);
      tileTypes.put(asList(true, true, true, true, true, false, false, true), VERTICAL_GRASS_LEFT);
      tileTypes.put(asList(true, true, true, true, true, true, false, true), VERTICAL_GRASS_LEFT);
      
      tileTypes.put(asList(true, false, false, false, true, true, true, true), VERTICAL_GRASS_RIGHT);
      tileTypes.put(asList(true, false, false, true, true, true, true, true), VERTICAL_GRASS_RIGHT);
      tileTypes.put(asList(true, true, false, false, true, true, true, true), VERTICAL_GRASS_RIGHT);
      tileTypes.put(asList(true, true, false, true, true, true, true, true), VERTICAL_GRASS_RIGHT);
      
      tileTypes.put(asList(true, true, true, false, false, false, false, false), WATER_TOP_RIGHT_CORNER);
      tileTypes.put(asList(true, true, true, true, false, false, false, false), WATER_TOP_RIGHT_CORNER);
      tileTypes.put(asList(true, true, true, false, false, false, false, true), WATER_TOP_RIGHT_CORNER);
      tileTypes.put(asList(true, true, true, true, false, false, false, true), WATER_TOP_RIGHT_CORNER);
      
      tileTypes.put(asList(false, false, true, true, true, false, false, false), WATER_BOTTOM_RIGHT_CORNER);
      tileTypes.put(asList(false, true, true, true, true, false, false, false), WATER_BOTTOM_RIGHT_CORNER);
      tileTypes.put(asList(false, false, true, true, true, true, false, false), WATER_BOTTOM_RIGHT_CORNER);
      tileTypes.put(asList(false, true, true, true, true, true, false, false), WATER_BOTTOM_RIGHT_CORNER);
      
      tileTypes.put(asList(false, false, false, false, true, true, true, false), WATER_BOTTOM_LEFT_CORNER);
      tileTypes.put(asList(false, false, false, true, true, true, true, false), WATER_BOTTOM_LEFT_CORNER);
      tileTypes.put(asList(false, false, false, false, true, true, true, true), WATER_BOTTOM_LEFT_CORNER);
      tileTypes.put(asList(false, false, false, true, true, true, true, true), WATER_BOTTOM_LEFT_CORNER);
      
      tileTypes.put(asList(true, false, false, false, false, false, true, true), WATER_TOP_LEFT_CORNER);
      tileTypes.put(asList(true, true, false, false, false, false, true, true), WATER_TOP_LEFT_CORNER);
      tileTypes.put(asList(true, false, false, false, false, true, true, true), WATER_TOP_LEFT_CORNER);
      tileTypes.put(asList(true, true, false, false, false, true, true, true), WATER_TOP_LEFT_CORNER);
      
      tileTypes.put(asList(true, false, true, true, true, true, true, true), GRASS_TOP_RIGHT_CORNER);
      tileTypes.put(asList(true, true, true, false, true, true, true, true), GRASS_BOTTOM_RIGHT_CORNER);
      tileTypes.put(asList(true, true, true, true, true, false, true, true), GRASS_BOTTOM_LEFT_CORNER);
      tileTypes.put(asList(true, true, true, true, true, true, true, false), GRASS_TOP_LEFT_CORNER);
      
   }
   
   public WaterTileType calculateTileType(WorldLocation worldLocation) {
      WorldLocation above = worldLocation.translate(0, -1, world);
      WorldLocation topRight = worldLocation.translate(1, -1, world);
      WorldLocation right = worldLocation.translate(1, 0, world);
      WorldLocation bottomRight = worldLocation.translate(1, 1, world);
      WorldLocation bottom = worldLocation.translate(0, 1, world);
      WorldLocation bottomLeft = worldLocation.translate(-1, 1, world);
      WorldLocation left = worldLocation.translate(-1, 0, world);
      WorldLocation topLeft = worldLocation.translate(-1, -1, world);
      
      boolean containsAbove = water.getCoverage().contains(above);
      boolean containsTopRight = water.getCoverage().contains(topRight);
      boolean containsRight = water.getCoverage().contains(right);
      boolean containsBottomRight = water.getCoverage().contains(bottomLeft);
      boolean containsBottom = water.getCoverage().contains(bottom);
      boolean containsBottomLeft = water.getCoverage().contains(bottomRight);
      boolean containsLeft = water.getCoverage().contains(left);
      boolean containsTopLeft = water.getCoverage().contains(topLeft);
      
      WaterTileType waterTileType = tileTypes.get(asList(
            containsAbove, containsTopRight, containsRight, containsBottomLeft, containsBottom, containsBottomRight, containsLeft, containsTopLeft));
      if ( waterTileType == null ) {
         return SURROUNDED;
      } else {
         return waterTileType;
      }
   }
}
