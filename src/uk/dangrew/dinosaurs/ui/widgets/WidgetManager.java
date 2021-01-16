
package uk.dangrew.dinosaurs.ui.widgets;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import uk.dangrew.dinosaurs.game.storage.Asset;
import uk.dangrew.dinosaurs.game.storage.AssetStore;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.GameContent;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;
import uk.dangrew.kode.observable.FunctionListChangeListenerImpl;

/**
 * Manager for widgets associated with assets, creating them and destroying them as appropriate.
 * @param <ConceptTypeT>
 * @param <WidgetTypeT>
 */
public class WidgetManager <ConceptTypeT extends Asset, WidgetTypeT extends AssetWidget> {
   
   private final GameContent gameContent;
   private final GameState gameState;
   private final WorldViewport worldViewport;
   
   private final AssetStore<ConceptTypeT, WidgetTypeT> assetStore;
   private final Map<ConceptTypeT, WidgetTypeT> widgets;
   
   public WidgetManager(
         GameContent gameContent,
         GameState gameState,
         WorldViewport worldViewport,
         AssetStore<ConceptTypeT, WidgetTypeT> assetStore) {
      
      this.widgets = new HashMap<>();
      this.gameContent = gameContent;
      this.gameState = gameState;
      this.worldViewport = worldViewport;
      this.assetStore = assetStore;
      
      assetStore.objectList().addListener(new FunctionListChangeListenerImpl<>(
            this::conceptCreated, this::conceptRemoved));
   }
   
   private void conceptCreated(ConceptTypeT concept) {
      WidgetTypeT widget = assetStore.createWidget(gameState, worldViewport, concept.properties().id());
      Optional.ofNullable(gameState.currentWorld().get()).ifPresent(unused -> widget.redraw());
      gameContent.addWidget(widget);
      widgets.put(concept, widget);
   }
   
   private void conceptRemoved(ConceptTypeT concept) {
      WidgetTypeT widget = widgets.remove(concept);
      widget.destroy();
      gameContent.removeWidget(widget);
   }
   
}
