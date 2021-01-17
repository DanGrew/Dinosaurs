
package uk.dangrew.dinosaurs.game.storage;

import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.ui.configuration.GameState;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;
import uk.dangrew.dinosaurs.ui.widgets.TreeWidget;
import uk.dangrew.kode.storage.structure.MappedObservableStoreManagerImpl;

public class TreeStore extends MappedObservableStoreManagerImpl<String, Tree> implements AssetStore<Tree, TreeWidget> {
   
   public TreeStore() {
      super(water -> water.properties().id());
   }
   
   @Override
   public Tree createConcept(String name) {
      Tree concept = new Tree(name);
      store(concept);
      return concept;
   }
   
   @Override
   public Tree createConcept(String id, String name) {
      Tree concept = new Tree(id, name);
      store(concept);
      return concept;
   }
   
   @Override
   public void removeConcept(Tree concept) {
      remove(concept.properties().id());
   }
   
   @Override
   public TreeWidget createWidget(GameState gameState, WorldViewport worldViewport, String id) {
      Tree concept = get(id);
      TreeWidget widget = new TreeWidget(gameState, concept, worldViewport);
      return widget;
   }
}
