
package uk.dangrew.dinosaurs.resources.worlds;

import uk.dangrew.dinosaurs.game.behaviour.consumption.ConsumptionBehaviour;
import uk.dangrew.dinosaurs.game.building.DinosaurBuilder;
import uk.dangrew.dinosaurs.game.building.RockBuilder;
import uk.dangrew.dinosaurs.game.building.TreeBuilder;
import uk.dangrew.dinosaurs.game.building.WaterBuilder;
import uk.dangrew.dinosaurs.game.mechanism.AssetManager;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.rubble.Rock;
import uk.dangrew.dinosaurs.game.model.water.Water;
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
      this.world.setDimension(20, 20);
      
      Water large_middle_lake = assetManager.getWaterStore().createConcept("Large Middle Lake");
      new WaterBuilder(large_middle_lake, world)
            .coverRectangle(new WorldLocation(5, 6, world), 6, 8)
            .coverRectangle(new WorldLocation(3, 5, world), 3, 3)
            .coverRectangle(new WorldLocation(3, 12, world), 3, 3)
            .coverRectangle(new WorldLocation(4, 9, world), 2, 2)
            .build();
      
      Rock test = assetManager.getRockStore().createConcept("Test");
      new RockBuilder(test, world)
            .coverTriangle(new WorldLocation(10, 7, world), 3, true, false)
            .coverTriangle(new WorldLocation(10, 12, world), 3, true, true)
            .coverRectangle(new WorldLocation(10, 8, world), 4, 4);
      
      this.player = assetManager.getDinosaurStore().createConcept("Steggy");
      player.getWorldLocation().set(new WorldLocation(0, 0, world));
      player.getBehaviour().set(new ConsumptionBehaviour(player));
      
      new DinosaurBuilder(assetManager.getDinosaurStore(), world, assetManager.getCollisionDetection())
            .buildHerbivoreRoamer("Herby", new WorldLocation(5, 2, world))
            .buildHerbivoreRoamer("BigTooth", new WorldLocation(6, 2, world))
            .buildHerbivoreRoamer("Littling", new WorldLocation(17, 8, world))
            .buildHerbivoreRoamer("Deano", new WorldLocation(18, 7, world))
            .buildHerbivoreRoamer("Balthazah", new WorldLocation(19, 9, world))
            .buildHerbivoreRoamer("Dave", new WorldLocation(2, 10, world));
      
      new TreeBuilder(assetManager.getTreeStore(), world)
            .straightLine(new WorldLocation(14, 18, world), 2, 0, 3)
            .straightLine(new WorldLocation(13, 19, world), 2, 0, 4)
            .straightLine(new WorldLocation(14, 0, world), 2, 0, 3)
            .straightLine(new WorldLocation(13, 1, world), 2, 0, 4)
            .straightLine(new WorldLocation(14, 2, world), 2, 0, 3)
            .straightLine(new WorldLocation(13, 3, world), 2, 0, 4)
            
            .straightLine(new WorldLocation(3, 5, world), 1, 0, 3)
            .straightLine(new WorldLocation(3, 5, world), 0, 1, 3)
            
            .straightLine(new WorldLocation(3, 12, world), 0, 1, 3)
            .straightLine(new WorldLocation(3, 14, world), 1, 0, 3);
   }
   
   public Dinosaur getPlayer() {
      return player;
   }
   
   public World getWorld() {
      return world;
   }
}
