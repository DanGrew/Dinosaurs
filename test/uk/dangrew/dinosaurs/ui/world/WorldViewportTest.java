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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorldViewportTest {
   
   private World world;
   private WorldViewport systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      world = new World();
      systemUnderTest = new WorldViewport(world);
   }
   
   @Test
   public void shouldUpdateTopLeftPosition() {
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new WorldLocation(0, 0)));
      
      systemUnderTest.moveRight();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new WorldLocation(1, 0)));

      systemUnderTest.moveLeft();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new WorldLocation(0, 0)));

      systemUnderTest.moveDown();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new WorldLocation(0, 1)));

      systemUnderTest.moveUp();
      assertThat(systemUnderTest.topLeftProperty().get(), equalTo(new WorldLocation(0, 0)));
   }

}