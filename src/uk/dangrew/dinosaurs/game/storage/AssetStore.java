
package uk.dangrew.dinosaurs.game.storage;

import java.util.Collection;
import java.util.stream.Collectors;

import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.world.WorldViewport;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.concept.ConceptStore;

/**
 * Extension to the concept store interfce to provide additional control for widgets.
 * @param <AssetTypeT>
 * @param <WidgetTypeT>
 */
public interface AssetStore <AssetTypeT extends Concept, WidgetTypeT extends AssetWidget> extends ConceptStore<AssetTypeT> {
   
   public WidgetTypeT createWidget(DinosaursConfiguration dinosaursConfiguration, WorldViewport worldViewport, String id);
   
   public default Collection<WidgetTypeT> createAllWidgets(DinosaursConfiguration dinosaursConfiguration, WorldViewport worldViewport) {
      return objectList().stream()
            .map(asset -> createWidget(dinosaursConfiguration, worldViewport, asset.properties().id()))
            .collect(Collectors.toList());
   }
}
