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

package uk.dangrew.dinosaurs.ui.main;

import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.GRASS_BOTTOM_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.GRASS_TOP_RIGHT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_BOTTOM_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_BOTTOM_RIGHT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.HORIZONTAL_GRASS_BOTTOM;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.HORIZONTAL_GRASS_TOP;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.SURROUNDED;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_TOP_LEFT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.WATER_TOP_RIGHT_CORNER;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.VERTICAL_GRASS_LEFT;
import static uk.dangrew.dinosaurs.game.model.water.WaterTileType.VERTICAL_GRASS_RIGHT;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.model.greenery.Tree;
import uk.dangrew.dinosaurs.game.model.water.Water;
import uk.dangrew.dinosaurs.game.model.water.WaterLocationProperties;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.controls.MovementControls;
import uk.dangrew.dinosaurs.ui.controls.PanningControls;
import uk.dangrew.dinosaurs.ui.world.UiWorld;
import uk.dangrew.dinosaurs.ui.world.WorldRedrawer;

public class UiTopLevelPane extends BorderPane {

   public static final int WINDOW_DIMENSION = 600;

   public UiTopLevelPane(DinosaursConfiguration dinosaursConfiguration) {
      Dinosaur dinosaur = new Dinosaur("Steggy");
      dinosaur.getWorldLocation().set(new WorldLocation(10, 10));
      Tree tree = new Tree(new WorldLocation(15, 8));
      Water water = new Water();
      water.cover(new WorldLocation(16, 7), new WaterLocationProperties(0, WATER_BOTTOM_RIGHT_CORNER));
      water.cover(new WorldLocation(16, 8), new WaterLocationProperties(1, VERTICAL_GRASS_LEFT));
      water.cover(new WorldLocation(16, 9), new WaterLocationProperties(0, WATER_TOP_RIGHT_CORNER));
      
      water.cover(new WorldLocation(17, 7), new WaterLocationProperties(1, HORIZONTAL_GRASS_TOP));
      water.cover(new WorldLocation(17, 8), new WaterLocationProperties(2, SURROUNDED));
      water.cover(new WorldLocation(17, 9), new WaterLocationProperties(1, GRASS_BOTTOM_LEFT_CORNER));
      
      water.cover(new WorldLocation(18, 7), new WaterLocationProperties(0, WATER_BOTTOM_LEFT_CORNER));
      water.cover(new WorldLocation(18, 8),new WaterLocationProperties( 1, VERTICAL_GRASS_RIGHT));
      water.cover(new WorldLocation(18, 9), new WaterLocationProperties(0, VERTICAL_GRASS_RIGHT));
      
      water.cover(new WorldLocation(17, 10),new WaterLocationProperties( 1, VERTICAL_GRASS_LEFT));
      water.cover(new WorldLocation(17, 11), new WaterLocationProperties(2, WATER_TOP_RIGHT_CORNER));
      
      water.cover(new WorldLocation(18, 10), new WaterLocationProperties(1, GRASS_TOP_RIGHT_CORNER));
      water.cover(new WorldLocation(18, 11), new WaterLocationProperties(0, HORIZONTAL_GRASS_BOTTOM));
      
      water.cover(new WorldLocation(19, 10),new WaterLocationProperties( 1, WATER_BOTTOM_LEFT_CORNER));
      water.cover(new WorldLocation(19, 11),new WaterLocationProperties( 0, WATER_TOP_LEFT_CORNER));
      
      World world = new World();
      UiWorld uiWorld = new UiWorld(dinosaursConfiguration, world, dinosaur, tree, water);
      uiWorld.setPrefSize(WINDOW_DIMENSION, WINDOW_DIMENSION);
      uiWorld.setMinSize(WINDOW_DIMENSION, WINDOW_DIMENSION);
      uiWorld.setMaxSize(WINDOW_DIMENSION, WINDOW_DIMENSION);
      setCenter(uiWorld);

      GridPane grid = new GridPane();
      grid.setGridLinesVisible(true);
      grid.add(new PanningControls(uiWorld.getViewport()), 0, 1);
      grid.add(new MovementControls(dinosaur, world), 0, 2);
      setRight(grid);
      
      new WorldRedrawer(uiWorld, dinosaur, tree);
      
   }
}
