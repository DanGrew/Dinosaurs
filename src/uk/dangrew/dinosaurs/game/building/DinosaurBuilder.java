
package uk.dangrew.dinosaurs.game.building;

import uk.dangrew.dinosaurs.game.ai.RoamingBehaviour;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.storage.DinosaurStore;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Provides easily building of dinosaurs in the world.
 */
public class DinosaurBuilder {
   
   private final World world;
   private final DinosaurStore dinosaurStore;
   
   public DinosaurBuilder(DinosaurStore dinosaurStore, World world) {
      this.dinosaurStore = dinosaurStore;
      this.world = world;
   }
   
   public DinosaurBuilder buildHerbivoreRoamer(String name, WorldLocation initialLocation) {
      Dinosaur dinosaur = dinosaurStore.createConcept(name);
      dinosaur.getWorldLocation().set(initialLocation);
      dinosaur.getBehaviour().set(new RoamingBehaviour(dinosaur, world));
      return this;
   }
}
