
package uk.dangrew.dinosaurs.game.model.dinosaur;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.game.world.World;

public class DinosaurTest {
   
   private World world;
   private Dinosaur systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      world = new World("World");
      world.setDimension(30, 30);
      systemUnderTest = new Dinosaur("Steggy");
   }
   
   @Test
   public void shouldProvideLocation() {
      assertThat(systemUnderTest.getWorldLocation().get(), nullValue());
      systemUnderTest.getWorldLocation().set(new TestWorldLocation(20, 21));
      assertThat(systemUnderTest.getWorldLocation().get(), equalTo(new TestWorldLocation(20, 21)));
   }
   
   @Test
   public void shouldMoveDinosaur() {
      systemUnderTest.getWorldLocation().set(new TestWorldLocation(10, 11));
      
      systemUnderTest.moveUp(world);
      assertThat(systemUnderTest.getWorldLocation().get(), equalTo(new TestWorldLocation(10, 10)));

      systemUnderTest.moveRight(world);
      assertThat(systemUnderTest.getWorldLocation().get(), equalTo(new TestWorldLocation(11, 10)));

      systemUnderTest.moveDown(world);
      assertThat(systemUnderTest.getWorldLocation().get(), equalTo(new TestWorldLocation(11, 11)));

      systemUnderTest.moveLeft(world);
      assertThat(systemUnderTest.getWorldLocation().get(), equalTo(new TestWorldLocation(10, 11)));
   }

}