package uk.dangrew.dinosaurs.game.mechanism;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.dangrew.dinosaurs.ui.view.CameraController;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;

@ExtendWith(MockitoExtension.class)
public class CameraControllerTest {
   
   @Mock private WorldViewport worldViewport;
   private CameraController systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      systemUnderTest = new CameraController(worldViewport);
   }
   
   @Test
   public void shouldForwardPanInstructions() {
      systemUnderTest.panRight();
      verify(worldViewport).panRight();

      systemUnderTest.panDown();
      verify(worldViewport).panDown();

      systemUnderTest.panLeft();
      verify(worldViewport).panLeft();

      systemUnderTest.panUp();
      verify(worldViewport).panUp();
   }
}