
package uk.dangrew.dinosaurs.ui.view;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.ui.configuration.GameState;

public class WorldViewportTest {
   
   private WorldViewport systemUnderTest;
   private GameState gameState;

   @BeforeEach
   public void initialiseSystemUnderTest() {
      gameState = new GameState();
      systemUnderTest = new WorldViewport(gameState);
   }
   
   @Test
   public void shouldUpdateTopLeftPosition() {
      World world = new World("World");
      world.setDimension(10, 10);
      gameState.currentWorld().set(world);
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new TestWorldLocation(0, 0)));
      
      systemUnderTest.panRight();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new TestWorldLocation(1, 0)));
      
      systemUnderTest.panLeft();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new TestWorldLocation(0, 0)));
      
      systemUnderTest.panDown();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new TestWorldLocation(0, 1)));
      
      systemUnderTest.panUp();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new TestWorldLocation(0, 0)));
   }
   
   @Test
   public void shouldResetLocationWhenWorldChanges() {
      fail();
   }
   
}
