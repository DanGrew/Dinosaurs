
package uk.dangrew.dinosaurs.game.world;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import uk.dangrew.dinosaurs.game.actions.mechanism.ActionGenerator;
import uk.dangrew.dinosaurs.game.actions.mechanism.NoActions;
import uk.dangrew.dinosaurs.game.collision.CollisionDetector;
import uk.dangrew.dinosaurs.game.collision.NoCollisions;
import uk.dangrew.dinosaurs.game.storage.Asset;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.concept.Properties;

/**
 * Definition of the world, and the associated grid behind it.
 */
public class World implements Asset {
   
   private final IntegerProperty width;
   private final IntegerProperty height;
   
   private final Properties properties;
   private final CollisionDetector collisionDetector;
   private final ActionGenerator actionGenerator;
   
   public World(String name) {
      this(name, name);
   }
   
   public World(String id, String name) {
      this(new Properties(id, name));
   }
   
   World(Properties properties) {
      this.width = new SimpleIntegerProperty();
      this.height = new SimpleIntegerProperty();
      this.properties = properties;
      this.collisionDetector = new NoCollisions();
      this.actionGenerator = new NoActions();
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

   public void setDimension(int width, int height){
      this.width.setValue(width);
      this.height.setValue(height);
   }

   public int getHorizontalCellCount() {
      return width.get();
   }

   public int getVerticalCellCount() {
      return height.get();
   }
}
