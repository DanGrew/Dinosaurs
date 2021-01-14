
package uk.dangrew.dinosaurs.game.model.dinosaur;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.concept.Properties;

public class Dinosaur implements Concept {
   
   private final Properties properties;
   private final int sightRadius;
   
   private ObjectProperty<WorldLocation> worldLocation;
   
   public Dinosaur(String name){
      this.properties = new Properties(name);
      this.sightRadius = 3;
      this.worldLocation = new SimpleObjectProperty<>();
   }

   @Override
   public Properties properties() {
      return properties;
   }

   @Override
   public Concept duplicate() {
      throw new UnsupportedOperationException();
   }

   public ObjectProperty<WorldLocation> getWorldLocation() {
      return worldLocation;
   }

   public int getSightRadius() {
      return sightRadius;
   }

   public void moveUp(World world) {
      move(0, 1, world);
   }

   public void moveDown(World world) {
      move(0, -1, world);
   }

   public void moveRight(World world) {
      move(1, 0, world);
   }

   public void moveLeft(World world) {
      move(-1, 0, world);
   }
   
   private void move(int horizontalMovement, int verticalMovement, World world){
      WorldLocation location = worldLocation.get();
      int horizontal = (location.getHorizontal() + horizontalMovement) % world.getHorizontalCellCount();
      int vertical = (location.getVertical() - verticalMovement) % world.getVerticalCellCount();
      worldLocation.set(new WorldLocation(horizontal, vertical));
   }
}