

package uk.dangrew.dinosaurs.ui.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DinosaursConfigurationTest {
   
   private DinosaursConfiguration systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      systemUnderTest = new DinosaursConfiguration();
   }
   
   @Test
   public void shouldProvideCellDimensionAndRadius() {
      assertThat(systemUnderTest.worldCellDimension().get(), equalTo(DinosaursConfiguration.DEFAULT_WORLD_CELL_DIMENSION));
      assertThat(systemUnderTest.worldCellRadius().get(), equalTo(DinosaursConfiguration.DEFAULT_WORLD_CELL_DIMENSION / 2));

      systemUnderTest.worldCellDimension().set(20);
      assertThat(systemUnderTest.worldCellDimension().get(), equalTo(20));
      assertThat(systemUnderTest.worldCellRadius().get(), equalTo(10));
   }
   
}
