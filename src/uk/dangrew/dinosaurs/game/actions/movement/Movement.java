
package uk.dangrew.dinosaurs.game.actions.movement;

import java.util.function.BiFunction;

import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Types of movement permitted by the player.
 */
public enum Movement {
   
   UP(
         "Up",
         (l, w) -> l.translate(0, -1, w)
   ),
   RIGHT(
         "Right",
         (l, w) -> l.translate(1, 0, w)
   ),
   DOWN(
         "Down",
         (l, w) -> l.translate(0, 1, w)
   ),
   LEFT(
         "Left",
         (l, w) -> l.translate(-1, 0, w)
   );
   
   private final String displayName;
   private final BiFunction<WorldLocation, World, WorldLocation> rawMoverFunction;
   
   private Movement(String displayName, BiFunction<WorldLocation, World, WorldLocation> rawMoverFunction) {
      this.displayName = displayName;
      this.rawMoverFunction = rawMoverFunction;
   }
   
   public WorldLocation move(WorldLocation worldLocation, World world) {
      return rawMoverFunction.apply(worldLocation, world);
   }

   public String displayName() {
      return displayName;
   }
}
