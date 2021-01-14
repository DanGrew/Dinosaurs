
package uk.dangrew.dinosaurs.game.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.world.GameContent;
import uk.dangrew.dinosaurs.ui.world.WorldViewport;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.observable.FunctionListChangeListenerImpl;

/**
 * Manager for widgets associated with assets, creating them and destroying them as appropriate.
 * @param <ConceptTypeT>
 * @param <WidgetTypeT>
 */
public class WidgetManager <ConceptTypeT extends Concept, WidgetTypeT extends AssetWidget> {
   
   private final GameContent gameContent;
   private final DinosaursConfiguration dinosaursConfiguration;
   private final WorldViewport worldViewport;
   
   private final AssetStore<ConceptTypeT, WidgetTypeT> assetStore;
   private final Map<ConceptTypeT, WidgetTypeT> widgets;
   
   public WidgetManager(
         GameContent gameContent,
         DinosaursConfiguration dinosaursConfiguration,
         WorldViewport worldViewport,
         AssetStore<ConceptTypeT, WidgetTypeT> assetStore) {
      
      this.widgets = new HashMap<>();
      this.gameContent = gameContent;
      this.dinosaursConfiguration = dinosaursConfiguration;
      this.worldViewport = worldViewport;
      this.assetStore = assetStore;
      
      assetStore.objectList().addListener(new FunctionListChangeListenerImpl<>(
            this::conceptCreated, this::conceptRemoved));
   }
   
   private void conceptCreated(ConceptTypeT concept) {
      WidgetTypeT widget = assetStore.createWidget(dinosaursConfiguration, worldViewport, concept.properties().id());
      Optional.ofNullable(dinosaursConfiguration.currentWorld().get()).ifPresent(unused -> widget.redraw());
      gameContent.addWidget(widget);
      widgets.put(concept, widget);
   }
   
   private void conceptRemoved(ConceptTypeT concept) {
      WidgetTypeT widget = widgets.remove(concept);
      widget.destroy();
      gameContent.removeWidget(widget);
   }
   
}
