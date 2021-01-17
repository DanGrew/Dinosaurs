
package uk.dangrew.dinosaurs.game.model.rubble;

import java.util.function.Consumer;
import java.util.function.Function;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uk.dangrew.dinosaurs.resources.DinosaurImages;

public enum RockTileType {
   
   ROCK(
         DinosaurImages::rock,
         imageView -> {}
   ),
   ROCK_BOTTOM_RIGHT_CORNER(
         DinosaurImages::rockCorner,
         imageView -> {}
   ),
   ROCK_BOTTOM_LEFT_CORNER(
         DinosaurImages::rockCorner,
         imageView -> imageView.setRotate(90)
   ),
   ROCK_TOP_LEFT_CORNER(
         DinosaurImages::rockCorner,
         imageView -> imageView.setRotate(180)
   ),
   ROCK_TOP_RIGHT_CORNER(
         DinosaurImages::rockCorner,
         imageView -> imageView.setRotate(270)
   ),
   ROCK_CAVE(
         DinosaurImages::rockCave,
         imageView -> {}
   );
   
   private static final DinosaurImages DINOSAUR_IMAGES = new DinosaurImages();
   
   private final Function<DinosaurImages, Image> imageProvider;
   private final Consumer<ImageView> imageOrientation;
   
   private RockTileType(Function<DinosaurImages, Image> imageProvider, Consumer<ImageView> imageOrientation) {
      this.imageProvider = imageProvider;
      this.imageOrientation = imageOrientation;
   }
   
   public ImageView buildImageView() {
      ImageView imageView = new ImageView(imageProvider.apply(DINOSAUR_IMAGES));
      imageOrientation.accept(imageView);
      return imageView;
   }
}
