package uk.dangrew.dinosaurs.game.collision;

import java.util.Collection;

import uk.dangrew.dinosaurs.game.algorithm.shortestpath.WorldTraversal;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Implementation of {@link WorldTraversal} that uses {@link CollisionDetection} to deduce valid movements.
 */
public class WorldTraversalCollisionDetector implements WorldTraversal {
   
   private final Dinosaur dinosaur;
   private final CollisionDetection collisionDetection;
   
   public WorldTraversalCollisionDetector(Dinosaur dinosaur, CollisionDetection collisionDetection){
      this.dinosaur = dinosaur;
      this.collisionDetection = collisionDetection;
   }
   
   @Override
   public boolean canTraverse(
         WorldLocation currentLocation, WorldLocation locationToMoveTo
   ) {
      Collection<Collision> collisions = collisionDetection.calculateCollisions(dinosaur, locationToMoveTo);
      return collisions.isEmpty();
   }
}