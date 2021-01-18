
package uk.dangrew.dinosaurs.game.building;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static uk.dangrew.dinosaurs.game.model.rubble.RockTileType.ROCK;
import static uk.dangrew.dinosaurs.game.model.rubble.RockTileType.ROCK_BOTTOM_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.rubble.RockTileType.ROCK_BOTTOM_RIGHT_CORNER;
import static uk.dangrew.dinosaurs.game.model.rubble.RockTileType.ROCK_TOP_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.rubble.RockTileType.ROCK_TOP_RIGHT_CORNER;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.model.rubble.Rock;
import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.game.world.World;

public class RockBuilderTest {
   
   private World world;
   private Rock rock;
   private RockBuilder systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      world = new World("World");
      world.setDimension(10, 10);
      rock = new Rock("Rock");
      systemUnderTest = new RockBuilder(rock, world);
   }
   
   @Test
   public void shouldBuildTriangleRightDown() {
      systemUnderTest.coverTriangle(new TestWorldLocation(5, 5), 2, true, true);
      
      assertThat(rock.getCoverage(), hasSize(6));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 5)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(6, 5)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(7, 5)).getTileType(), equalTo(ROCK_TOP_LEFT_CORNER));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 6)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(6, 6)).getTileType(), equalTo(ROCK_TOP_LEFT_CORNER));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 7)).getTileType(), equalTo(ROCK_TOP_LEFT_CORNER));
   }

   @Test
   public void shouldBuildTriangleRightUp() {
      systemUnderTest.coverTriangle(new TestWorldLocation(5, 5), 2, true, false);

      assertThat(rock.getCoverage(), hasSize(6));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 5)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(6, 5)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(7, 5)).getTileType(), equalTo(ROCK_BOTTOM_LEFT_CORNER));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 4)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(6, 4)).getTileType(), equalTo(ROCK_BOTTOM_LEFT_CORNER));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 3)).getTileType(), equalTo(ROCK_BOTTOM_LEFT_CORNER));
   }

   @Test
   public void shouldBuildTriangleLeftDown() {
      systemUnderTest.coverTriangle(new TestWorldLocation(5, 5), 2, false, true);

      assertThat(rock.getCoverage(), hasSize(6));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 5)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(4, 5)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(3, 5)).getTileType(), equalTo(ROCK_TOP_RIGHT_CORNER));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 6)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(4, 6)).getTileType(), equalTo(ROCK_TOP_RIGHT_CORNER));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 7)).getTileType(), equalTo(ROCK_TOP_RIGHT_CORNER));
   }

   @Test
   public void shouldBuildTriangleLeftUp() {
      systemUnderTest.coverTriangle(new TestWorldLocation(5, 5), 2, false, false);

      assertThat(rock.getCoverage(), hasSize(6));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 5)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(4, 5)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(3, 5)).getTileType(), equalTo(ROCK_BOTTOM_RIGHT_CORNER));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 4)).getTileType(), equalTo(ROCK));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(4, 4)).getTileType(), equalTo(ROCK_BOTTOM_RIGHT_CORNER));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(5, 3)).getTileType(), equalTo(ROCK_BOTTOM_RIGHT_CORNER));
   }

   @Test
   public void shouldCoverRectangle() {
      systemUnderTest.coverRectangle(new TestWorldLocation(1, 1), 3, 2);
      assertThat(rock.getCoverage(), hasSize(6));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(1, 1)), CoreMatchers.notNullValue());
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(2, 1)), CoreMatchers.notNullValue());
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(3, 1)), CoreMatchers.notNullValue());
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(1, 2)), CoreMatchers.notNullValue());
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(2, 2)), CoreMatchers.notNullValue());
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(3, 2)), CoreMatchers.notNullValue());
   }

   @Test
   public void shouldCoverRectangleWrappingWorld() {
      systemUnderTest.coverRectangle(new TestWorldLocation(9, 9), 3, 2);
      assertThat(rock.getCoverage(), hasSize(6));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(9, 9)), CoreMatchers.notNullValue());
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(0, 9)), CoreMatchers.notNullValue());
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(1, 9)), CoreMatchers.notNullValue());
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(9, 0)), CoreMatchers.notNullValue());
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(0, 0)), CoreMatchers.notNullValue());
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(1, 0)), CoreMatchers.notNullValue());
   }

   @Test
   public void shouldAppend() {
      systemUnderTest
            .append(new TestWorldLocation(1, 2))
            .append(new TestWorldLocation(2, 3));
      assertThat(rock.getCoverage(), hasSize(2));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(1, 2)), CoreMatchers.notNullValue());
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(2, 3)), CoreMatchers.notNullValue());
   }

   @Test
   public void shouldRemove() {
      systemUnderTest
            .append(new TestWorldLocation(1, 2))
            .append(new TestWorldLocation(2, 3))
            .subtract(new TestWorldLocation(1, 2));

      assertThat(rock.getCoverage(), hasSize(1));
      assertThat(rock.getLocationPropertiesFor(new TestWorldLocation(2, 3)), CoreMatchers.notNullValue());
   }
}
