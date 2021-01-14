package uk.dangrew.dinosaurs.game.actions;

import java.util.function.Function;

import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Types of movement permitted by the player.
 */
public enum Movement {
   
   UP(
         l -> new WorldLocation(l.getHorizontal(), l.getVertical() - 1)
   ),
   RIGHT(
         l -> new WorldLocation(l.getHorizontal() + 1, l.getVertical())
   ),
   DOWN(
         l -> new WorldLocation(l.getHorizontal(), l.getVertical() + 1)
   ),
   LEFT(
         l -> new WorldLocation(l.getHorizontal() - 1, l.getVertical())
   );
   
   private final Function<WorldLocation, WorldLocation> rawMoverFunction;
   
   private Movement(Function<WorldLocation, WorldLocation> rawMoverFunction){
      this.rawMoverFunction = rawMoverFunction;
   }
   
   public WorldLocation rawMove(WorldLocation worldLocation){
      return rawMoverFunction.apply(worldLocation);
   }
}