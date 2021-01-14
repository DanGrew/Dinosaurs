
package uk.dangrew.dinosaurs.ui.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorldViewportTest {
   
   private World world;
   private WorldViewport systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      world = new World();
      systemUnderTest = new WorldViewport(world);
   }
   
   @Test
   public void shouldUpdateTopLeftPosition() {
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new WorldLocation(0, 0)));
      
      systemUnderTest.panRight();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new WorldLocation(1, 0)));

      systemUnderTest.panLeft();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new WorldLocation(0, 0)));

      systemUnderTest.panDown();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new WorldLocation(0, 1)));

      systemUnderTest.panUp();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new WorldLocation(0, 0)));
   }

}