package uk.dangrew.dinosaurs.ui.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import uk.dangrew.dinosaurs.game.actions.GameAction;
import uk.dangrew.dinosaurs.game.mechanism.GameEngine;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;

@ExtendWith(MockitoExtension.class)
public class ActionListTest {
   
   @Mock private GameEngine gameEngine;
   private ObservableList<GameAction> gameActions;
   private ActionList systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      JavaFxThreading.startup();
      gameActions = FXCollections.observableArrayList();
      when(gameEngine.availableActions()).thenReturn(gameActions);
      systemUnderTest = new ActionList(gameEngine);
   }
   
   @Test
   public void shouldAddButtonsAtBottom() {
      GameAction gameAction = mock(GameAction.class);
      when(gameAction.describe()).thenReturn("some description");
      gameActions.add(gameAction);

      Button button = systemUnderTest.buttonFor(gameAction);
      assertThat(systemUnderTest.getChildren().contains(button), equalTo(true));
      assertThat(button.getText(), equalTo(gameAction.describe()));
 
      button.getOnAction().handle(mock(ActionEvent.class));
      verify(gameEngine).executePlayerAction(gameAction);
   }
   
   @Test
   public void shouldRemoveButtons() {
      GameAction gameAction = mock(GameAction.class);
      GameAction gameAction2 = mock(GameAction.class);
      gameActions.add(gameAction);
      gameActions.add(gameAction2);

      Button button = systemUnderTest.buttonFor(gameAction);
      assertThat(systemUnderTest.getChildren().contains(button), equalTo(true));
      Button button2 = systemUnderTest.buttonFor(gameAction2);
      assertThat(systemUnderTest.getChildren().contains(button2), equalTo(true));
      
      gameActions.remove(gameAction);
      assertThat(systemUnderTest.getChildren().contains(button), equalTo(false));
      assertThat(systemUnderTest.getChildren().contains(button2), equalTo(true));
   }
   
   @Test
   public void shouldDisableButtonsWhenActionIsUnavailable() {
      GameAction gameAction = mock(GameAction.class);
      when(gameAction.isAvailable()).thenReturn(false);
      gameActions.add(gameAction);

      Button button = systemUnderTest.buttonFor(gameAction);
      assertThat(systemUnderTest.getChildren().contains(button), equalTo(true));
      assertThat(button.isDisable(), equalTo(true));
   }
   
}