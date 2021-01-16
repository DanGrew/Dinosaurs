

package uk.dangrew.dinosaurs.ui.configuration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameStateTest {
   
   private GameState systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      systemUnderTest = new GameState();
   }
   
   @Test
   public void shouldProvideCellDimensionAndRadius() {
      assertThat(systemUnderTest.worldCellDimension().get(), equalTo(GameState.DEFAULT_WORLD_CELL_DIMENSION));
      assertThat(systemUnderTest.worldCellRadius().get(), equalTo(GameState.DEFAULT_WORLD_CELL_DIMENSION / 2));

      systemUnderTest.worldCellDimension().set(20);
      assertThat(systemUnderTest.worldCellDimension().get(), equalTo(20));
      assertThat(systemUnderTest.worldCellRadius().get(), equalTo(10));
   }
   
}
