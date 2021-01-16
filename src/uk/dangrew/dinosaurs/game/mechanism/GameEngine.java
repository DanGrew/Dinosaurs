package uk.dangrew.dinosaurs.game.mechanism;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uk.dangrew.dinosaurs.game.actions.GameAction;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.kode.observable.PrivatelyModifiableObservableListImpl;

/**
 * Core object in the game, responsible for the game mechanics and assets. Does not care for the ui.
 */
public class GameEngine {
   
   private final AssetManager assetManager;
   private final ActionProcessor actionProcessor;

   private final ObservableList<GameAction> availableActions;
   private final ObservableList<GameAction> privatelyManagedAvailableActions;
   
   private final GameState gameState;
   
   public GameEngine(GameState gameState) {
      this(gameState, new AssetManager());
   }
   
   private GameEngine(GameState gameState, AssetManager assetManager){
      this(gameState, assetManager, new ActionProcessor(gameState, assetManager));
   }
   
   GameEngine(GameState gameState, AssetManager assetManager, ActionProcessor actionProcessor){
      this.gameState = gameState;
      this.assetManager = assetManager;
      this.actionProcessor = actionProcessor;

      this.availableActions = FXCollections.observableArrayList();
      this.privatelyManagedAvailableActions = new PrivatelyModifiableObservableListImpl<>(availableActions);
   }
   
   public AssetManager getAssetManager() {
      return assetManager;
   }
   
   public ActionProcessor getActionProcessor() {
      return actionProcessor;
   }

   /**
    * Provides access to the changing available actions. Note that this is read only.
    * @return the action available, can be registered on.
    */
   public ObservableList<GameAction> availableActions(){
      return privatelyManagedAvailableActions;
   }

   private void updateAvailableActions(Collection<GameAction> updatedActions){
      this.availableActions.clear();
      this.availableActions.addAll(updatedActions);
   }

   public void begin() {
      assetManager.buildWorld1();
      gameState.currentWorld().set(assetManager.getWorld());
      gameState.currentPlayer().set(assetManager.getPlayer());
      updatePlayerActions();
   }
   
   public void executePlayerAction(GameAction gameAction) {
      gameAction.performAction();
      updatePlayerActions();
   }
   
   private void updatePlayerActions() {
      Collection<GameAction> availableActions = actionProcessor.calculateAvailableActions();
      updateAvailableActions(availableActions);
   }
}
