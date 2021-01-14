package uk.dangrew.dinosaurs.resources.worlds;

import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.GRASS_BOTTOM_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.GRASS_TOP_RIGHT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.HORIZONTAL_GRASS_BOTTOM;
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
   
   public void build(){
      this.world = assetManager.getWorldStore().createConcept("Home World");

      Water water = assetManager.getWaterStore().createConcept("My First Pond");
      water.cover(new WorldLocation(16, 7), new WaterLocationProperties(0, WATER_BOTTOM_RIGHT_CORNER));
      water.cover(new WorldLocation(16, 8), new WaterLocationProperties(1, VERTICAL_GRASS_LEFT));
      water.cover(new WorldLocation(16, 9), new WaterLocationProperties(0, WATER_TOP_RIGHT_CORNER));

      water.cover(new WorldLocation(17, 7), new WaterLocationProperties(1, HORIZONTAL_GRASS_TOP));
      water.cover(new WorldLocation(17, 8), new WaterLocationProperties(2, SURROUNDED));
      water.cover(new WorldLocation(17, 9), new WaterLocationProperties(1, GRASS_BOTTOM_LEFT_CORNER));

      water.cover(new WorldLocation(18, 7), new WaterLocationProperties(0, WATER_BOTTOM_LEFT_CORNER));
      water.cover(new WorldLocation(18, 8),new WaterLocationProperties( 1, VERTICAL_GRASS_RIGHT));
      water.cover(new WorldLocation(18, 9), new WaterLocationProperties(0, VERTICAL_GRASS_RIGHT));

      water.cover(new WorldLocation(17, 10),new WaterLocationProperties( 1, VERTICAL_GRASS_LEFT));
      water.cover(new WorldLocation(17, 11), new WaterLocationProperties(2, WATER_TOP_RIGHT_CORNER));

      water.cover(new WorldLocation(18, 10), new WaterLocationProperties(1, GRASS_TOP_RIGHT_CORNER));
      water.cover(new WorldLocation(18, 11), new WaterLocationProperties(0, HORIZONTAL_GRASS_BOTTOM));

      water.cover(new WorldLocation(19, 10),new WaterLocationProperties( 1, WATER_BOTTOM_LEFT_CORNER));
      water.cover(new WorldLocation(19, 11),new WaterLocationProperties( 0, WATER_TOP_LEFT_CORNER));
      
      this.player = assetManager.getDinosaurStore().createConcept("Steggy");
      player.getWorldLocation().set(new WorldLocation(10, 10));
   }
   
   public Dinosaur getPlayer(){
      return player;
   }
   
   public World getWorld(){
      return world;
   }
}