package uk.dangrew.dinosaurs.game.storage;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.view.GameContent;
import uk.dangrew.dinosaurs.ui.view.WorldViewport;
import uk.dangrew.dinosaurs.ui.widgets.DinosaurWidget;
import uk.dangrew.dinosaurs.ui.widgets.WidgetManager;

@ExtendWith(MockitoExtension.class)
public class WidgetManagerTest {
   
   @Mock private GameContent gameContent;
   private DinosaursConfiguration dinosaursConfiguration;
   @Mock private WorldViewport worldViewport;
   @Mock private AssetStore<Dinosaur, DinosaurWidget> dinosaurStore;
   private ObservableList<Dinosaur> dinosaurs;
   
   @Mock private DinosaurWidget dinosaurWidget;
   
   private WidgetManager<Dinosaur, DinosaurWidget> systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      dinosaursConfiguration = new DinosaursConfiguration();
      dinosaurs = FXCollections.observableArrayList();
      when(dinosaurStore.objectList()).thenReturn(dinosaurs);
      when(dinosaurStore.createWidget(dinosaursConfiguration, worldViewport, "Steggy")).thenReturn(dinosaurWidget);
      
      systemUnderTest = new WidgetManager<>(gameContent, dinosaursConfiguration, worldViewport, dinosaurStore);
   }
   
   @Test
   public void shouldCreateWidgetWhenConceptAdded() {
      dinosaurs.add(new Dinosaur("Steggy"));
      
      verify(gameContent).addWidget(dinosaurWidget);
      verify(dinosaurWidget, never()).redraw();
   }
   
   @Test
   public void shouldDrawWidgetWhenCreatedAndWorldAvailable() {
      dinosaursConfiguration.currentWorld().set(new World("World"));
      dinosaurs.add(new Dinosaur("Steggy"));

      verify(gameContent).addWidget(dinosaurWidget);
      verify(dinosaurWidget).redraw();
   }
   
   @Test
   public void shouldRemoveWidgetWhenConceptRemoved() {
      dinosaurs.add(new Dinosaur("Steggy"));
      dinosaurs.remove(0);
      
      verify(dinosaurWidget).destroy();
      verify(gameContent).removeWidget(dinosaurWidget);
   }
}