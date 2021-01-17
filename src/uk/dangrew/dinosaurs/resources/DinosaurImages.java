

package uk.dangrew.dinosaurs.resources;

import javafx.scene.image.Image;

public class DinosaurImages {
   
   private static final Image GRASS;
   private static final Image TREE;
   private static final Image WATER_STRAIGHT;
   private static final Image GRASS_WITH_WATER_CORNER;
   private static final Image WATER_WITH_GRASS_CORNER;
   private static final Image WATER_DIAGONAL_BOTTOM_LEFT_TOP_RIGHT;
   private static final Image WATER_SURROUNDED;
   
   static {
      GRASS = new Image(DinosaurImages.class.getResourceAsStream("grass.png"));
      TREE = new Image(DinosaurImages.class.getResourceAsStream("tree.png"));
      WATER_STRAIGHT = new Image(DinosaurImages.class.getResourceAsStream("water-straight.png"));
      GRASS_WITH_WATER_CORNER = new Image(DinosaurImages.class.getResourceAsStream("grass-with-water-corner.png"));
      WATER_WITH_GRASS_CORNER = new Image(DinosaurImages.class.getResourceAsStream("water-with-grass-corner.png"));
      WATER_DIAGONAL_BOTTOM_LEFT_TOP_RIGHT = new Image(DinosaurImages.class.getResourceAsStream("water-diagonal-bottom-left-top-right.png"));
      WATER_SURROUNDED = new Image(DinosaurImages.class.getResourceAsStream("water-surrounded.png"));
   }

   public Image grass(){
      return GRASS;
   }

   public Image tree(){
      return TREE;
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
   
   public Image waterDiagonalBottomLeftTopRight(){
      return WATER_DIAGONAL_BOTTOM_LEFT_TOP_RIGHT;
   }
   
   public Image waterSurrounded(){
      return WATER_SURROUNDED;
   }
}
