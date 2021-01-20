
package uk.dangrew.dinosaurs.game.algorithm.shortestpath;

import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Implementation of {@link WorldTraversal} that permits all movements.
 */
public class AllMovementsPermitted implements WorldTraversal {
   
   @Override
   public boolean canTraverse(WorldLocation currentLocation, WorldLocation locationToMoveTo) {
      return true;
   }
}
