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

import javafx.scene.layout.GridPane;
import uk.dangrew.dinosaurs.game.mechanism.CameraController;
import uk.dangrew.dinosaurs.game.mechanism.GameEngine;
import uk.dangrew.dinosaurs.ui.controller.PlayerController;
import uk.dangrew.dinosaurs.ui.world.UiWorld;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

public class UiTopLevelPane extends GridPane {

   public UiTopLevelPane(UiWorld uiWorld, GameEngine gameEngine, CameraController cameraController) {
      new JavaFxStyle().configureConstraintsForColumnPercentages(this, 80, 20);
      
      add(uiWorld, 0, 0);
      add(new PlayerController(gameEngine, cameraController), 1, 0);
   }
}
