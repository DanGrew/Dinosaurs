
package uk.dangrew.dinosaurs.game.actions.consumption;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.game.world.World;

public class EatActionGeneratorTest {
   
   private World world;
   private Dinosaur dinosaur;
   
   private Tree tree;
   private EatActionGenerator systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      world = new World("World");
      world.setDimension(10, 10);
      
      dinosaur = new Dinosaur("Dino");
      dinosaur.getWorldLocation().set(new TestWorldLocation(4, 5));
      dinosaur.getInteractionZone().calculateInteractionZone(world);
      
      tree = new Tree("Tree");
      systemUnderTest = new EatActionGenerator(tree);
   }
   
   @Test
   public void shouldProvideEatActionWhenInInteractionZone() {
      tree.setWorldLocation(new TestWorldLocation(3, 4));
      
      Collection<GameAction> result = systemUnderTest.generateActions(dinosaur, world);
      assertThat(result, hasSize(1));
      assertThat(result.iterator().next(), instanceOf(EatAction.class));
   }
   
   @Test
   public void shouldNotProvideEatActionWhenNotInInteractioZone() {
      tree.setWorldLocation(new TestWorldLocation(8, 8));
      
      Collection<GameAction> result = systemUnderTest.generateActions(dinosaur, world);
      assertThat(result, empty());
   }
}
