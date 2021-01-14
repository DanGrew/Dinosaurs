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
package uk.dangrew.dinosaurs.ui.widgets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.ui.configuration.DinosaursConfiguration;
import uk.dangrew.dinosaurs.ui.world.WorldViewport;

import static org.junit.jupiter.api.Assertions.fail;

public class DinosaurWidgetTest {

   private DinosaursConfiguration dinosaursConfiguration;
   private Dinosaur dinosaur;
   private WorldViewport worldViewport;
   private DinosaurWidget systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      systemUnderTest = new DinosaurWidget(dinosaursConfiguration, dinosaur, worldViewport);
   }
   
   @Test
   public void should() {
      fail();
   }
   
}