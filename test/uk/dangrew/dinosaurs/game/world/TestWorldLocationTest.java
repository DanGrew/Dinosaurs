
package uk.dangrew.dinosaurs.game.world;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.Test;

public class TestWorldLocationTest {
   
   @Test
   public void shouldBeEqual() {
      assertThat(new TestWorldLocation(0, 0), equalTo(new TestWorldLocation(0, 0)));
      assertThat(new WorldLocation(0, 0), equalTo(new TestWorldLocation(0, 0)));
      assertThat(new TestWorldLocation(0, 0), equalTo(new WorldLocation(0, 0)));

      assertThat(new TestWorldLocation(0, 1), not(equalTo(new TestWorldLocation(0, 0))));
      assertThat(new WorldLocation(0, 1), not(equalTo(new TestWorldLocation(0, 0))));
      assertThat(new TestWorldLocation(0, 1), not(equalTo(new WorldLocation(0, 0))));
   }
}
