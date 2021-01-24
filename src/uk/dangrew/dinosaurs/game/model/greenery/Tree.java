
package uk.dangrew.dinosaurs.game.model.greenery;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import uk.dangrew.dinosaurs.game.actions.consumption.EatActionGenerator;
import uk.dangrew.dinosaurs.game.actions.mechanism.ActionGenerator;
import uk.dangrew.dinosaurs.game.collision.CollisionDetector;
import uk.dangrew.dinosaurs.game.collision.TreeCollisionDetector;
import uk.dangrew.dinosaurs.game.storage.Asset;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.concept.Properties;

/**
 * Asset representing a tree that can be eaten, provide an obstacle or be a hiding place.
 */
public class Tree implements Asset {
   
   private final Properties properties;
   private final CollisionDetector collisionDetector;
   private final ActionGenerator actionGenerator;
   
   private final ObjectProperty<Integer> foodAvailable;
   
   private int height;
   
   private WorldLocation worldLocation;
   
   public Tree(String name) {
      this(name, name);
   }
   
   public Tree(String id, String name) {
      this(new Properties(id, name));
   }
   
   Tree(Properties properties) {
      this.properties = properties;
      this.collisionDetector = new TreeCollisionDetector(this);
      this.actionGenerator = new EatActionGenerator(this);
      
      this.height = 1;
      this.foodAvailable = new SimpleObjectProperty<>(100);
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

   @Override
   public ActionGenerator getActionGenerator() {
      return actionGenerator;
   }

   public void setWorldLocation(WorldLocation worldLocation){
      this.worldLocation = worldLocation;
   }
   
   public WorldLocation getWorldLocation() {
      return worldLocation;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public int getHeight() {
      return height;
   }

   public ObjectProperty<Integer> foodAvailable() {
      return foodAvailable;
   }
}
