

package uk.dangrew.dinosaurs.ui.controls;

import java.util.HashMap;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uk.dangrew.kode.javafx.controls.DirectionControlType;
import uk.dangrew.kode.javafx.controls.DirectionControls;
import uk.dangrew.kode.javafx.keyboard.KeyBoardCapture;

/**
 * Provides a base implementation for the ui controlls allowing scrolling and movement.
 */
public abstract class AbstractGameControls extends DirectionControls {

   private boolean shiftHeld;
   
   protected AbstractGameControls(KeyBoardCapture keyBoardCapture) {
      super(new HashMap<>() {
         
         {
            this.put(DirectionControlType.Left, new FontAwesomeIconView(FontAwesomeIcon.ANGLE_DOUBLE_LEFT));
            this.put(DirectionControlType.Up, new FontAwesomeIconView(FontAwesomeIcon.ANGLE_DOUBLE_UP));
            this.put(DirectionControlType.Right, new FontAwesomeIconView(FontAwesomeIcon.ANGLE_DOUBLE_RIGHT));
            this.put(DirectionControlType.Down, new FontAwesomeIconView(FontAwesomeIcon.ANGLE_DOUBLE_DOWN));
         }
      });
      
      keyBoardCapture.capture(KeyEvent.KEY_PRESSED, this::keyHandler);
      keyBoardCapture.capture(KeyEvent.KEY_RELEASED, this::keyHandler);

      buttonFor(DirectionControlType.Up).setFocusTraversable(false);
      buttonFor(DirectionControlType.Down).setFocusTraversable(false);
      buttonFor(DirectionControlType.Right).setFocusTraversable(false);
      buttonFor(DirectionControlType.Left).setFocusTraversable(false);

   }

   private void keyHandler(KeyEvent event) {
      if (event.getCode() == KeyCode.SHIFT){
         if (event.getEventType() == KeyEvent.KEY_PRESSED){
            shiftHeld = true;
         } else if (event.getEventType() == KeyEvent.KEY_RELEASED){
            shiftHeld = false;
         }
      }

      if (event.getEventType() == KeyEvent.KEY_RELEASED){
         //should this really be here?
         return;
      }
      customKeyHandler(event);
   }
   
   protected boolean isShiftHeld(){
      return shiftHeld;
   }
   
   protected abstract void customKeyHandler(KeyEvent event);

   @Override
   protected Button buttonFor(DirectionControlType type) {
      return super.buttonFor(type);
   }
}
