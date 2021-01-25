
package uk.dangrew.dinosaurs.game.behaviour.consumption;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;

@ExtendWith(MockitoExtension.class)
public class ConsumptionBehaviourTest {
   
   @Mock private Random random;
   private Dinosaur dinosaur;
   private ConsumptionBehaviour systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      dinosaur = new Dinosaur("Danny");
      systemUnderTest = new ConsumptionBehaviour(random, dinosaur);
   }
   
   @Test
   public void shouldIncreaseHungerAndThirst() {
      when(random.nextInt(anyInt()))
            .thenReturn(10)
            .thenReturn(15);
      
      dinosaur.hunger().set(30);
      dinosaur.thirst().set(40);
      
      systemUnderTest.behave();
      assertThat(dinosaur.hunger().get(), equalTo(30 + 10));
      assertThat(dinosaur.thirst().get(), equalTo(40 + 15));
   }
   
   @Test
   public void shouldNotIncreaseHungerAndThirstBeyondMaximum() {
      when(random.nextInt(anyInt()))
            .thenReturn(20)
            .thenReturn(25);
      
      dinosaur.hunger().set(190);
      dinosaur.thirst().set(190);
      
      systemUnderTest.behave();
      assertThat(dinosaur.hunger().get(), equalTo(ConsumptionWarnings.maximum().limit()));
      assertThat(dinosaur.thirst().get(), equalTo(ConsumptionWarnings.maximum().limit()));
   }
}
