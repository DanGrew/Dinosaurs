package uk.dangrew.dinosaurs.game.model.rubble;

import uk.dangrew.dinosaurs.game.collision.CollisionDetector;
import uk.dangrew.dinosaurs.game.collision.NoCollisions;
import uk.dangrew.dinosaurs.game.storage.Asset;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.concept.Properties;

public class Rock implements Asset {
   
   private final Properties properties;
   private final CollisionDetector collisionDetector;
   
   private WorldLocation worldLocation;
   private RockTileType rockTileType;
   
   public Rock(String name){
      this(name, name);
   }
   
   public Rock(String id, String name){
      this(new Properties(id, name));
   }
   
   Rock(Properties properties){
      this.properties = properties;
      this.collisionDetector = new NoCollisions();
      this.rockTileType = RockTileType.ROCK;
      this.worldLocation = WorldLocation.defaultLocation();
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

   public RockTileType getTileType() {
      return rockTileType;
   }

   public void setRockTileType(RockTileType rockTileType) {
      this.rockTileType = rockTileType;
   }

   public WorldLocation getWorldLocation() {
      return worldLocation;
   }

   public void setWorldLocation(WorldLocation worldLocation) {
      this.worldLocation = worldLocation;
   }
}