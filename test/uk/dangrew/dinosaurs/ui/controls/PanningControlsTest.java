// *************************************************************
//                         H*E*R*M*E*S
//                   Holistic Environment for
//         Railway Modelling, Evaluation and Simulation
//        Built on Graffica System Development Kit: GSDK
//
//       Copyright: (c) Graffica Ltd (www.graffica.co.uk)
//
// This software is made available under binary licence. Holding 
// source code without the express permission of Graffica Ltd is 
//           not permitted under any circumstances. 
// *************************************************************

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
import uk.dangrew.dinosaurs.ui.world.WorldViewport;
import uk.dangrew.kode.javafx.keyboard.KeyBoardCapture;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;
import uk.dangrew.kode.utility.event.TestKeyEvent;

@ExtendWith(MockitoExtension.class)
public class PanningControlsTest {
   
   @Captor private ArgumentCaptor<Consumer<KeyEvent>> pressedCaptor;
   @Captor private ArgumentCaptor<Consumer<KeyEvent>> releasedCaptor;
   
   @Mock private KeyBoardCapture keyBoardCapture;
   
   @Mock private WorldViewport worldViewport;
   private PanningControls systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      JavaFxThreading.startup();
      systemUnderTest = new PanningControls(keyBoardCapture, worldViewport);
   }
   
   @Test
   public void shouldPanViewport() {
      verify(keyBoardCapture).capture(eq(KeyEvent.KEY_PRESSED), pressedCaptor.capture());

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.SHIFT));
      
      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.RIGHT));
      verify(worldViewport).moveRight();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.LEFT));
      verify(worldViewport).moveLeft();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.UP));
      verify(worldViewport).moveUp();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.DOWN));
      verify(worldViewport).moveDown();
   }
   
   @Test
   public void shouldNotMoveDinosuarIfNotUsingShift() {
      verify(keyBoardCapture).capture(eq(KeyEvent.KEY_PRESSED), pressedCaptor.capture());
      verify(keyBoardCapture).capture(eq(KeyEvent.KEY_RELEASED), releasedCaptor.capture());

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.RIGHT));
      verify(worldViewport, never()).moveRight();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.LEFT));
      verify(worldViewport, never()).moveLeft();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.UP));
      verify(worldViewport, never()).moveUp();

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyCode.DOWN));
      verify(worldViewport, never()).moveDown();

      releasedCaptor.getValue().accept(new TestKeyEvent().build(KeyEvent.KEY_PRESSED,KeyCode.SHIFT));

      pressedCaptor.getValue().accept(new TestKeyEvent().build( KeyCode.RIGHT));
      verify(worldViewport).moveRight();
   }
   
}
