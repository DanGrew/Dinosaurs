
package uk.dangrew.dinosaurs.game.actions;

import java.util.function.BiFunction;

import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Types of movement permitted by the player.
 */
public enum Movement {
   
   UP(
         (l, w) -> l.translate(0, -1, w)
   ),
   RIGHT(
         (l, w) -> l.translate(1, 0, w)
   ),
   DOWN(
         (l, w) -> l.translate(0, 1, w)
   ),
   LEFT(
         (l, w) -> l.translate(-1, 0, w)
   );
   
   private final BiFunction<WorldLocation, World, WorldLocation> rawMoverFunction;
   
   private Movement(BiFunction<WorldLocation, World, WorldLocation> rawMoverFunction) {
      this.rawMoverFunction = rawMoverFunction;
   }
   
   public WorldLocation move(WorldLocation worldLocation, World world) {
      return rawMoverFunction.apply(worldLocation, world);
   }
}
