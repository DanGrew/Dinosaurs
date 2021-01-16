
package uk.dangrew.dinosaurs.ui.view;

import javafx.beans.value.ChangeListener;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;

/**
 * Object that manages the viewport in relation to the players position, panning around to keep the player in the view.
 */
public class PlayerFocusController {
   
   private final GameState gameState;
   private final WorldViewport worldViewport;
   private final ChangeListener<WorldLocation> panToPlayer;
   
   public PlayerFocusController(GameState gameState, WorldViewport worldViewport) {
      this.gameState = gameState;
      this.worldViewport = worldViewport;
      this.panToPlayer = (s, o, n) -> panToPlayer();
      
      gameState.currentPlayer().addListener((s, o, n) -> refreshPlayerRegistration(o, n));
   }
   
   private void refreshPlayerRegistration(Dinosaur previous, Dinosaur current) {
      if (previous != null) {
         previous.getWorldLocation().removeListener(panToPlayer);
      }
      if (current != null) {
         current.getWorldLocation().addListener(panToPlayer);
      }
   }
   
   private void panToPlayer() {
      Dinosaur player = gameState.currentPlayer().get();
      if (player == null) {
         return;
      }
      
      focusRightEdge();
      focusLeftEdge();
      focusBottomEdge();
      focusTopEdge();
   }
   
   private void focusRightEdge(){
      World currentWorld = gameState.expectWorld();

      int playerRange = gameState.expectPlayer().getInteractionZone().getRange();
      int playerHorizontal = gameState.expectPlayer().getWorldLocation().get().getHorizontal();

      int rightMostPosition = Math.floorMod(playerHorizontal + playerRange, currentWorld.getHorizontalCellCount());
      int viewPointLeftEdge = worldViewport.topLeft().getHorizontal();

      int viewPortRightEdge = Math.floorMod(viewPointLeftEdge + worldViewport.numberOfCellsInViewDimension(), currentWorld.getHorizontalCellCount());
      if (rightMostPosition == viewPortRightEdge) {
         worldViewport.panRight();
      }
   }

   private void focusLeftEdge(){
      World currentWorld = gameState.expectWorld();

      int playerRange = gameState.expectPlayer().getInteractionZone().getRange();
      int playerHorizontal = gameState.expectPlayer().getWorldLocation().get().getHorizontal();

      int viewPointLeftEdge = worldViewport.topLeft().getHorizontal();

      int leftMostPosition = Math.floorMod(playerHorizontal - playerRange + 1, currentWorld.getHorizontalCellCount());
      if (leftMostPosition == viewPointLeftEdge) {
         worldViewport.panLeft();
      }
   }

   private void focusBottomEdge(){
      World currentWorld = gameState.expectWorld();

      int playerRange = gameState.expectPlayer().getInteractionZone().getRange();
      int playerVertical = gameState.expectPlayer().getWorldLocation().get().getVertical();

      int bottomMostPosition = Math.floorMod(playerVertical + playerRange, currentWorld.getVerticalCellCount());
      int viewPointTopEdge = worldViewport.topLeft().getVertical();

      int viewPortBottomEdge = Math.floorMod(viewPointTopEdge + worldViewport.numberOfCellsInViewDimension(), currentWorld.getVerticalCellCount());
      if (bottomMostPosition == viewPortBottomEdge) {
         worldViewport.panDown();
      }

   }

   private void focusTopEdge(){
      World currentWorld = gameState.expectWorld();

      int playerRange = gameState.expectPlayer().getInteractionZone().getRange();
      int playerVertical = gameState.expectPlayer().getWorldLocation().get().getVertical();

      int viewPointTopEdge = worldViewport.topLeft().getVertical();

      int topMostPosition = Math.floorMod(playerVertical - playerRange + 1, currentWorld.getVerticalCellCount());
      if (topMostPosition == viewPointTopEdge) {
         worldViewport.panUp();
      }
   }
}
