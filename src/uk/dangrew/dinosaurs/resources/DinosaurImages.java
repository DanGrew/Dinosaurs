

package uk.dangrew.dinosaurs.resources;

import javafx.scene.image.Image;

public class DinosaurImages {
   
   private static final Image GRASS;
   private static final Image TREE_ROUNDED;
   private static final Image TREE_SPIKEY;
   
   private static final Image WATER_STRAIGHT;
   private static final Image GRASS_WITH_WATER_CORNER;
   private static final Image WATER_WITH_GRASS_CORNER;
   private static final Image WATER_DIAGONAL_BOTTOM_LEFT_TOP_RIGHT;
   private static final Image WATER_SURROUNDED;
   
   private static final Image ROCK;
   private static final Image ROCK_CORNER;
   private static final Image ROCK_CAVE;
   
   static {
      GRASS = new Image(DinosaurImages.class.getResourceAsStream("grass.png"));
      TREE_ROUNDED = new Image(DinosaurImages.class.getResourceAsStream("tree-rounded.png"));
      TREE_SPIKEY = new Image(DinosaurImages.class.getResourceAsStream("tree-spikey.png"));
      
      WATER_STRAIGHT = new Image(DinosaurImages.class.getResourceAsStream("water-straight.png"));
      GRASS_WITH_WATER_CORNER = new Image(DinosaurImages.class.getResourceAsStream("grass-with-water-corner.png"));
      WATER_WITH_GRASS_CORNER = new Image(DinosaurImages.class.getResourceAsStream("water-with-grass-corner.png"));
      WATER_DIAGONAL_BOTTOM_LEFT_TOP_RIGHT = new Image(DinosaurImages.class.getResourceAsStream("water-diagonal-bottom-left-top-right.png"));
      WATER_SURROUNDED = new Image(DinosaurImages.class.getResourceAsStream("water-surrounded.png"));

      ROCK = new Image(DinosaurImages.class.getResourceAsStream("rock.png"));
      ROCK_CORNER = new Image(DinosaurImages.class.getResourceAsStream("rock-corner.png"));
      ROCK_CAVE = new Image(DinosaurImages.class.getResourceAsStream("rock-cave.png"));
   }

   public Image grass(){
      return GRASS;
   }

   public Image treeRounded(){
      return TREE_ROUNDED;
   }

   public Image treeSpikey(){
      return TREE_SPIKEY;
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

   public Image rock(){
      return ROCK;
   }
   public Image rockCorner(){
      return ROCK_CORNER;
   }
   public Image rockCave(){
      return ROCK_CAVE;
   }
}
