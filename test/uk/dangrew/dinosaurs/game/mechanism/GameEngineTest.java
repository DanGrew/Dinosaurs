package uk.dangrew.dinosaurs.game.mechanism;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.dangrew.dinosaurs.game.actions.mechanism.GameAction;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.ui.configuration.GameState;

@ExtendWith(MockitoExtension.class)
public class GameEngineTest {
   
   private AssetManager assetManager;
   @Mock private ActionProcessor actionProcessor;
   private List<GameAction> gameActions;
   
   private GameEngine systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      assetManager = new AssetManager();
      systemUnderTest = new GameEngine(new GameState(), assetManager, actionProcessor);
      
      gameActions = asList(mock(GameAction.class), mock(GameAction.class));
      when(actionProcessor.calculateAvailableActions()).thenReturn(gameActions);
   }
   
   @Test
   public void shouldBeginGame() {
      systemUnderTest.begin();
      assertThat(systemUnderTest.availableActions(), contains(gameActions.get(0), gameActions.get(1)));
   }
   
   @Test
   public void shouldExecutePlayerAction() {
      GameAction gameAction = mock(GameAction.class);
      systemUnderTest.executePlayerAction(gameAction);
      
      verify(gameAction).performAction();
      assertThat(systemUnderTest.availableActions(), contains(gameActions.get(0), gameActions.get(1)));
   }
   
   @Test
   public void shouldProcessDinosaurBehaviour() {
      Dinosaur dinosaur = spy(new Dinosaur("Dino"));
      assetManager.getDinosaurStore().store(dinosaur);
      
      systemUnderTest.executePlayerAction(mock(GameAction.class));
      verify(dinosaur).behave();
   }
}