package uk.dangrew.dinosaurs.game.mechanism;

import static java.util.Arrays.asList;

import java.util.Collection;

import uk.dangrew.dinosaurs.game.collision.CollisionDetection;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.storage.Asset;
import uk.dangrew.dinosaurs.game.storage.AssetStore;
import uk.dangrew.dinosaurs.game.storage.DinosaurStore;
import uk.dangrew.dinosaurs.game.storage.RockStore;
import uk.dangrew.dinosaurs.game.storage.TreeStore;
import uk.dangrew.dinosaurs.game.storage.WaterStore;
import uk.dangrew.dinosaurs.game.storage.WorldStore;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.resources.worlds.World1Assets;

/**
 * Manages all of the different types of assets in the game.
 */
public class AssetManager {

   private final WorldStore worldStore;
   private final DinosaurStore dinosaurStore;
   private final WaterStore waterStore;
   private final TreeStore treeStore;
   private final RockStore rockStore;
   private final CollisionDetection collisionDetection;
   
   private final World1Assets world1Assets;
   
   public AssetManager(){
      this.worldStore = new WorldStore();
      this.dinosaurStore = new DinosaurStore();
      this.waterStore = new WaterStore();
      this.treeStore = new TreeStore();
      this.rockStore = new RockStore();
      this.collisionDetection = new CollisionDetection(getCollidableAssetStores());
      this.world1Assets = new World1Assets(this);
   }
   
   public void buildWorld1(){
      world1Assets.build();
   }

   public World getWorld() {
      return world1Assets.getWorld();
   }

   public Dinosaur getPlayer() {
      return world1Assets.getPlayer();
   }

   public WorldStore getWorldStore() {
      return worldStore;
   }

   public TreeStore getTreeStore() {
      return treeStore;
   }

   public DinosaurStore getDinosaurStore() {
      return dinosaurStore;
   }

   public WaterStore getWaterStore() {
      return waterStore;
   }

   public RockStore getRockStore() {
      return rockStore;
   }

   public CollisionDetection getCollisionDetection() {
      return collisionDetection;
   }

   public Collection<AssetStore<? extends Asset, ?>> getCollidableAssetStores(){
      return asList(waterStore, treeStore, rockStore);
   }

}