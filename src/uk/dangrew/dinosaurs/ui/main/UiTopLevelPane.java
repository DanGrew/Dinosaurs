

package uk.dangrew.dinosaurs.ui.main;

import javafx.scene.layout.GridPane;
import uk.dangrew.dinosaurs.game.mechanism.GameEngine;
import uk.dangrew.dinosaurs.ui.controller.PlayerController;
import uk.dangrew.dinosaurs.ui.view.CameraController;
import uk.dangrew.dinosaurs.ui.view.GameContent;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

public class UiTopLevelPane extends GridPane {

   public UiTopLevelPane(GameEngine gameEngine, CameraController cameraController, GameContent gameContent) {
      new JavaFxStyle().configureConstraintsForColumnPercentages(this, 70, 30);
      add(gameContent, 0, 0);
      add(new PlayerController(gameEngine, cameraController), 1, 0);
   }

}