
package uk.dangrew.dinosaurs.game.actions.consumption;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import uk.dangrew.dinosaurs.game.actions.mechanism.ActionGenerator;
import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * {@link ActionGenerator} for drinking from water in a dinosaurs interaction zone.
 */
public class DrinkActionGenerator implements ActionGenerator {
   
   private final Water water;
   
   public DrinkActionGenerator(Water water) {
      this.water = water;
   }
   
   @Override
   public Collection<GameAction> generateActions(Dinosaur dinosaur, World world) {
      List<WorldLocation> locationsInInteractionZone = dinosaur.getInteractionZone()
            .calculateInteractionZone(world).stream()
            .filter(water.getCoverage()::contains)
            .collect(Collectors.toList());
      if (locationsInInteractionZone.isEmpty()) {
         return emptyList();
      }
      
      return singleton(new DrinkAction(water, locationsInInteractionZone, dinosaur));
   }
}
