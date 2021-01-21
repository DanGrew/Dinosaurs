package uk.dangrew.dinosaurs.game.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.dangrew.dinosaurs.game.algorithm.shortestpath.AyeStah;
import uk.dangrew.dinosaurs.game.algorithm.shortestpath.AyeStahException;
import uk.dangrew.dinosaurs.game.algorithm.shortestpath.SearchSession;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

public class RoamingBehaviour implements DinosaurBehaviour {
   
   private final World world;
   private final Dinosaur dinosaur;
   
   private final AyeStah search;
   
   private final Random random;
   private final List<WorldLocation> movementPath;
   
   public RoamingBehaviour(Dinosaur dinosaur, World world){
      this.dinosaur = dinosaur;
      this.world = world;
      this.search = new AyeStah(world);
      this.random = new Random();
      this.movementPath = new ArrayList<>();
   }
   
   public void behave(){
      if ( movementPath.isEmpty()){
         int horizontalLocation = random.nextInt(world.getHorizontalCellCount());
         int verticalLocation = random.nextInt(world.getVerticalCellCount());
         try {
            List<WorldLocation> pathToFollow =
                  this.search.search(new SearchSession(dinosaur.expectLocation(), new WorldLocation(horizontalLocation, verticalLocation, world)));
            movementPath.addAll(pathToFollow);
         } catch (AyeStahException e) {
            return;
         }
      }

      WorldLocation locationToMoveTo = movementPath.remove(0);
      dinosaur.getWorldLocation().set(locationToMoveTo);
      System.out.println("Moved to " + locationToMoveTo);
   }
   
}