package uk.dangrew.dinosaurs.game.mechanism;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.storage.DinosaurStore;
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
   
   private final World1Assets world1Assets;
   
   public AssetManager(){
      this.worldStore = new WorldStore();
      this.dinosaurStore = new DinosaurStore();
      this.waterStore = new WaterStore();
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

   public DinosaurStore getDinosaurStore() {
      return dinosaurStore;
   }

   public WaterStore getWaterStore() {
      return waterStore;
   }

}