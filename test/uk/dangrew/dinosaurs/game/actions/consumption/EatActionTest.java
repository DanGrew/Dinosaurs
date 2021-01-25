
package uk.dangrew.dinosaurs.game.actions.consumption;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.dangrew.dinosaurs.game.actions.consumption.EatAction.MAXIMUM_FEED;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.world.TestWorldLocation;

public class EatActionTest {
   
   private Tree tree;
   private Dinosaur dinosaur;
   private EatAction systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      tree = new Tree("Tree");
      tree.setWorldLocation(new TestWorldLocation(10, 11));
      dinosaur = new Dinosaur("Dinosaur");
      systemUnderTest = new EatAction(tree, dinosaur);
   }
   
   @Test
   public void shouldBeAvailableWhenTreeHasFoodAndDinoHungry() {
      tree.foodAvailable().set(100);
      dinosaur.hunger().set(100);
      assertThat(systemUnderTest.isAvailable(), equalTo(true));
   }
   
   @Test
   public void shouldNotBeAvailableWhenDinoNotHungry() {
      tree.foodAvailable().set(100);
      assertThat(systemUnderTest.isAvailable(), equalTo(false));
   }
   
   @Test
   public void shouldNotBeAvailableWhenTreeEmpty() {
      tree.foodAvailable().set(0);
      dinosaur.hunger().set(100);
      assertThat(systemUnderTest.isAvailable(), equalTo(false));
   }
   
   @Test
   public void shouldDescribeWhenAvailable() {
      tree.foodAvailable().set(100);
      dinosaur.hunger().set(100);
      assertThat(systemUnderTest.describe(), equalTo("{10, 11}:\nEat! Juicy leaves available!"));
   }
   
   @Test
   public void shouldDescribeWhenDinosaurNotHungry() {
      tree.foodAvailable().set(100);
      assertThat(systemUnderTest.describe(), equalTo("{10, 11}:\nNo appetite..."));
   }
   
   @Test
   public void shouldDescribeWhenTreeEmpty() {
      tree.foodAvailable().set(0);
      dinosaur.hunger().set(100);
      assertThat(systemUnderTest.describe(), equalTo("{10, 11}:\nNothing to eat... Tree's looking a bit bare..."));
   }
   
   @Test
   public void shouldActionDinosaurEating() {
      tree.foodAvailable().set(200);
      dinosaur.hunger().set(100);
      
      systemUnderTest.performAction();
      assertThat(dinosaur.hunger().get(), equalTo(100 - MAXIMUM_FEED));
      assertThat(tree.foodAvailable().get(), equalTo(200 - MAXIMUM_FEED));
   }
   
   @Test
   public void shouldTakeOnlyFoodThatDinosaurNeeds() {
      tree.foodAvailable().set(200);
      dinosaur.hunger().set(15);
      
      systemUnderTest.performAction();
      assertThat(dinosaur.hunger().get(), equalTo(0));
      assertThat(tree.foodAvailable().get(), equalTo(200 - 15));
   }
   
   @Test
   public void shouldTakeOnlyFoodTreeHas() {
      tree.foodAvailable().set(15);
      dinosaur.hunger().set(100);
      
      systemUnderTest.performAction();
      assertThat(dinosaur.hunger().get(), equalTo(100 - 15));
      assertThat(tree.foodAvailable().get(), equalTo(0));
   }
}
