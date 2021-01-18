
package uk.dangrew.dinosaurs.game.building;

import uk.dangrew.dinosaurs.game.model.rubble.Rock;
import uk.dangrew.dinosaurs.game.model.rubble.RockLocationProperties;
import uk.dangrew.dinosaurs.game.model.rubble.RockTileType;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Builder for rock assets, quickly populating locations and associated properties.
 */
public class RockBuilder {
   
   private final Rock rock;
   private final World world;
   
   public RockBuilder(Rock rock, World world) {
      this.rock = rock;
      this.world = world;
   }
   
   public RockBuilder coverRectangle(WorldLocation topLeft, int width, int height) {
      for (int w = 0; w < width; w++) {
         for (int h = 0; h < height; h++) {
            rock.cover(topLeft.translate(w, h, world));
         }
      }
      return this;
   }
   
   /**
    * Populates rock locations in a right angled triangle from the given corner location. This will, by default, populate right and down. Changing the
    * direction will essentially rotate the triangle multiples of 90 degrees.
    * @param corner the corner with the right angle.
    * @param height the length of one side of the triangle.
    * @param rightToLeft populating right to left.
    * @param topToBottom populating top to bottom.
    * @return this builder.
    */
   public RockBuilder coverTriangle(WorldLocation corner, int height, boolean rightToLeft, boolean topToBottom) {
      height = Math.abs(height);
      
      int horizontalMultiplier = convertToSignedMultiplier(rightToLeft);
      int verticalMultiplier = convertToSignedMultiplier(topToBottom);
      RockTileType rounderTileType = calculateRockTileTypeFor(rightToLeft, topToBottom);
      
      for (int w = 0; w < height; w++) {
         int horizontalAdjustment = w * horizontalMultiplier;
         for (int h = 0; h < height - w; h++) {
            int verticalAdjustment = h * verticalMultiplier;
            rock.cover(corner.translate(horizontalAdjustment, verticalAdjustment, world));
         }
         
         int verticalAdjustment = (height - w) * verticalMultiplier;
         WorldLocation translated = corner.translate(horizontalAdjustment, verticalAdjustment, world);
         rock.cover(translated, new RockLocationProperties(rounderTileType));
      }
      rock.cover(corner.translate(height * horizontalMultiplier, 0, world), new RockLocationProperties(rounderTileType));
      
      return this;
   }
   
   /**
    * May seem a little strange, but converts a nice boolean interface into a mathematical interface allowing the locations to be translated correctly
    * using the same iteration method.
    * @param value the boolean to covert.
    * @return the int multiplier, 1 or -1.
    */
   private int convertToSignedMultiplier(boolean value) {
      return value ? 1 : -1;
   }
   
   private RockTileType calculateRockTileTypeFor(boolean rightToLeft, boolean topToBottom) {
      if (rightToLeft) {
         if (topToBottom) {
            return RockTileType.ROCK_TOP_LEFT_CORNER;
         } else {
            return RockTileType.ROCK_BOTTOM_LEFT_CORNER;
         }
      } else {
         if (topToBottom) {
            return RockTileType.ROCK_TOP_RIGHT_CORNER;
         } else {
            return RockTileType.ROCK_BOTTOM_RIGHT_CORNER;
         }
      }
   }
   
   public RockBuilder append(WorldLocation worldLocation) {
      rock.cover(worldLocation);
      return this;
   }
   
   public RockBuilder subtract(WorldLocation worldLocation) {
      rock.remove(worldLocation);
      return this;
   }
   
}
