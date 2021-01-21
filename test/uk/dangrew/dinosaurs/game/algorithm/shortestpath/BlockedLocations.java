package uk.dangrew.dinosaurs.game.algorithm.shortestpath;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.Set;

import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Collects locations that cannot be traversed and implements {@link WorldTraversal} to constrain the solution.
 */
public class BlockedLocations implements WorldTraversal{

   private final Set<WorldLocation> blocked;
   
   public BlockedLocations(WorldLocation... blockedLocations){
      this.blocked = new HashSet<>(asList(blockedLocations));
   }
   
   @Override
   public boolean canTraverse(
         WorldLocation currentLocation, WorldLocation locationToMoveTo
   ) {
      return !blocked.contains(locationToMoveTo);
   }
}