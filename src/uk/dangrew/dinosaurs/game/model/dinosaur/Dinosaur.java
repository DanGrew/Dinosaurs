
package uk.dangrew.dinosaurs.game.model.dinosaur;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import uk.dangrew.dinosaurs.game.collision.CollisionDetector;
import uk.dangrew.dinosaurs.game.collision.NoCollisions;
import uk.dangrew.dinosaurs.game.storage.Asset;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.concept.Properties;

public class Dinosaur implements Asset {
   
   private final Properties properties;
   private final int height;
   private final CollisionDetector collisionDetector;
   private final InteractionZone interactionZone;
   
   private ObjectProperty<WorldLocation> worldLocation;
   
   public Dinosaur(String name) {
      this(name, name);
   }
   
   public Dinosaur(String id, String name) {
      this(new Properties(id, name));
   }
   
   Dinosaur(Properties properties) {
      this.properties = properties;
      this.collisionDetector = new NoCollisions();
      this.height = 1;
      this.interactionZone = new InteractionZone(this, 1);
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

   @Override
   public CollisionDetector getCollisionDetector() {
      return collisionDetector;
   }

   public InteractionZone getInteractionZone() {
      return interactionZone;
   }

   public ObjectProperty<WorldLocation> getWorldLocation() {
      return worldLocation;
   }

   public WorldLocation expectLocation(){
      WorldLocation worldLocation = this.worldLocation.get();
      if (worldLocation == null) {
         throw new IllegalStateException("Location expected but not available.");
      }
      return worldLocation;
   }

   public int getHeight() {
      return height;
   }

   public void moveUp(World world) {
      move(0, -1, world);
   }

   public void moveDown(World world) {
      move(0, 1, world);
   }

   public void moveRight(World world) {
      move(1, 0, world);
   }

   public void moveLeft(World world) {
      move(-1, 0, world);
   }

   private void move(int horizontalMovement, int verticalMovement, World world) {
      WorldLocation location = worldLocation.get();
      worldLocation.set(location.translate(horizontalMovement, verticalMovement, world));
   }
}
