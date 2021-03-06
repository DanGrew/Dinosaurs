

package uk.dangrew.dinosaurs.ui.controls;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uk.dangrew.dinosaurs.ui.view.CameraController;
import uk.dangrew.kode.javafx.keyboard.KeyBoardCapture;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;
import uk.dangrew.kode.utility.event.TestKeyEvent;

@ExtendWith(MockitoExtension.class)
public class PanningControlsTest {
   
   @Captor private ArgumentCaptor<Consumer<KeyEvent>> pressedCaptor;
   @Captor private ArgumentCaptor<Consumer<KeyEvent>> releasedCaptor;
   
   @Mock private KeyBoardCapture keyBoardCapture;
   
   @Mock private CameraController cameraController;
   private PanningControls systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      JavaFxThreading.startup();
      systemUnderTest = new PanningControls(keyBoardCapture, cameraController);
   }
   
   @Test
   public void shouldPanViewport() {
      verify(keyBoardCapture).capture(eq(KeyEvent.KEY_PRESSED), pressedCaptor.capture());

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.SHIFT));
      
      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.RIGHT));
      verify(cameraController).panRight();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.LEFT));
      verify(cameraController).panLeft();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.UP));
      verify(cameraController).panUp();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.DOWN));
      verify(cameraController).panDown();
   }
   
   @Test
   public void shouldNotMoveDinosuarIfNotUsingShift() {
      verify(keyBoardCapture).capture(eq(KeyEvent.KEY_PRESSED), pressedCaptor.capture());
      verify(keyBoardCapture).capture(eq(KeyEvent.KEY_RELEASED), releasedCaptor.capture());

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.RIGHT));
      verify(cameraController, never()).panRight();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.LEFT));
      verify(cameraController, never()).panLeft();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.UP));
      verify(cameraController, never()).panUp();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.DOWN));
      verify(cameraController, never()).panDown();

      releasedCaptor.getValue().accept(new TestKeyEvent().build(KeyEvent.KEY_PRESSED,KeyCode.SHIFT));

      pressedCaptor.getValue().accept(new TestKeyEvent().build( KeyCode.RIGHT));
      verify(cameraController).panRight();
   }
   
}
