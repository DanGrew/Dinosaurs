
package uk.dangrew.dinosaurs.game.storage;

import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;
import uk.dangrew.dinosaurs.ui.widgets.WorldWidget;
import uk.dangrew.kode.storage.structure.MappedObservableStoreManagerImpl;

public class WorldStore extends MappedObservableStoreManagerImpl<String, World> implements AssetStore<World, WorldWidget> {
   
   public WorldStore() {
      super(concept -> concept.properties().id());
   }
   
   @Override
   public World createConcept(String name) {
      World concept = new World(name);
      store(concept);
      return concept;
   }
   
   @Override
   public World createConcept(String id, String name) {
      World concept = new World(id, name);
      store(concept);
      return concept;
   }
   
   @Override
   public void removeConcept(World concept) {
      remove(concept.properties().id());
   }
   
   @Override
   public WorldWidget createWidget(GameState gameState, WorldViewport worldViewport, String id) {
      World concept = get(id);
      WorldWidget widget = new WorldWidget(gameState, concept, worldViewport);
      return widget;
   }
}
