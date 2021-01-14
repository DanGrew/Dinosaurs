package uk.dangrew.dinosaurs.game.mechanism;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import uk.dangrew.dinosaurs.game.actions.GameAction;
import uk.dangrew.dinosaurs.game.actions.MoveAction;
import uk.dangrew.dinosaurs.game.actions.Movement;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;

/**
 * Calculates all possible actions available to the player per turn.
 */
public class ActionProcessor {
   
   private final DinosaursConfiguration dinosaursConfiguration;
   
   public ActionProcessor(DinosaursConfiguration dinosaursConfiguration){
      this.dinosaursConfiguration = dinosaursConfiguration;
   }
   
   public Collection<GameAction> calculateAvailableActions(){
      Set<GameAction> possibleActions = new LinkedHashSet<>();

      World currentWorld = dinosaursConfiguration.currentWorld().get();
      Dinosaur currentPlayer = dinosaursConfiguration.currentPlayer().get();
      
      if ( currentPlayer == null || currentPlayer == null ) {
         throw new IllegalStateException("Must have world and player configured.");
      }
      
      for(Movement movement : Movement.values()) {
         possibleActions.add(new MoveAction(currentWorld, currentPlayer, movement));
      }
      
      return possibleActions;
   }
}