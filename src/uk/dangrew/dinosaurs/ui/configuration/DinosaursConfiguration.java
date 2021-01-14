
package uk.dangrew.dinosaurs.ui.configuration;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;

public class DinosaursConfiguration {

   public static final int DEFAULT_WORLD_CELL_DIMENSION = 30;
   
   private final ObjectProperty<World> currentWorld;
   private final ObjectProperty<Dinosaur> currentPlayer;
   
   private final ObjectProperty<Integer> worldCellDimension;
   private final ObjectProperty<Integer> worldCellRadius;
   
   public DinosaursConfiguration(){
      this.currentWorld = new SimpleObjectProperty<>();
      this.currentPlayer = new SimpleObjectProperty<>();
      
      this.worldCellDimension = new SimpleObjectProperty<>();
      this.worldCellRadius = new SimpleObjectProperty<>();
      
      worldCellDimension.addListener((s,o,n) -> worldCellRadius.set(n / 2));
      worldCellDimension.set(DEFAULT_WORLD_CELL_DIMENSION);
   }

   public ObjectProperty<World> currentWorld() {
      return currentWorld;
   }

   public ObjectProperty<Dinosaur> currentPlayer() {
      return currentPlayer;
   }

   public ObjectProperty<Integer> worldCellDimension() {
      return worldCellDimension;
   }

   public ReadOnlyObjectProperty<Integer> worldCellRadius() {
      return worldCellRadius;
   }
}