package uk.dangrew.dinosaurs.game.storage;

import uk.dangrew.dinosaurs.game.model.rubble.Rock;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;
import uk.dangrew.dinosaurs.ui.widgets.RockWidget;
import uk.dangrew.kode.storage.structure.MappedObservableStoreManagerImpl;

public class RockStore extends MappedObservableStoreManagerImpl<String, Rock> implements AssetStore<Rock, RockWidget> {
   
   public RockStore(){
      super(rock -> rock.properties().id());
   }

   @Override
   public Rock createConcept(String name) {
      Rock concept = new Rock(name);
      store(concept);
      return concept;
   }

   @Override
   public Rock createConcept(String id, String name) {
      Rock concept = new Rock(id, name);
      store(concept);
      return concept;
   }

   @Override
   public void removeConcept(Rock concept) {
      remove(concept.properties().id());
   }

   @Override
   public RockWidget createWidget(GameState gameState, WorldViewport worldViewport, String id) {
      Rock concept = get(id);
      RockWidget widget = new RockWidget(gameState, concept, worldViewport);
      return widget;
   }
}