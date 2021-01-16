package uk.dangrew.dinosaurs.game.collision;

import java.util.Optional;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.model.water.WaterDepth;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class WaterCollisionDetector implements CollisionDetector {
   
   private final Water water;
   
   public WaterCollisionDetector(Water water){
      this.water = water;
   }
   
   @Override
   public Optional<Collision> canLocationBeOccupied(Dinosaur dinosaur, WorldLocation newLocation){
      if (!water.getCoverage().contains(newLocation)){
         return Optional.empty();
      }

      WaterDepth depth = water.getLocationPropertiesFor(newLocation).getDepth();
      if ( depth.dinosaurCanSurvive(dinosaur)){
         return Optional.empty();
      } else {
         return Optional.of(new Collision("Water is too deep!"));
      }
   }
}