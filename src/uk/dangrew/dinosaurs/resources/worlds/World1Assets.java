
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

import uk.dangrew.dinosaurs.game.building.TreeBuilder;
import uk.dangrew.dinosaurs.game.building.WaterBuilder;
import uk.dangrew.dinosaurs.game.mechanism.AssetManager;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
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
            new WorldLocation(16, 5, world),
            new WorldLocation(16, 6, world),
            new WorldLocation(17, 5, world),
            new WorldLocation(17, 6, world),
            new WorldLocation(17, 7, world),
            new WorldLocation(17, 8, world),
            new WorldLocation(18, 7, world),
            new WorldLocation(18, 8, world))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(1, SURROUNDED)));
      asList(
            new WorldLocation(16, 4, world),
            new WorldLocation(17, 4, world))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(0, HORIZONTAL_GRASS_TOP)));
      asList(
            new WorldLocation(16, 8, world),
            new WorldLocation(15, 5, world),
            new WorldLocation(15, 6, world))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(0, VERTICAL_GRASS_LEFT)));
      asList(
            new WorldLocation(19, 7, world),
            new WorldLocation(19, 8, world),
            new WorldLocation(18, 5, world))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(0, VERTICAL_GRASS_RIGHT)));
      asList(
            new WorldLocation(15, 4, world))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(0, WATER_BOTTOM_RIGHT_CORNER)));
      asList(
            new WorldLocation(19, 6, world),
            new WorldLocation(18, 4, world))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(0, WATER_BOTTOM_LEFT_CORNER)));
      asList(
            new WorldLocation(18, 10, world),
            new WorldLocation(19, 9, world))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(0, WATER_TOP_LEFT_CORNER)));
      asList(
            new WorldLocation(16, 9, world),
            new WorldLocation(17, 10, world),
            new WorldLocation(15, 7, world))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(0, WATER_TOP_RIGHT_CORNER)));
      asList(
            new WorldLocation(18, 6, world))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(0, GRASS_TOP_RIGHT_CORNER)));
      asList(
            new WorldLocation(18, 9, world))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(0, GRASS_BOTTOM_RIGHT_CORNER)));
      asList(
            new WorldLocation(16, 7, world),
            new WorldLocation(17, 9, world))
                  .forEach(location -> water.cover(location, new WaterLocationProperties(0, GRASS_BOTTOM_LEFT_CORNER)));
      
      Water generated = assetManager.getWaterStore().createConcept("Generated");
      new WaterBuilder(generated, world)
            .coverRectangle(new WorldLocation(2, 2, world), 6, 7)
            .coverRectangle(new WorldLocation(7, 4, world), 3, 3)
            .coverRectangle(new WorldLocation(7, 8, world), 3, 3)
            .append(new WorldLocation(8, 3, world))
            .subtract(new WorldLocation(2, 8, world))
            .build();
      
      this.player = assetManager.getDinosaurStore().createConcept("Steggy");
      player.getWorldLocation().set(new WorldLocation(10, 10, world));
      
      Tree tree = assetManager.getTreeStore().createConcept("Tree");
      tree.setWorldLocation(new WorldLocation(10, 2, world));
      tree.setHeight(2);
      
      new TreeBuilder(world, assetManager.getTreeStore())
            .straightLine(new WorldLocation(2, 2, world), 0, 1, 5)
            .straightLine(new WorldLocation(7, 2, world), 1, 1, 3);
   }
   
   public Dinosaur getPlayer() {
      return player;
   }
   
   public World getWorld() {
      return world;
   }
}
