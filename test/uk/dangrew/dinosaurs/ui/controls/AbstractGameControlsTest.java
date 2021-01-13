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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
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
import uk.dangrew.kode.javafx.controls.DirectionControlType;
import uk.dangrew.kode.javafx.keyboard.KeyBoardCapture;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;
import uk.dangrew.kode.utility.event.TestKeyEvent;

@ExtendWith(MockitoExtension.class)
public class AbstractGameControlsTest {
   
   @Captor private ArgumentCaptor<Consumer<KeyEvent>> pressedCaptor;
   @Captor private ArgumentCaptor<Consumer<KeyEvent>> releasedCaptor;
   
   @Mock private KeyBoardCapture keyBoardCapture;
   private AbstractGameControls systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      JavaFxThreading.startup();
      systemUnderTest = new AbstractGameControls(keyBoardCapture) {
         
         @Override
         protected void customKeyHandler(KeyEvent event) {
            //do nothing
         }
      };
   }
   
   @Test
   public void shouldNotBeFocusable() {
      for (DirectionControlType value : DirectionControlType.values()) {
         assertThat(systemUnderTest.buttonFor(value).isFocusTraversable(), equalTo(false));
      }
   }
   
   @Test
   public void shouldManageWhetherShiftIsHeld() {
      verify(keyBoardCapture).capture(eq(KeyEvent.KEY_PRESSED), pressedCaptor.capture());
      verify(keyBoardCapture).capture(eq(KeyEvent.KEY_RELEASED), releasedCaptor.capture());
      
      assertThat(systemUnderTest.isShiftHeld(), equalTo(false));
      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyEvent.KEY_PRESSED, KeyCode.SHIFT));
      assertThat(systemUnderTest.isShiftHeld(), equalTo(true));

      pressedCaptor.getValue().accept(new TestKeyEvent().build(KeyEvent.KEY_RELEASED, KeyCode.SHIFT));
      assertThat(systemUnderTest.isShiftHeld(), equalTo(false));
   }
   
}
