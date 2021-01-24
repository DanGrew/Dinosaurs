
package uk.dangrew.dinosaurs.game.model.dinosaur;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class InteractionZoneTest {
   
   private Dinosaur dinosaur;
   private World world;
   private InteractionZone systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      world = new World("World");
      world.setDimension(20, 20);
      dinosaur = new Dinosaur("Dino");
      systemUnderTest = new InteractionZone(dinosaur, 2);
      
      dinosaur.getWorldLocation().set(new TestWorldLocation(3, 4));
   }
   
   @Test
   public void shouldProvideZoneOverRange() {
      Collection<WorldLocation> interactionZone = systemUnderTest.calculateInteractionZone(world);
      assertThat(interactionZone.size(), equalTo(25));
      for (int i = 0; i < 5; i++) {
         for (int j = 0; j < 5; j++) {
            assertThat(interactionZone.contains(new TestWorldLocation(i + 1, j + 2)), equalTo(true));
            assertThat(systemUnderTest.contains(new TestWorldLocation(i + 1, j + 2)), equalTo(true));
         }
      }
   }
   
}
