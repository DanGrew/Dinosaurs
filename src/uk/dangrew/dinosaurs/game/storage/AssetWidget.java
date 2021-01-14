package uk.dangrew.dinosaurs.game.storage;

import javafx.scene.Node;

/**
 * Common interface for widgets representing assets in the game.
 */
public interface AssetWidget {
   
   public Node getGraphicalComponent();
   
   public void redraw();
   
   public void destroy();
}
