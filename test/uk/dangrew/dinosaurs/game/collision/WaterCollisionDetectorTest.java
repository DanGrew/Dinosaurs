package uk.dangrew.dinosaurs.game.collision;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.model.water.WaterLocationProperties;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class WaterCollisionDetectorTest {
   
   private Dinosaur dinosaur;
   private Water water;
   private WaterCollisionDetector systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      dinosaur = new Dinosaur("Dino");
      water = new Water("Water");
      systemUnderTest = new WaterCollisionDetector(water);
   }
   
   @Test
   public void shouldAllowDinosaurWithReasonableHeight() {
      water.cover(new WorldLocation(5, 5), new WaterLocationProperties(0, null));
      assertThat(systemUnderTest.canLocationBeOccupied(dinosaur, new WorldLocation(5, 5)).isPresent(), equalTo(false));
   }
   
   @Test
   public void shouldPreventDinosaurWhoIsTooShortForDepth() {
      water.cover(new WorldLocation(5, 5), new WaterLocationProperties(3, null));
      assertThat(systemUnderTest.canLocationBeOccupied(dinosaur, new WorldLocation(5, 5)).isPresent(), equalTo(true));
   }
}