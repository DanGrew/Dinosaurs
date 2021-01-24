package uk.dangrew.dinosaurs.ui.controller;

import javafx.scene.layout.BorderPane;
import uk.dangrew.dinosaurs.game.mechanism.GameEngine;

/**
 * Ui component providing a controller for the player.
 */
public class PlayerController extends BorderPane {
   
   public PlayerController(GameEngine gameController){
      setCenter(new ActionList(gameController));
   }
}