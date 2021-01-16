

package uk.dangrew.dinosaurs.ui.widgets;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;

public class WaterWidgetTest {
   
   private GameState gameState;
   private Water water;
   private WorldViewport worldViewport;
   private WaterWidget systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      systemUnderTest = new WaterWidget(gameState, water, worldViewport);
   }
   
   @Test
   public void shouldDrawLocationsInViewOnly() {
      fail();
   }

   @Test
   public void shouldHandleLocationSplitOver4CornersOfScreen() {
      //grid 20x20 = size of view port, pan around until split over 4 corners.
      fail();
   }
}
