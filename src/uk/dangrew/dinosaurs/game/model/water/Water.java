
package uk.dangrew.dinosaurs.game.model.water;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import uk.dangrew.dinosaurs.game.collision.CollisionDetector;
import uk.dangrew.dinosaurs.game.collision.WaterCollisionDetector;
import uk.dangrew.dinosaurs.game.storage.Asset;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.concept.Properties;

/**
 * An individual body of water spanning multiple world locations.
 */
public class Water implements Asset {
   
   private final Properties properties;
   private final Map<WorldLocation, WaterLocationProperties> locationProperties;
   private final WaterCollisionDetector waterCollisionDetector;
   
   public Water(String name) {
      this(name, name);
   }

   public Water(String id, String name) {
      this(new Properties(id, name));
   }
   
   Water(Properties properties){
      this.properties = properties;
      this.locationProperties = new LinkedHashMap<>();
      this.waterCollisionDetector = new WaterCollisionDetector(this);
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
      return waterCollisionDetector;
   }

   public Collection<WorldLocation> getCoverage() {
      return locationProperties.keySet();
   }
   
   public void cover(WorldLocation worldLocation, WaterLocationProperties locationProperties){
      this.locationProperties.put(worldLocation, locationProperties);
   }
   
   public WaterLocationProperties getLocationPropertiesFor(WorldLocation worldLocation){
      return locationProperties.get(worldLocation);
   }
}