package uk.dangrew.dinosaurs.game.actions.mechanism;

import java.util.Collection;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;

/**
 * Interface providing commonality between assets that generate actions for the player to use.
 */
public interface ActionGenerator {
   
   public Collection<GameAction> generateActions(Dinosaur dinosaur, World world);
}