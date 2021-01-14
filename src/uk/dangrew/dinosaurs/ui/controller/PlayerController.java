package uk.dangrew.dinosaurs.ui.controller;

import javafx.scene.layout.GridPane;
import uk.dangrew.dinosaurs.game.mechanism.CameraController;
import uk.dangrew.dinosaurs.game.mechanism.GameEngine;
import uk.dangrew.dinosaurs.ui.controls.PanningControls;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

/**
 * Ui component providing a controller for the player.
 */
public class PlayerController extends GridPane {
   
   public PlayerController(GameEngine gameController, CameraController cameraController){
      JavaFxStyle styling = new JavaFxStyle();
      styling.configureConstraintsForRowPercentages(this, 20, 80);
      styling.configureConstraintsForColumnPercentages(this, 100);
      add(new PanningControls(cameraController), 0, 0);
      add(new ActionList(gameController), 0, 1);
   }
}