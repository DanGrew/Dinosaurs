package uk.dangrew.dinosaurs.game.algorithm.shortestpath;

import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Session for a search, providing the origin destination and constraints on movement.
 */
public class SearchSession {
   
   private final WorldLocation origin;
   private final WorldLocation destination;
   private final WorldTraversal worldTraversal;

   public SearchSession(WorldLocation origin, WorldLocation destination){
      this(origin, destination, new AllMovementsPermitted());
   }
   
   public SearchSession(WorldLocation origin, WorldLocation destination, WorldTraversal worldTraversal) {
      this.origin = origin;
      this.destination = destination;
      this.worldTraversal = worldTraversal;
   }

   public WorldLocation origin() {
      return origin;
   }

   public WorldLocation destination() {
      return destination;
   }

   public WorldTraversal worldTraversal() {
      return worldTraversal;
   }
}