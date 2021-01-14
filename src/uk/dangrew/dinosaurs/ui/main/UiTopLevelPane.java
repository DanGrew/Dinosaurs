

package uk.dangrew.dinosaurs.ui.main;

import javafx.scene.layout.GridPane;
import uk.dangrew.dinosaurs.game.mechanism.CameraController;
import uk.dangrew.dinosaurs.game.mechanism.GameEngine;
import uk.dangrew.dinosaurs.ui.controller.PlayerController;
import uk.dangrew.dinosaurs.ui.world.UiWorld;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

public class UiTopLevelPane extends GridPane {

   public UiTopLevelPane(UiWorld uiWorld, GameEngine gameEngine, CameraController cameraController) {
      new JavaFxStyle().configureConstraintsForColumnPercentages(this, 80, 20);
      
      add(uiWorld, 0, 0);
      add(new PlayerController(gameEngine, cameraController), 1, 0);
   }
}
