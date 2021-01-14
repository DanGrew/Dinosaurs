// *************************************************************
//                         H*E*R*M*E*S
//                   Holistic Environment for
//         Railway Modelling, Evaluation and Simulation
//        Built on Graffica System Development Kit: GSDK
//
//       Copyright: (c) Graffica Ltd (www.graffica.co.uk)
//
// This software is made available under binary licence. Holding 
// source code without the express permission of Graffica Ltd is 
//           not permitted under any circumstances. 
// *************************************************************

package uk.dangrew.dinosaurs.ui.world;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.resources.DinosaurImages;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;

/**
 * Ui representation of the {@link World}.
 */
public class UiWorld extends Pane {
   
   private final DinosaursConfiguration dinosaursConfiguration;
   
   private final World world;
   private final WorldViewport worldViewport;
   
   public UiWorld(DinosaursConfiguration dinosaursConfiguration, World world) {
      this.dinosaursConfiguration = dinosaursConfiguration;
      this.world = world;
      this.worldViewport = new WorldViewport(world);
      
      
      redraw();
   }

   public WorldViewport getViewport() {
      return worldViewport;
   }

   public World getWorld() {
      return world;
   }

   public void redraw() {
      getChildren().clear();
      
      int worldCellDimension = dinosaursConfiguration.worldCellDimension().get();
      
      for (WorldLocation location : getViewport().getLocationsInView()) {
         int horizontalLocation = (location.getHorizontal() - getViewport().topLeftProperty().get().getHorizontal()) * worldCellDimension;
         int verticalLocation = (location.getVertical() - getViewport().topLeftProperty().get().getVertical()) * worldCellDimension;
         
         ImageView imageView = new ImageView(new DinosaurImages().grass());
         imageView.setFitWidth(worldCellDimension);
         imageView.setFitHeight(worldCellDimension);
         imageView.setX(horizontalLocation);
         imageView.setY(verticalLocation);
         getChildren().add(imageView);
      }
   }
   
}
