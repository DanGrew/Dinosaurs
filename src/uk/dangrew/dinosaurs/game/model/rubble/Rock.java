package uk.dangrew.dinosaurs.game.model.rubble;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uk.dangrew.dinosaurs.game.actions.mechanism.ActionGenerator;
import uk.dangrew.dinosaurs.game.actions.mechanism.NoActions;
import uk.dangrew.dinosaurs.game.collision.CollisionDetector;
import uk.dangrew.dinosaurs.game.collision.NoCollisions;
import uk.dangrew.dinosaurs.game.storage.Asset;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.concept.Properties;

public class Rock implements Asset {
   
   private final Properties properties;
   private final Map<WorldLocation, RockLocationProperties> rockLocationProperties;
   private final CollisionDetector collisionDetector;
   private final ActionGenerator actionGenerator;
   
   public Rock(String name){
      this(name, name);
   }
   
   public Rock(String id, String name){
      this(new Properties(id, name));
   }
   
   Rock(Properties properties){
      this.properties = properties;
      this.rockLocationProperties = new HashMap<>();
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

   public Collection<WorldLocation> getCoverage() {
      return rockLocationProperties.keySet();
   }

   public void cover(WorldLocation worldLocation){
      cover(worldLocation, new RockLocationProperties());
   }

   public void cover(WorldLocation worldLocation, RockLocationProperties locationProperties){
      rockLocationProperties.put(worldLocation, locationProperties);
   }

   public void remove(WorldLocation worldLocation){
      rockLocationProperties.remove(worldLocation);
   }


   public RockLocationProperties getLocationPropertiesFor(WorldLocation worldLocation){
      return rockLocationProperties.get(worldLocation);
   }

}