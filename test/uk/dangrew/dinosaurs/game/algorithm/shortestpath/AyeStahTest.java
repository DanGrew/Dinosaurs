
package uk.dangrew.dinosaurs.game.algorithm.shortestpath;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class AyeStahTest {
   
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
      result = systemUnderTest.search(sessionFor(new TestWorldLocation(4, 2), new TestWorldLocation(7, 2)));
      assertThatPathIsFound(result, 4, 2, 5, 2, 6, 2, 7, 2);
   }
   
   @Test
   public void shouldFindShortestPathInsideBoundsLeft() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(new TestWorldLocation(7, 2), new TestWorldLocation(4, 2)));
      assertThatPathIsFound(result, 7, 2, 6, 2, 5, 2, 4, 2);
   }
   
   @Test
   public void shouldFindShortestPathInsideBoundsDown() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(new TestWorldLocation(4, 2), new TestWorldLocation(4, 5)));
      assertThatPathIsFound(result, 4, 2, 4, 3, 4, 4, 4, 5);
   }
   
   @Test
   public void shouldFindShortestPathInsideBoundsUp() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(new TestWorldLocation(7, 5), new TestWorldLocation(7, 2)));
      assertThatPathIsFound(result, 7, 5, 7, 4, 7, 3, 7, 2);
   }
   
   @Test
   public void shouldFindShortestPathInsideBoundsDiagonal() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(new TestWorldLocation(4, 2), new TestWorldLocation(7, 5)));
      assertThatPathIsFound(result, 4, 2, 4, 3, 5, 3, 5, 4, 6, 4, 6, 5, 7, 5);
   }
   
   @Test
   public void shouldFindShortestPathOutsideBoundsRight() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(new TestWorldLocation(9, 2), new TestWorldLocation(1, 2)));
      assertThatPathIsFound(result, 9, 2, 0, 2, 1, 2);
   }
   
   @Test
   public void shouldFindShortestPathOutsideBoundsLeft() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(new TestWorldLocation(1, 2), new TestWorldLocation(9, 2)));
      assertThatPathIsFound(result, 1, 2, 0, 2, 9, 2);
   }
   
   @Test
   public void shouldFindShortestPathOutsideBoundsDown() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(new TestWorldLocation(4, 9), new TestWorldLocation(4, 1)));
      assertThatPathIsFound(result, 4, 9, 4, 0, 4, 1);
   }
   
   @Test
   public void shouldFindShortestPathOutsideBoundsUp() throws AyeStahException {
      result = systemUnderTest.search(sessionFor(new TestWorldLocation(7, 1), new TestWorldLocation(7, 9)));
      assertThatPathIsFound(result, 7, 1, 7, 0, 7, 9);
   }
   
   @Test
   public void shouldClearStructuresToPerformMultipleSearches() throws AyeStahException {
      shouldFindShortestPathInsideBoundsUp();
      shouldFindShortestPathInsideBoundsDown();
      shouldFindShortestPathInsideBoundsDiagonal();
      shouldFindShortestPathOutsideBoundsUp();
   }
   
   private SearchSession sessionFor(WorldLocation origin, WorldLocation destination){
      return new SearchSession(origin, destination);
   }
   
   public static void assertThatPathIsFound(List<WorldLocation> result, int... locationPairs) {
      assertThat("Must provide pairs.", locationPairs.length % 2 == 0, equalTo(true));
      assertThat(result, hasSize(locationPairs.length / 2));
      
      for (int i = 0; i < locationPairs.length / 2; i++) {
         WorldLocation expected = new TestWorldLocation(locationPairs[i * 2], locationPairs[i * 2 + 1]);
         assertThat(result.get(i), equalTo(expected));
      }
   }
}
