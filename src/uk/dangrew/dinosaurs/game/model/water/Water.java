
package uk.dangrew.dinosaurs.game.model.water;

import uk.dangrew.dinosaurs.game.world.WorldLocation;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An individual body of water spanning multiple world locations.
 */
public class Water {
   
   private final Map<WorldLocation, WaterLocationProperties> locationProperties;
   
   public Water(){
      this.locationProperties = new LinkedHashMap<>();
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