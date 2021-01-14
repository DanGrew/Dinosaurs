
package uk.dangrew.dinosaurs.game.world;

import uk.dangrew.dinosaurs.game.actions.Movement;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.concept.Properties;

/**
 * Definition of the world, and the associated grid behind it.
 */
public class World implements Concept {
   
   private static final int WORLD_DIMENSION = 100;
   
   private final Properties properties;
   private final WorldLocation[][] worldLocations;
   
   public World(String name) {
      this(name, name);
   }
   
   public World(String id, String name) {
      this(new Properties(id, name));
   }
   
   World(Properties properties) {
      this.properties = properties;
      this.worldLocations = new WorldLocation[WORLD_DIMENSION][WORLD_DIMENSION];
      for (int horizontal = 0; horizontal < WORLD_DIMENSION; horizontal++) {
         for (int vertical = 0; vertical < WORLD_DIMENSION; vertical++) {
            worldLocations[horizontal][vertical] = new WorldLocation(horizontal, vertical);
         }
      }
   }

   @Override
   public Properties properties() {
      return properties;
   }

   @Override
   public Concept duplicate() {
      throw new UnsupportedOperationException();
   }

   public WorldLocation locationForMovement(WorldLocation worldLocation, Movement movement) {
      WorldLocation rawMove = movement.rawMove(worldLocation);
      return new WorldLocation(
            rawMove.getHorizontal() % getHorizontalCellCount(),
            rawMove.getVertical() % getVerticalCellCount());
   }
   
   public int getHorizontalCellCount() {
      return WORLD_DIMENSION;
   }
   
   public int getVerticalCellCount() {
      return WORLD_DIMENSION;
   }
}
