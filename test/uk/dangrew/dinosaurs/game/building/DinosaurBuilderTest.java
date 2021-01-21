package uk.dangrew.dinosaurs.game.building;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.ai.RoamingBehaviour;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.storage.DinosaurStore;
import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.game.world.World;

public class DinosaurBuilderTest {
   
   private World world;
   private DinosaurStore dinosaurStore;
   private DinosaurBuilder systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      world = new World("World");
      dinosaurStore = new DinosaurStore();
      systemUnderTest = new DinosaurBuilder(dinosaurStore, world);
   }
   
   @Test
   public void shouldCreateHerbivoreRoamer() {
      systemUnderTest.buildHerbivoreRoamer("Herby", new TestWorldLocation(2, 2));
      Dinosaur dinosaur = dinosaurStore.get("Herby");
      assertThat(dinosaur.getBehaviour().get(), instanceOf(RoamingBehaviour.class));
   }
}