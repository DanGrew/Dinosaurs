
package uk.dangrew.dinosaurs.game.storage;

import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.widgets.WaterWidget;
import uk.dangrew.dinosaurs.ui.world.WorldViewport;
import uk.dangrew.kode.storage.structure.MappedObservableStoreManagerImpl;

public class WaterStore extends MappedObservableStoreManagerImpl<String, Water> implements AssetStore<Water, WaterWidget> {
   
   public WaterStore() {
      super(water -> water.properties().id());
   }
   
   @Override
   public Water createConcept(String name) {
      Water water = new Water(name);
      store(water);
      return water;
   }
   
   @Override
   public Water createConcept(String id, String name) {
      Water water = new Water(id, name);
      store(water);
      return water;
   }
   
   @Override
   public void removeConcept(Water concept) {
      remove(concept.properties().id());
   }
   
   @Override
   public WaterWidget createWidget(DinosaursConfiguration dinosaursConfiguration, WorldViewport worldViewport, String id) {
      Water water = get(id);
      WaterWidget widget = new WaterWidget(dinosaursConfiguration, water, worldViewport);
      return widget;
   }
}
