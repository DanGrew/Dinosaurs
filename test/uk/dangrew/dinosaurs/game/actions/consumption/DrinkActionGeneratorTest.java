package uk.dangrew.dinosaurs.game.actions.consumption;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.game.world.World;

public class DrinkActionGeneratorTest {
   
   private Dinosaur dinosaur;
   private World world;
   
   private Water water;
   private DrinkActionGenerator systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      dinosaur = new Dinosaur("Dino");
      world = new World("World");
      world.setDimension(10, 10);
      
      water = new Water("Water");
      systemUnderTest = new DrinkActionGenerator(water);
   }
   
   @Test
   public void shouldNotProvideActionWhenNotNearWater() {
      water.cover(new TestWorldLocation(5, 6));
      dinosaur.getWorldLocation().set(new TestWorldLocation(0, 0));
      
      assertThat(systemUnderTest.generateActions(dinosaur, world), empty());
   }
   
   @Test
   public void shouldProvideDrinkActionForLocation() {
      water.cover(new TestWorldLocation(5, 5));
      dinosaur.getWorldLocation().set(new TestWorldLocation(4, 5));

      Collection<GameAction> actions = systemUnderTest.generateActions(dinosaur, world);
      assertThat(actions, hasSize(1));
      assertThat(actions.iterator().next().describe(), containsString("{5, 5}"));
   }
   
   @Test
   public void shouldProvideDrinkActionForMultipleLocations() {
      water.cover(new TestWorldLocation(5, 4));
      water.cover(new TestWorldLocation(5, 5));
      water.cover(new TestWorldLocation(5, 6));
      dinosaur.getWorldLocation().set(new TestWorldLocation(4, 5));

      Collection<GameAction> actions = systemUnderTest.generateActions(dinosaur, world);
      assertThat(actions, hasSize(1));
      assertThat(actions.iterator().next().describe(), containsString("{5, 4}, {5, 5}, {5, 6}"));
   }
}