
package uk.dangrew.dinosaurs.game.storage;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;
import uk.dangrew.dinosaurs.ui.widgets.DinosaurWidget;
import uk.dangrew.kode.storage.structure.MappedObservableStoreManagerImpl;

public class DinosaurStore extends MappedObservableStoreManagerImpl<String, Dinosaur> implements AssetStore<Dinosaur, DinosaurWidget> {
   
   public DinosaurStore() {
      super(water -> water.properties().id());
   }
   
   @Override
   public Dinosaur createConcept(String name) {
      Dinosaur concept = new Dinosaur(name);
      store(concept);
      return concept;
   }
   
   @Override
   public Dinosaur createConcept(String id, String name) {
      Dinosaur concept = new Dinosaur(id, name);
      store(concept);
      return concept;
   }
   
   @Override
   public void removeConcept(Dinosaur concept) {
      remove(concept.properties().id());
   }
   
   @Override
   public DinosaurWidget createWidget(DinosaursConfiguration dinosaursConfiguration, WorldViewport worldViewport, String id) {
      Dinosaur concept = get(id);
      DinosaurWidget widget = new DinosaurWidget(dinosaursConfiguration, concept, worldViewport);
      return widget;
   }
}
