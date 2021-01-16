

package uk.dangrew.dinosaurs.game.model.water;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;

public class WaterDepthTest {
   
   @Test
   public void shouldBeEqual() {
      assertThat(new WaterDepth(1), equalTo(new WaterDepth(1)));
      assertThat(new WaterDepth(2), not(equalTo(new WaterDepth(1))));
   }
   
   @Test
   public void shouldNotAllowDinosaurTooShort() {
      Dinosaur dinosaur = new Dinosaur("Dino");
      assertThat(new WaterDepth(0).dinosaurCanSurvive(dinosaur), equalTo(true));
      assertThat(new WaterDepth(3).dinosaurCanSurvive(dinosaur), equalTo(false));
   }
}
