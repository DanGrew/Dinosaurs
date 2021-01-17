package uk.dangrew.dinosaurs.game.collision;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class TreeCollisionDetectorTest {
   
   private Dinosaur dinosaur;
   private Tree tree;
   private TreeCollisionDetector systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      tree = new Tree("Tree");
      tree.setWorldLocation(new WorldLocation(5, 6));
      dinosaur = new Dinosaur("Dino");
      systemUnderTest = new TreeCollisionDetector(tree);
   }
   
   @Test
   public void shouldCollideWithTreeBelowHeight() {
      tree.setHeight(1);
      assertThat(systemUnderTest.canLocationBeOccupied(dinosaur, tree.getWorldLocation()).isPresent(), equalTo(true));
      tree.setHeight(0);
      assertThat(systemUnderTest.canLocationBeOccupied(dinosaur, tree.getWorldLocation()).isPresent(), equalTo(true));
      tree.setHeight(2);
      assertThat(systemUnderTest.canLocationBeOccupied(dinosaur, tree.getWorldLocation()).isPresent(), equalTo(false));
   }
}