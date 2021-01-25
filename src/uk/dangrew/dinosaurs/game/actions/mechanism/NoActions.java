
package uk.dangrew.dinosaurs.game.actions.mechanism;

import java.util.Collection;
import java.util.Collections;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;

/**
 * Basic implementation to satisfy the interface but provide no actions.
 */
public class NoActions implements ActionGenerator {
   
   @Override
   public Collection<GameAction> generateActions(Dinosaur dinosaur, World world) {
      return Collections.emptyList();
   }
}
