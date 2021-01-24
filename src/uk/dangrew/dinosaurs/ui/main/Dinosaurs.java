/*
 * ----------------------------------------
 *      Nutrient Usage Tracking System
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */

package uk.dangrew.dinosaurs.ui.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import uk.dangrew.dinosaurs.game.mechanism.GameEngine;
import uk.dangrew.dinosaurs.game.mechanism.GraphicalEngine;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.GameContent;
import uk.dangrew.kode.javafx.keyboard.KeyBoardCapture;

/**
 * Entry point to the system for launching.
 */
public class Dinosaurs extends Application {
   
   static final String TITLE = "Dinosaurs!";
   
   private static KeyBoardCapture KEY_BOARD_CAPTURE;
   
   public Dinosaurs() {
   }
   
   public static KeyBoardCapture keyBoard() {
      return KEY_BOARD_CAPTURE;
   }
   
   @Override
   public void start(Stage stage) {
      stage.setTitle(TITLE);
      stage.setOnCloseRequest(event -> {
         Platform.exit();
         System.exit(0);
      });
      
      BorderPane wrapper = new BorderPane();
      Scene scene = new Scene(wrapper);
      KEY_BOARD_CAPTURE = new KeyBoardCapture(scene);
      stage.setScene(scene);

      GameState gameState = new GameState();
      GameEngine gameEngine = new GameEngine(gameState);
      GameContent gameContent = new GameContent();
      GraphicalEngine graphicalEngine = new GraphicalEngine(gameContent, gameState, gameEngine.getAssetManager());
      UiTopLevelPane topLevelPane = new UiTopLevelPane(gameState, gameEngine, graphicalEngine.getCameraController(), gameContent);

      wrapper.setCenter(topLevelPane);
      stage.show();
      
      gameEngine.begin();
   }//End Method
   
   public static void main(String[] args) {
      launch();
   }//End Method
   
}//End Class
