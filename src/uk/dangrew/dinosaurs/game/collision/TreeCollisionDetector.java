package uk.dangrew.dinosaurs.game.collision;

import java.util.Optional;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * {@link CollisionDetector} for trees.
 */
public class TreeCollisionDetector implements CollisionDetector {
   
   private final Tree tree;
   
   public TreeCollisionDetector(Tree tree){
      this.tree = tree;
   }
   
   @Override
   public Optional<Collision> canLocationBeOccupied(Dinosaur dinosaur, WorldLocation newLocation){
      if (!tree.getWorldLocation().equals(newLocation)){
         return Optional.empty();
      }

      if (dinosaur.getHeight() < tree.getHeight()){
         return Optional.empty();
      } else {
         return Optional.of(new Collision("Too big to move through trees!"));
      }
   }
}