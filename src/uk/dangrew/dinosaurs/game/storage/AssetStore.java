
package uk.dangrew.dinosaurs.game.storage;

import java.util.Collection;
import java.util.stream.Collectors;

import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;
import uk.dangrew.dinosaurs.ui.widgets.AssetWidget;
import uk.dangrew.kode.concept.ConceptStore;

/**
 * Extension to the concept store interfce to provide additional control for widgets.
 * @param <AssetTypeT>
 * @param <WidgetTypeT>
 */
public interface AssetStore <AssetTypeT extends Asset, WidgetTypeT extends AssetWidget> extends ConceptStore<AssetTypeT> {
   
   public WidgetTypeT createWidget(GameState gameState, WorldViewport worldViewport, String id);
   
   public default Collection<WidgetTypeT> createAllWidgets(GameState gameState, WorldViewport worldViewport) {
      return objectList().stream()
            .map(asset -> createWidget(gameState, worldViewport, asset.properties().id()))
            .collect(Collectors.toList());
   }
}
