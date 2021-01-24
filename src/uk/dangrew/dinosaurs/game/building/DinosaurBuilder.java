
package uk.dangrew.dinosaurs.game.building;

import uk.dangrew.dinosaurs.game.behaviour.roaming.RoamingBehaviour;
import uk.dangrew.dinosaurs.game.collision.CollisionDetection;
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
   private final CollisionDetection collisionDetection;
   
   public DinosaurBuilder(DinosaurStore dinosaurStore, World world, CollisionDetection collisionDetection) {
      this.dinosaurStore = dinosaurStore;
      this.world = world;
      this.collisionDetection = collisionDetection;
   }
   
   public DinosaurBuilder buildHerbivoreRoamer(String name, WorldLocation initialLocation) {
      Dinosaur dinosaur = dinosaurStore.createConcept(name);
      dinosaur.getWorldLocation().set(initialLocation);
      dinosaur.getBehaviour().set(new RoamingBehaviour(dinosaur, world, collisionDetection));
      return this;
   }
}
