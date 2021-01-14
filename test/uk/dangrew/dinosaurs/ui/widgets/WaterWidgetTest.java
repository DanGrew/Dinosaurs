

package uk.dangrew.dinosaurs.ui.widgets;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;

public class WaterWidgetTest {
   
   private DinosaursConfiguration dinosaursConfiguration;
   private Water water;
   private WorldViewport worldViewport;
   private WaterWidget systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      systemUnderTest = new WaterWidget(dinosaursConfiguration, water, worldViewport);
   }
   
   @Test
   public void should() {
      fail();
   }
}
