
package uk.dangrew.dinosaurs.ui.stats;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.dangrew.dinosaurs.game.behaviour.consumption.ConsumptionWarnings.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.TestWorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.controls.PanningControls;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;

@ExtendWith(MockitoExtension.class)
public class StatPanelTest {
   
   private Dinosaur dinosaur;
   
   private GameState gameState;
   @Mock private PanningControls panningControls;
   private StatPanel systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      JavaFxThreading.startup();
      dinosaur = new Dinosaur("Dino");
      dinosaur.getWorldLocation().set(new TestWorldLocation(3, 4));
      gameState = new GameState();
      systemUnderTest = new StatPanel(panningControls, gameState);
   }
   
   @Test
   public void shouldConvertLocationToText() {
      assertThat(systemUnderTest.locationProperty().get(), equalTo("Unknown"));
      gameState.currentPlayer().set(dinosaur);
      assertThat(systemUnderTest.locationProperty().get(), equalTo("{3, 4}"));
   }
   
   @Test
   public void shouldConvertHungerToText() {
      assertThat(systemUnderTest.hungerProperty().get(), equalTo("Unknown"));
      dinosaur.hunger().set(10);
      gameState.currentPlayer().set(dinosaur);
      assertThat(systemUnderTest.hungerProperty().get(), equalTo(NO_WARNING.hungerName()));
      
      dinosaur.hunger().set(60);
      assertThat(systemUnderTest.hungerProperty().get(), equalTo(MILD.hungerName()));
      
      dinosaur.hunger().set(110);
      assertThat(systemUnderTest.hungerProperty().get(), equalTo(RUMBLY.hungerName()));
      
      dinosaur.hunger().set(160);
      assertThat(systemUnderTest.hungerProperty().get(), equalTo(HANGRY.hungerName()));
      
      dinosaur.hunger().set(210);
      assertThat(systemUnderTest.hungerProperty().get(), equalTo(AGGRESSIVE.hungerName()));
   }
   
   @Test
   public void shouldConvertThirstToText() {
      assertThat(systemUnderTest.thirstProperty().get(), equalTo("Unknown"));
      dinosaur.thirst().set(10);
      gameState.currentPlayer().set(dinosaur);
      assertThat(systemUnderTest.thirstProperty().get(), equalTo(NO_WARNING.thirstName()));
      
      dinosaur.thirst().set(60);
      assertThat(systemUnderTest.thirstProperty().get(), equalTo(MILD.thirstName()));
      
      dinosaur.thirst().set(110);
      assertThat(systemUnderTest.thirstProperty().get(), equalTo(RUMBLY.thirstName()));
      
      dinosaur.thirst().set(160);
      assertThat(systemUnderTest.thirstProperty().get(), equalTo(HANGRY.thirstName()));
      
      dinosaur.thirst().set(210);
      assertThat(systemUnderTest.thirstProperty().get(), equalTo(AGGRESSIVE.thirstName()));
   }
}
