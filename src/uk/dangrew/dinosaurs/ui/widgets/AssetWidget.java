package uk.dangrew.dinosaurs.ui.widgets;

import javafx.scene.Node;

/**
 * Common interface for widgets representing assets in the game.
 */
public interface AssetWidget {
   
   public Node getGraphicalComponent();
   
   public void redraw();
   
   public void destroy();
}
