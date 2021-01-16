
package uk.dangrew.dinosaurs.resources.worlds;

import static java.util.Arrays.asList;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.GRASS_BOTTOM_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.GRASS_BOTTOM_RIGHT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.GRASS_TOP_RIGHT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.HORIZONTAL_GRASS_TOP;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.SURROUNDED;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.VERTICAL_GRASS_LEFT;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.VERTICAL_GRASS_RIGHT;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_BOTTOM_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_BOTTOM_RIGHT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_TOP_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_TOP_RIGHT_CORNER;

import uk.dangrew.dinosaurs.game.mechanism.AssetManager;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.model.water.WaterLocationProperties;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class World1Assets {
   
   private final AssetManager assetManager;
   
   private Dinosaur player;
   private World world;
   
   public World1Assets(AssetManager assetManager) {
      this.assetManager = assetManager;
   }
   
   public void build() {
      this.world = assetManager.getWorldStore().createConcept("Home World");
      this.world.setDimension(30, 30);
      
      Water water = assetManager.getWaterStore().createConcept("My First Pond");
      asList(
            new WorldLocation(16, 5),
            new WorldLocation(16, 6),
            new WorldLocation(17, 5),
            new WorldLocation(17, 6),
            new WorldLocation(17, 7),
            new WorldLocation(17, 8),
            new WorldLocation(18, 7),
            new WorldLocation(18, 8))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(1, SURROUNDED)));
      asList(
            new WorldLocation(16, 4),
            new WorldLocation(17, 4))
            .forEach(location -> water.cover(location, new WaterLocationProperties(0, HORIZONTAL_GRASS_TOP)));
      asList(
            new WorldLocation(16, 8),
            new WorldLocation(15, 5),
            new WorldLocation(15, 6))
            .forEach(location -> water.cover(location, new WaterLocationProperties(0, VERTICAL_GRASS_LEFT)));
      asList(
            new WorldLocation(19, 7),
            new WorldLocation(19, 8),
            new WorldLocation(18, 5))
            .forEach(location -> water.cover(location, new WaterLocationProperties(0, VERTICAL_GRASS_RIGHT)));
      asList(
            new WorldLocation(15, 4))
            .forEach(location -> water.cover(location, new WaterLocationProperties(0, WATER_BOTTOM_RIGHT_CORNER)));
      asList(
            new WorldLocation(19, 6),
            new WorldLocation(18, 4))
            .forEach(location -> water.cover(location, new WaterLocationProperties(0, WATER_BOTTOM_LEFT_CORNER)));
      asList(
            new WorldLocation(18, 10),
            new WorldLocation(19, 9))
            .forEach(location -> water.cover(location, new WaterLocationProperties(0, WATER_TOP_LEFT_CORNER)));
      asList(
            new WorldLocation(16, 9),
            new WorldLocation(17, 10),
            new WorldLocation(15, 7))
            .forEach(location -> water.cover(location, new WaterLocationProperties(0, WATER_TOP_RIGHT_CORNER)));
      asList(
            new WorldLocation(18, 6))
            .forEach(location -> water.cover(location, new WaterLocationProperties(0, GRASS_TOP_RIGHT_CORNER)));
      asList(
            new WorldLocation(18, 9))
            .forEach(location -> water.cover(location, new WaterLocationProperties(0, GRASS_BOTTOM_RIGHT_CORNER)));
      asList(
            new WorldLocation(16, 7),
            new WorldLocation(17, 9))
            .forEach(location -> water.cover(location, new WaterLocationProperties(0, GRASS_BOTTOM_LEFT_CORNER)));
      
      
      this.player = assetManager.getDinosaurStore().createConcept("Steggy");
      player.getWorldLocation().set(new WorldLocation(10, 10));
   }
   
   public Dinosaur getPlayer() {
      return player;
   }
   
   public World getWorld() {
      return world;
   }
}
