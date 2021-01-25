
package uk.dangrew.dinosaurs.game.actions.consumption;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.dangrew.dinosaurs.game.actions.consumption.EatAction.MAXIMUM_FEED;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class DrinkActionTest {
   
   private Water water;
   private WorldLocation location;
   private Dinosaur dinosaur;
   private DrinkAction systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      water = new Water("Water");
      location = new TestWorldLocation(10, 11);
      water.cover(location);
      dinosaur = new Dinosaur("Dinosaur");
      systemUnderTest = new DrinkAction(water, Collections.singleton(location), dinosaur);
   }
   
   @Test
   public void shouldBeAvailableWhenTreeHasFoodAndDinoThirsty() {
      water.waterAvailable().set(100);
      dinosaur.thirst().set(100);
      assertThat(systemUnderTest.isAvailable(), equalTo(true));
   }
   
   @Test
   public void shouldNotBeAvailableWhenDinoNotThirsty() {
      water.waterAvailable().set(100);
      assertThat(systemUnderTest.isAvailable(), equalTo(false));
   }
   
   @Test
   public void shouldNotBeAvailableWhenWaterEmpty() {
      water.waterAvailable().set(0);
      dinosaur.thirst().set(100);
      assertThat(systemUnderTest.isAvailable(), equalTo(false));
   }
   
   @Test
   public void shouldDescribeWhenAvailable() {
      water.waterAvailable().set(100);
      dinosaur.thirst().set(100);
      assertThat(systemUnderTest.describe(), equalTo("{10, 11}:\n: Drink! Top quality natural mineral water!"));
   }
   
   @Test
   public void shouldDescribeWhenDinosaurNotThirsty() {
      water.waterAvailable().set(100);
      assertThat(systemUnderTest.describe(), equalTo("{10, 11}:\n: Not thirsty..."));
   }
   
   @Test
   public void shouldDescribeWhenWaterEmpty() {
      water.waterAvailable().set(0);
      dinosaur.thirst().set(100);
      assertThat(systemUnderTest.describe(), equalTo("{10, 11}:\n: Nothing to drink... Think we're having a drought..."));
   }
   
   @Test
   public void shouldActionDinosaurDrinking() {
      water.waterAvailable().set(200);
      dinosaur.thirst().set(100);
      
      systemUnderTest.performAction();
      assertThat(dinosaur.thirst().get(), equalTo(100 - MAXIMUM_FEED));
      assertThat(water.waterAvailable().get(), equalTo(200 - MAXIMUM_FEED));
   }
   
   @Test
   public void shouldTakeOnlyWaterThatDinosaurNeeds() {
      water.waterAvailable().set(200);
      dinosaur.thirst().set(15);
      
      systemUnderTest.performAction();
      assertThat(dinosaur.thirst().get(), equalTo(0));
      assertThat(water.waterAvailable().get(), equalTo(200 - 15));
   }
   
   @Test
   public void shouldTakeOnlyWaterAvailable() {
      water.waterAvailable().set(15);
      dinosaur.thirst().set(100);
      
      systemUnderTest.performAction();
      assertThat(dinosaur.thirst().get(), equalTo(100 - 15));
      assertThat(water.waterAvailable().get(), equalTo(0));
   }
}
