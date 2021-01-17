
package uk.dangrew.dinosaurs.game.collision;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.mechanism.AssetManager;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.model.water.WaterLocationProperties;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class CollisionDetectionTest {
   
   private Dinosaur dinosaur;
   
   private AssetManager assetManager;
   private CollisionDetection systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      dinosaur = new Dinosaur("Dino");
      dinosaur.getWorldLocation().set(new WorldLocation(4, 5));
      
      assetManager = new AssetManager();
      systemUnderTest = new CollisionDetection(assetManager);
   }
   
   @Test
   public void shouldCalculateWaterCollisions() {
      Water water = assetManager.getWaterStore().createConcept("Water");
      water.cover(new WorldLocation(5, 5), new WaterLocationProperties(3, null));
      
      assertThat(systemUnderTest.calculateCollisions(dinosaur, new WorldLocation(4, 6)), empty());
      assertThat(systemUnderTest.calculateCollisions(dinosaur, new WorldLocation(5, 5)), not(empty()));
   }

   @Test
   public void shouldCalculateTreeCollisions() {
      Tree concept = assetManager.getTreeStore().createConcept("Tree");
      concept.setWorldLocation(new WorldLocation(5, 5));

      assertThat(systemUnderTest.calculateCollisions(dinosaur, new WorldLocation(4, 6)), empty());
      assertThat(systemUnderTest.calculateCollisions(dinosaur, new WorldLocation(5, 5)), not(empty()));
   }
   
   @Test
   public void shouldHandleMultipleCollisions() {
      Water water = assetManager.getWaterStore().createConcept("Water");
      water.cover(new WorldLocation(5, 5), new WaterLocationProperties(3, null));

      Tree concept = assetManager.getTreeStore().createConcept("Tree");
      concept.setWorldLocation(new WorldLocation(5, 5));

      assertThat(systemUnderTest.calculateCollisions(dinosaur, new WorldLocation(4, 6)), empty());
      assertThat(systemUnderTest.calculateCollisions(dinosaur, new WorldLocation(5, 5)), hasSize(2));
   }
}
