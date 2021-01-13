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

package uk.dangrew.dinosaurs.game.world;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class WorldLocationTest {
   
   @Test
   public void shouldBeEqual() {
      assertThat(new WorldLocation(20, 21), equalTo(new WorldLocation(20, 21)));
      assertThat(new WorldLocation(20, 21), not(equalTo(new WorldLocation(20, 19))));
      assertThat(new WorldLocation(20, 21), not(equalTo(new WorldLocation(19, 21))));
   }
   
}
