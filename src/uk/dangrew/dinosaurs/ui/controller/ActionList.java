package uk.dangrew.dinosaurs.ui.controller;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.mechanism.GameEngine;
import uk.dangrew.kode.javafx.style.JavaFxStyle;
import uk.dangrew.kode.observable.FunctionListChangeListenerImpl;

/**
 * Provides a graphical display of all actions available to the player.
 */
public class ActionList extends GridPane {
   
   private final GameEngine gameEngine;
   private final Map<GameAction, Button> actionButtons;
   
   public ActionList(GameEngine gameEngine){
      this.gameEngine = gameEngine;
      this.actionButtons = new HashMap<>();

      JavaFxStyle styling = new JavaFxStyle();
      styling.configureConstraintsForEvenRows(this, 20);
      styling.configureConstraintsForColumnPercentages(this, 100);
      
      gameEngine.availableActions().addListener(new FunctionListChangeListenerImpl<>(
            this::addGameActionAtBottom, this::removeGameAction
      ));
   }
   
   private void addGameActionAtBottom(GameAction gameAction){
      Button actionButton = new Button(gameAction.describe());
      actionButton.setTextAlignment(TextAlignment.CENTER);
      actionButton.setMaxWidth(Double.MAX_VALUE);
      actionButton.setMaxHeight(Double.MAX_VALUE);
      actionButton.setOnAction(e -> gameEngine.executePlayerAction(gameAction));
      actionButtons.put(gameAction, actionButton);
      actionButton.setDisable(!gameAction.isAvailable());
      add(actionButton, 0, actionButtons.size());
   }

   private void removeGameAction(GameAction gameAction){
      Button actionButton = actionButtons.remove(gameAction);
      getChildren().remove(actionButton);
   }
   
   Button buttonFor(GameAction gameAction){
      return actionButtons.get(gameAction);
   }
}