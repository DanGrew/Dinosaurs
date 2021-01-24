
package uk.dangrew.dinosaurs.game.actions.consumption;

import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;

/**
 * Action that results in the dinosaur satifsying some hunger by eating from a tree.
 */
public class EatAction implements GameAction {

   static final String EAT_JUICY_LEAVES_AVAILABLE = ": Eat! Juicy leaves available!";
   static final String NOTHING_TO_EAT_TREE_S_LOOKING_A_BIT_BARE = ": Nothing to eat... Tree's looking a bit bare...";
   static final String NOT_HUNGRY = ": Not hungry...";
   
   static final int MAXIMUM_FEED = 30;

   private final Tree tree;
   private final Dinosaur dinosaur;
   
   public EatAction(Tree tree, Dinosaur dinosaur) {
      this.tree = tree;
      this.dinosaur = dinosaur;
   }
   
   @Override
   public boolean isAvailable() {
      return tree.foodAvailable().get() > 0 && dinosaur.hunger().get() > 0;
   }
   
   @Override
   public String describe() {
      if (dinosaur.hunger().get() > 0) {
         if (tree.foodAvailable().get() > 0) {
            return tree.getWorldLocation().wrapedCoordinates() + EAT_JUICY_LEAVES_AVAILABLE;
         } else {
            return tree.getWorldLocation().wrapedCoordinates() + NOTHING_TO_EAT_TREE_S_LOOKING_A_BIT_BARE;
         }
      } else {
         return tree.getWorldLocation().wrapedCoordinates() + NOT_HUNGRY;
      }
   }
   
   @Override
   public void performAction() {
      int foodWanted = Math.min(dinosaur.hunger().get(), MAXIMUM_FEED);
      int foodThatCanBeConsumed = Math.min(tree.foodAvailable().get(), foodWanted);
      tree.foodAvailable().setValue(tree.foodAvailable().get() - foodThatCanBeConsumed);
      dinosaur.hunger().setValue(dinosaur.hunger().get() - foodThatCanBeConsumed);
   }
}
