

package uk.dangrew.dinosaurs.game.world;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class WorldLocationTest {
   
   @Test
   public void shouldBeEqual() {
      assertThat(new WorldLocation(20, 21), equalTo(new WorldLocation(20, 21)));
      assertThat(new WorldLocation(20, 21), not(equalTo(new WorldLocation(20, 19))));
      assertThat(new WorldLocation(20, 21), not(equalTo(new WorldLocation(19, 21))));
   }
   
}
