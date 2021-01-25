
package uk.dangrew.dinosaurs.game.actions.consumption;

import java.util.Collection;
import java.util.stream.Collectors;

import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Action that results in the dinosaur satifsying some thirst by drinking from {@link Water}.
 */
public class DrinkAction implements GameAction {
   
   static final String WATER_AVAILABLE = ": Drink! Top quality natural mineral water!";
   static final String NOTHING_TO_DRINK = ": Nothing to drink... Think we're having a drought...";
   static final String NOT_THIRSTY = ": Not thirsty...";
   
   static final int MAXIMUM_DRINK = 30;
   
   private final Water water;
   private final Collection<WorldLocation> waterLocation;
   private final Dinosaur dinosaur;
   
   public DrinkAction(Water water, Collection<WorldLocation> waterLocation, Dinosaur dinosaur) {
      this.water = water;
      this.waterLocation = waterLocation;
      this.dinosaur = dinosaur;
   }
   
   @Override
   public boolean isAvailable() {
      return water.waterAvailable().get() > 0 && dinosaur.thirst().get() > 0;
   }
   
   @Override
   public String describe() {
      String waterLocations = waterLocation.stream()
            .map(WorldLocation::wrapedCoordinates)
            .collect(Collectors.joining(", "));
      
      if (dinosaur.thirst().get() > 0) {
         if (water.waterAvailable().get() > 0) {
            return describe(waterLocations, WATER_AVAILABLE);
         } else {
            return describe(waterLocations, NOTHING_TO_DRINK);
         }
      } else {
         return describe(waterLocations, NOT_THIRSTY);
      }
   }
   
   @Override
   public void performAction() {
      int waterWanted = Math.min(dinosaur.thirst().get(), MAXIMUM_DRINK);
      int waterThatCanBeConsumed = Math.min(water.waterAvailable().get(), waterWanted);
      water.waterAvailable().setValue(water.waterAvailable().get() - waterThatCanBeConsumed);
      dinosaur.thirst().setValue(dinosaur.thirst().get() - waterThatCanBeConsumed);
   }
}
