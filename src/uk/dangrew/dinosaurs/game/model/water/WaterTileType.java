

package uk.dangrew.dinosaurs.game.model.water;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uk.dangrew.dinosaurs.resources.DinosaurImages;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Types of graphical tiles for water locations.
 */
public enum WaterTileType {
   
   VERTICAL_GRASS_LEFT(
         DinosaurImages::waterStraight,
         view -> view.setRotate(0)
   ),
   VERTICAL_GRASS_RIGHT(
         DinosaurImages::waterStraight,
         view -> view.setRotate(180)
   ),
   HORIZONTAL_GRASS_TOP(
         DinosaurImages::waterStraight,
         view -> view.setRotate(90)
   ),
   HORIZONTAL_GRASS_BOTTOM(
         DinosaurImages::waterStraight,
         view -> view.setRotate(270)
   ),
   
   WATER_TOP_LEFT_CORNER(
         DinosaurImages::grassWithWaterCorner,
         view -> view.setRotate(180)
   ),
   WATER_TOP_RIGHT_CORNER(
         DinosaurImages::grassWithWaterCorner,
         view -> view.setRotate(270)
   ),
   WATER_BOTTOM_LEFT_CORNER(
         DinosaurImages::grassWithWaterCorner,
         view -> view.setRotate(90)
   ),
   WATER_BOTTOM_RIGHT_CORNER(
         DinosaurImages::grassWithWaterCorner,
         view -> view.setRotate(0)
   ),
   
   GRASS_BOTTOM_RIGHT_CORNER(
         DinosaurImages::waterWithGrassCorner,
         imageView -> {}
   ),
   GRASS_BOTTOM_LEFT_CORNER(
         DinosaurImages::waterWithGrassCorner,
         imageView -> imageView.setRotate(90)
   ),
   GRASS_TOP_LEFT_CORNER(
         DinosaurImages::waterWithGrassCorner,
         imageView -> imageView.setRotate(180)
   ),
   GRASS_TOP_RIGHT_CORNER(
         DinosaurImages::waterWithGrassCorner,
         imageView -> imageView.setRotate(270)
   ),
   
   SURROUNDED(
         DinosaurImages::waterSurrounded,
         view -> {}
   );
   
   private static final DinosaurImages DINOSAUR_IMAGES = new DinosaurImages();
   
   private  final Function<DinosaurImages, Image> imageProvider;
   private final Consumer<ImageView> imageOrientation;
   
   private WaterTileType(Function<DinosaurImages, Image> imageProvider, Consumer<ImageView> imageOrientation) {
      this.imageProvider = imageProvider;
      this.imageOrientation = imageOrientation;
   }
   
   public ImageView buildRawImageView() {
      ImageView imageView = new ImageView(imageProvider.apply(DINOSAUR_IMAGES));
      imageOrientation.accept(imageView);
      return imageView;
   }
}
