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
package uk.dangrew.dinosaurs.ui.configuration;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DinosaursConfiguration {

   public static final int DEFAULT_WORLD_CELL_DIMENSION = 30;
   
   private final ObjectProperty<Integer> worldCellDimension;
   private final ObjectProperty<Integer> worldCellRadius;
   
   public DinosaursConfiguration(){
      this.worldCellDimension = new SimpleObjectProperty<>();
      this.worldCellRadius = new SimpleObjectProperty<>();
      
      worldCellDimension.addListener((s,o,n) -> worldCellRadius.set(n / 2));
      worldCellDimension.set(DEFAULT_WORLD_CELL_DIMENSION);
   }

   public ObjectProperty<Integer> worldCellDimension() {
      return worldCellDimension;
   }

   public ReadOnlyObjectProperty<Integer> worldCellRadius() {
      return worldCellRadius;
   }
}