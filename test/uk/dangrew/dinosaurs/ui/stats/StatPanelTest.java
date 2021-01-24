package uk.dangrew.dinosaurs.ui.stats;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
      gameState = new GameState();
      systemUnderTest = new StatPanel(panningControls, gameState);
   }
   
   @Test
   public void shouldConvertLocationToText() {
      assertThat(systemUnderTest.locationProperty().get(), equalTo("Unknown"));
      dinosaur.getWorldLocation().set(new TestWorldLocation(3, 4));
      gameState.currentPlayer().set(dinosaur);
      assertThat(systemUnderTest.locationProperty().get(), equalTo("{3, 4}"));
   }
}