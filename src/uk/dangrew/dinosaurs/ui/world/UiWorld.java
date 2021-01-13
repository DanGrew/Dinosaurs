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
import uk.dangrew.dinosaurs.ui.widgets.DinosaurWidget;
import uk.dangrew.dinosaurs.ui.widgets.TreeWidget;
import uk.dangrew.dinosaurs.ui.widgets.WaterWidget;

/**
 * Ui representation of the {@link World}.
 */
public class UiWorld extends Pane {
   
   private final DinosaursConfiguration dinosaursConfiguration;
   
   private final World world;
   private final WorldViewport worldViewport;
   
   private final Dinosaur dinosaur;
   private final Tree tree;
   private final Water water;
   
   public UiWorld(DinosaursConfiguration dinosaursConfiguration, World world, Dinosaur dinosaur, Tree tree, Water water) {
      this.dinosaursConfiguration = dinosaursConfiguration;
      this.world = world;
      this.dinosaur = dinosaur;
      this.tree = tree;
      this.water = water;
      this.worldViewport = new WorldViewport(world);
      
      refresh();
   }

   public WorldViewport getViewport() {
      return worldViewport;
   }

   public World getWorld() {
      return world;
   }

   public void refresh() {
      getChildren().clear();
      drawWorld();
      drawWater(water);
      drawTree(tree);
      drawDinosaur(dinosaur);
   }

   private void drawWorld() {
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
   
   private void drawDinosaur(Dinosaur dinosaur) {
      DinosaurWidget dinosaurWidget = new DinosaurWidget(dinosaursConfiguration, dinosaur);
      dinosaurWidget.redraw(this);
      getChildren().add(dinosaurWidget);
   }

   private void drawTree(Tree tree) {
      TreeWidget treeWidget = new TreeWidget(dinosaursConfiguration, tree);
      treeWidget.redraw(this);
      getChildren().add(treeWidget);
   }

   private void drawWater(Water water) {
      WaterWidget waterWidget = new WaterWidget(dinosaursConfiguration, water);
      waterWidget.redraw(this);
      getChildren().add(waterWidget);
   }

}
