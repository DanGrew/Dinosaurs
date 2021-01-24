

package uk.dangrew.dinosaurs.ui.main;

import javafx.scene.layout.GridPane;
import uk.dangrew.dinosaurs.game.mechanism.GameEngine;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.controller.PlayerController;
import uk.dangrew.dinosaurs.ui.stats.StatPanel;
import uk.dangrew.dinosaurs.ui.view.CameraController;
import uk.dangrew.dinosaurs.ui.view.GameContent;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

public class UiTopLevelPane extends GridPane {

   public UiTopLevelPane(GameState gameState, GameEngine gameEngine, CameraController cameraController, GameContent gameContent) {
      new JavaFxStyle().configureConstraintsForColumnPercentages(this, 20, 50, 30);
      add(new StatPanel(gameState, cameraController).getGraphicalComponent(), 0, 0);
      add(gameContent, 1, 0);
      add(new PlayerController(gameEngine), 2, 0);
   }

}