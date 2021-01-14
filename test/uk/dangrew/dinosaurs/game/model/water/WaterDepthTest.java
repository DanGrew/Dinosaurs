

package uk.dangrew.dinosaurs.game.model.water;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class WaterDepthTest {
   
   @Test
   public void shouldBeEqual() {
      assertThat(new WaterDepth(1), equalTo(new WaterDepth(1)));
      assertThat(new WaterDepth(2), not(equalTo(new WaterDepth(1))));
   }
}
