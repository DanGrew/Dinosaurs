
package uk.dangrew.dinosaurs.game.model.dinosaur;

import static java.util.Collections.unmodifiableCollection;

import java.util.Collection;
import java.util.HashSet;

import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Represents the area around the dinosaur that it can interact with other assets in the world.
 */
public class InteractionZone {
   
   private final Dinosaur dinosaur;
   private final int range;
   private final Collection<WorldLocation> interactionZone;
   
   public InteractionZone(Dinosaur dinosaur, int range) {
      this.interactionZone = new HashSet<>();
      this.dinosaur = dinosaur;
      this.range = range;
   }
   
   public Collection<WorldLocation> calculateInteractionZone(World world) {
      interactionZone.clear();
      
      WorldLocation topLeft = dinosaur.expectLocation().translate(-range, -range, world);
      int zoneDimension = range * 2 + 1;
      
      for (int i = 0; i < zoneDimension; i++) {
         for (int j = 0; j < zoneDimension; j++) {
            interactionZone.add(topLeft.translate(i, j, world));
         }
      }
      
      return unmodifiableCollection(interactionZone);
   }
   
   public int getRange() {
      return range;
   }
}
