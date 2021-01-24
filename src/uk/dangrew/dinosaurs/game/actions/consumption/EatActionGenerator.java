
package uk.dangrew.dinosaurs.game.actions.consumption;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;

import java.util.Collection;

import uk.dangrew.dinosaurs.game.actions.mechanism.ActionGenerator;
import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;

/**
 * {@link ActionGenerator} for eating from trees in a dinosaurs interaction zone.
 */
public class EatActionGenerator implements ActionGenerator {
   
   private final Tree tree;
   
   public EatActionGenerator(Tree tree) {
      this.tree = tree;
   }
   
   @Override
   public Collection<GameAction> generateActions(Dinosaur dinosaur) {
      if (dinosaur.getInteractionZone().contains(tree.getWorldLocation())) {
         return singleton(new EatAction(tree, dinosaur));
      }
      
      return emptyList();
   }
}
