package uk.dangrew.dinosaurs.game.mechanism;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;

/**
 * Calculates all possible actions available to the player per turn.
 */
public class ActionProcessor {
   
   private final World world;
   private final Dinosaur dinosaur;
   
   public ActionProcessor(World world, Dinosaur dinosaur){
      this.world = world;
      this.dinosaur = dinosaur;
   }
   
   public Collection<GameAction> calculateAvailableActions(){
      Set<GameAction> possibleActions = new LinkedHashSet<>();
      
      for(Movement movement : Movement.values()) {
         possibleActions.add(new MoveAction(world, dinosaur, movement));
      }
      
      return possibleActions;
   }
}