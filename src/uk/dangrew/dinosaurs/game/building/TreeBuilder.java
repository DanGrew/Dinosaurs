package uk.dangrew.dinosaurs.game.building;

import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.storage.TreeStore;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Provides a building method for trees in the world that saves a lot of computation and code.
 */
public class TreeBuilder {
   
   private final World world;
   private final TreeStore treeStore;
   
   public TreeBuilder(World world, TreeStore treeStore){
      this.world = world;
      this.treeStore = treeStore;
   }
   
   public TreeBuilder straightLine(WorldLocation fromLocation, int horizontalStep, int verticalStep, int amount){
      for(int count = 0; count < amount; count++) {
         WorldLocation location = fromLocation.translate(count * horizontalStep, count * verticalStep, world);
         Tree tree = treeStore.createConcept("Tree@" + location.toString());
         tree.setWorldLocation(location);
      }
      return this;
   }
}