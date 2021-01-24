package uk.dangrew.dinosaurs.game.behaviour.consumption;

import java.util.Random;

import uk.dangrew.dinosaurs.game.behaviour.DinosaurBehaviour;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;

/**
 * Provides behaviour of a dinosaur that increases hunger and thirst as they complete actions.
 */
public class ConsumptionBehaviour implements DinosaurBehaviour {
   
   private static final int MAXIMUM_INCREASE = 5;
   
   private final Random consumptionRandomizer;
   private final Dinosaur dinosaur;
   
   public ConsumptionBehaviour(Dinosaur dinosaur){
      this(new Random(), dinosaur);
   }
   
   ConsumptionBehaviour(Random random, Dinosaur dinosaur){
      this.dinosaur = dinosaur;
      this.consumptionRandomizer = random;
   }
   
   @Override
   public void behave() {
      int hungerIncrease = consumptionRandomizer.nextInt(MAXIMUM_INCREASE);
      dinosaur.hunger().set(dinosaur.hunger().get() + hungerIncrease);
      
      int thirstIncrease = consumptionRandomizer.nextInt(MAXIMUM_INCREASE);
      dinosaur.thirst().set(dinosaur.thirst().get() + thirstIncrease);
   }
}