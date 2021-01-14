

package uk.dangrew.dinosaurs.resources;

import javafx.scene.image.Image;

public class DinosaurImages {
   
   private static final Image GRASS;
   private static final Image WATER_STRAIGHT;
   private static final Image GRASS_WITH_WATER_CORNER;
   private static final Image WATER_WITH_GRASS_CORNER;
   private static final Image WATER_SURROUNDED;
   
   static {
      GRASS = new Image(DinosaurImages.class.getResourceAsStream("grass.png"));
      WATER_STRAIGHT = new Image(DinosaurImages.class.getResourceAsStream("water-straight.png"));
      GRASS_WITH_WATER_CORNER = new Image(DinosaurImages.class.getResourceAsStream("grass-with-water-corner.png"));
      WATER_WITH_GRASS_CORNER = new Image(DinosaurImages.class.getResourceAsStream("water-with-grass-corner.png"));
      WATER_SURROUNDED = new Image(DinosaurImages.class.getResourceAsStream("water-surrounded.png"));
   }

   public Image grass(){
      return GRASS;
   }
   
   public Image waterStraight() {
      return WATER_STRAIGHT;
   }

   public Image grassWithWaterCorner() {
      return GRASS_WITH_WATER_CORNER;
   }
   
   public Image waterWithGrassCorner(){
      return WATER_WITH_GRASS_CORNER;
   }
   
   public Image waterSurrounded(){
      return WATER_SURROUNDED;
   }
}
