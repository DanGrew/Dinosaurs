package uk.dangrew.dinosaurs.game.algorithm.shortestpath;

import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Interface for providing constraints on movements when performing a search.
 */
public interface WorldTraversal {
   
   public boolean canTraverse(WorldLocation currentLocation, WorldLocation locationToMoveTo);
}
