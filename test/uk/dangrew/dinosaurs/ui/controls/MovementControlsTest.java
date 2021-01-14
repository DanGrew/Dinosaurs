

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
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.kode.javafx.keyboard.KeyBoardCapture;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;
import uk.dangrew.kode.utility.event.TestKeyEvent;

@ExtendWith(MockitoExtension.class)
public class MovementControlsTest {
   
   @Captor private ArgumentCaptor<Consumer<KeyEvent>> pressedCaptor;
   @Captor private ArgumentCaptor<Consumer<KeyEvent>> releasedCaptor;
   
   @Mock private KeyBoardCapture keyBoardCapture;
   
   @Mock private Dinosaur dinosaur;
   @Mock private World world;
   private MovementControls systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      JavaFxThreading.startup();
      systemUnderTest = new MovementControls(keyBoardCapture, dinosaur, world);
   }
   
   @Test
   public void shouldMoveDinosaur() {
      verify(keyBoardCapture).capture(eq(KeyEvent.KEY_PRESSED), pressedCaptor.capture());
      
      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.RIGHT));
      verify(dinosaur).moveRight(world);

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.LEFT));
      verify(dinosaur).moveLeft(world);

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.UP));
      verify(dinosaur).moveUp(world);

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.DOWN));
      verify(dinosaur).moveDown(world);
   }
   
   @Test
   public void shouldNotMoveDinosuarIfUsingShift() {
      verify(keyBoardCapture).capture(eq(KeyEvent.KEY_PRESSED), pressedCaptor.capture());
      verify(keyBoardCapture).capture(eq(KeyEvent.KEY_RELEASED), releasedCaptor.capture());

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.SHIFT));

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.RIGHT));
      verify(dinosaur, never()).moveRight(world);

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.LEFT));
      verify(dinosaur, never()).moveLeft(world);

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.UP));
      verify(dinosaur, never()).moveUp(world);

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.DOWN));
      verify(dinosaur, never()).moveDown(world);

      releasedCaptor.getValue().accept(new TestKeyEvent().build(KeyEvent.KEY_RELEASED,KeyCode.SHIFT));

      pressedCaptor.getValue().accept(new TestKeyEvent().build( KeyCode.RIGHT));
      verify(dinosaur).moveRight(world);
   }
   
}
