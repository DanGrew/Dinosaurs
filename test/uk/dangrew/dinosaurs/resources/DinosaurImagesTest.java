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

package uk.dangrew.dinosaurs.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DinosaurImagesTest {
   
   private DinosaurImages systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      systemUnderTest = new DinosaurImages();
   }
   
   @Test
   public void shouldPopulateImages() {
      assertAll(
            () -> assertThat(systemUnderTest.grass(), notNullValue()),
            () -> assertThat(systemUnderTest.grassWithWaterCorner(), notNullValue()),
            () -> assertThat(systemUnderTest.waterStraight(), notNullValue()),
            () -> assertThat(systemUnderTest.waterWithGrassCorner(), notNullValue()),
            () -> assertThat(systemUnderTest.waterSurrounded(), notNullValue()));
   }
}
