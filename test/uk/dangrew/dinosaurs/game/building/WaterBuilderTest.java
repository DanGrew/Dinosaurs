
package uk.dangrew.dinosaurs.game.building;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.HORIZONTAL_GRASS_TOP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

@ExtendWith(MockitoExtension.class)
public class WaterBuilderTest {
   
   private World world;
   private Water water;
   
   private WaterTileCalculator waterTileCalculator;
   private WaterBuilder systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      world = new World("World");
      world.setDimension(10, 10);
      water = new Water("Water");
      waterTileCalculator = spy(new WaterTileCalculator(water, world));
      systemUnderTest = new WaterBuilder(waterTileCalculator, water, world);
   }
   
   @Test
   public void shouldCoverRectangle() {
      systemUnderTest.coverRectangle(new WorldLocation(1, 1), 3, 2);
      assertThat(water.getCoverage(), hasSize(6));
      assertThat(water.getLocationPropertiesFor(new WorldLocation(1, 1)), notNullValue());
      assertThat(water.getLocationPropertiesFor(new WorldLocation(2, 1)), notNullValue());
      assertThat(water.getLocationPropertiesFor(new WorldLocation(3, 1)), notNullValue());
      assertThat(water.getLocationPropertiesFor(new WorldLocation(1, 2)), notNullValue());
      assertThat(water.getLocationPropertiesFor(new WorldLocation(2, 2)), notNullValue());
      assertThat(water.getLocationPropertiesFor(new WorldLocation(3, 2)), notNullValue());
   }
   
   @Test
   public void shouldCoverRectangleWrappingWorld() {
      systemUnderTest.coverRectangle(new WorldLocation(9, 9), 3, 2);
      assertThat(water.getCoverage(), hasSize(6));
      assertThat(water.getLocationPropertiesFor(new WorldLocation(9, 9)), notNullValue());
      assertThat(water.getLocationPropertiesFor(new WorldLocation(0, 9)), notNullValue());
      assertThat(water.getLocationPropertiesFor(new WorldLocation(1, 9)), notNullValue());
      assertThat(water.getLocationPropertiesFor(new WorldLocation(9, 0)), notNullValue());
      assertThat(water.getLocationPropertiesFor(new WorldLocation(0, 0)), notNullValue());
      assertThat(water.getLocationPropertiesFor(new WorldLocation(1, 0)), notNullValue());
   }
   
   @Test
   public void shouldAppend() {
      systemUnderTest
            .append(new WorldLocation(1, 2))
            .append(new WorldLocation(2, 3));
      assertThat(water.getCoverage(), hasSize(2));
      assertThat(water.getLocationPropertiesFor(new WorldLocation(1, 2)), notNullValue());
      assertThat(water.getLocationPropertiesFor(new WorldLocation(2, 3)), notNullValue());
   }
   
   @Test
   public void shouldRemove() {
      systemUnderTest
            .append(new WorldLocation(1, 2))
            .append(new WorldLocation(2, 3))
            .subtract(new WorldLocation(1, 2));
      
      assertThat(water.getCoverage(), hasSize(1));
      assertThat(water.getLocationPropertiesFor(new WorldLocation(2, 3)), notNullValue());
   }
   
   @Test
   public void shouldPopulateTileTypes() {
      doReturn(HORIZONTAL_GRASS_TOP).when(waterTileCalculator).calculateTileType(any());
      
      systemUnderTest
            .coverRectangle(new WorldLocation(0, 0), 3, 3)
            .build();
      assertThat(water.getCoverage(), hasSize(9));
      
      for (WorldLocation location : water.getCoverage()) {
         assertThat(water.getLocationPropertiesFor(location).getTileType(), equalTo(HORIZONTAL_GRASS_TOP));
      }
   }
   
   @Test
   public void shouldPopulateShallowDepths() {
      systemUnderTest
            .coverRectangle(new WorldLocation(0, 0), 2, 2)
            .build();
      assertThat(water.getCoverage(), hasSize(4));
      
      for (WorldLocation location : water.getCoverage()) {
         assertThat(water.getLocationPropertiesFor(location).getDepth().getDepth(), equalTo(0));
      }
   }
   
   @Test
   public void shouldPopulateShallowSurroundedDepths() {
      systemUnderTest
            .coverRectangle(new WorldLocation(0, 0), 3, 3)
            .build();
      assertThat(water.getCoverage(), hasSize(9));
      
      assertThat(water.getLocationPropertiesFor(new WorldLocation(1, 1)).getDepth().getDepth(), equalTo(1));
   }
   
   @Test
   public void shouldPopulateDeepestDepths() {
      systemUnderTest
            .coverRectangle(new WorldLocation(0, 0), 5, 5)
            .build();
      assertThat(water.getCoverage(), hasSize(25));
      
      assertThat(water.getLocationPropertiesFor(new WorldLocation(2, 2)).getDepth().getDepth(), equalTo(2));
   }
   
}
