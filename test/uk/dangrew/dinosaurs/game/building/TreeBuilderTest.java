package uk.dangrew.dinosaurs.game.building;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.storage.TreeStore;
import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.game.world.World;

public class TreeBuilderTest {
   
   private World world;
   private TreeStore treeStore;
   private TreeBuilder systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      world = new World("World");
      world.setDimension(10, 10);
      treeStore = new TreeStore();
      systemUnderTest = new TreeBuilder(treeStore, world);
   }
   
   @Test
   public void shouldCreateTreesInStraightLine() {
      systemUnderTest.straightLine(new TestWorldLocation(0, 0), 1, 2, 3);
      
      assertThat(treeStore.objectList(), hasSize(3));
      assertThat(treeStore.objectList().get(0).getWorldLocation(), equalTo(new TestWorldLocation(0, 0)));
      assertThat(treeStore.objectList().get(1).getWorldLocation(), equalTo(new TestWorldLocation(1, 2)));
      assertThat(treeStore.objectList().get(2).getWorldLocation(), equalTo(new TestWorldLocation(2, 4)));
   }
   
   @Test
   public void shouldWrapAroundWorld() {
      systemUnderTest.straightLine(new TestWorldLocation(8, 9), 2, 3, 3);

      assertThat(treeStore.objectList(), hasSize(3));
      assertThat(treeStore.objectList().get(0).getWorldLocation(), equalTo(new TestWorldLocation(8, 9)));
      assertThat(treeStore.objectList().get(1).getWorldLocation(), equalTo(new TestWorldLocation(0, 2)));
      assertThat(treeStore.objectList().get(2).getWorldLocation(), equalTo(new TestWorldLocation(2, 5)));
   }
}