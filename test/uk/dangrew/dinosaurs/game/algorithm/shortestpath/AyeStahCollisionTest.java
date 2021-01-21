
package uk.dangrew.dinosaurs.game.algorithm.shortestpath;

import static uk.dangrew.dinosaurs.game.algorithm.shortestpath.AyeStahTest.assertThatPathIsFound;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class AyeStahCollisionTest {
   
   private AyeStah systemUnderTest;
   private List<WorldLocation> result;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      World world = new World("World");
      world.setDimension(10, 10);
      systemUnderTest = new AyeStah(world);
   }
   
   @Test
   public void shouldFindShortestPathInsideBoundsRight() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(
            new TestWorldLocation(4, 2), new TestWorldLocation(7, 2),
            new TestWorldLocation(5, 2), new TestWorldLocation(6, 2)));
      assertThatPathIsFound(result, 4, 2, 4, 1, 5, 1, 6, 1, 7, 1, 7, 2);
   }
   
   @Test
   public void shouldFindShortestPathInsideBoundsLeft() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(
            new TestWorldLocation(7, 2), new TestWorldLocation(4, 2),
            new TestWorldLocation(6, 2), new TestWorldLocation(5, 2)));
      assertThatPathIsFound(result, 7, 2, 7, 1, 6, 1, 5, 1, 4, 1, 4, 2);
   }
   
   @Test
   public void shouldFindShortestPathInsideBoundsDown() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(
            new TestWorldLocation(4, 2), new TestWorldLocation(4, 5),
            new TestWorldLocation(4, 3), new TestWorldLocation(4, 4)));
      assertThatPathIsFound(result, 4, 2, 3, 2, 3, 3, 3, 4, 3, 5, 4, 5);
   }
   
   @Test
   public void shouldFindShortestPathInsideBoundsUp() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(
            new TestWorldLocation(7, 5), new TestWorldLocation(7, 2),
            new TestWorldLocation(7, 4), new TestWorldLocation(7, 3)));
      assertThatPathIsFound(result, 7, 5, 6, 5, 6, 4, 6, 3, 6, 2, 7, 2);
   }
   
      @Test
      public void shouldFindShortestPathInsideBoundsDiagonal() throws AyeStahException {
         result = systemUnderTest.search(sessionFor(
               new TestWorldLocation(4, 2), new TestWorldLocation(7, 5),
               new TestWorldLocation(5, 4)));
         assertThatPathIsFound(result, 4, 2, 4, 3, 5, 3, 6, 3, 6, 4, 6, 5, 7, 5);
      }

      @Test
      public void shouldFindShortestPathOutsideBoundsRight() throws AyeStahException {
         result = systemUnderTest.search(sessionFor(
               new TestWorldLocation(9, 2), new TestWorldLocation(1, 2),
               new TestWorldLocation(0, 2)));
         assertThatPathIsFound(result, 9, 2, 9, 1, 0, 1, 1, 1, 1, 2);
      }

      @Test
      public void shouldFindShortestPathOutsideBoundsLeft() throws AyeStahException {
         result = systemUnderTest.search(sessionFor(
               new TestWorldLocation(1, 2), new TestWorldLocation(9, 2),
               new TestWorldLocation(0, 2)));
         assertThatPathIsFound(result, 1, 2, 1, 1, 0, 1, 9, 1, 9, 2);
      }

      @Test
      public void shouldFindShortestPathOutsideBoundsDown() throws AyeStahException {
         result = systemUnderTest.search(sessionFor(
               new TestWorldLocation(4, 9), new TestWorldLocation(4, 1),
               new TestWorldLocation(4, 0)));
         assertThatPathIsFound(result, 4, 9, 3, 9, 3, 0, 3, 1, 4, 1);
      }

      @Test
      public void shouldFindShortestPathOutsideBoundsUp() throws AyeStahException {
         result = systemUnderTest.search(sessionFor(
               new TestWorldLocation(7, 1), new TestWorldLocation(7, 9),
               new TestWorldLocation(7, 0)));
         assertThatPathIsFound(result, 7, 1, 6, 1, 6, 0, 6, 9, 7, 9);
      }

   private SearchSession sessionFor(WorldLocation origin, WorldLocation destination, WorldLocation... blocked) {
      return new SearchSession(origin, destination, new BlockedLocations(blocked));
   }
   
}
