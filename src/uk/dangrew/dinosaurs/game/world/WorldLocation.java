
package uk.dangrew.dinosaurs.game.world;

import java.util.Objects;

/**
 * Represents a specific location in the world, which is split into a grid.
 */
public class WorldLocation {
   
   private final int horizontal;
   private final int vertical;
   
   public WorldLocation(int horizontal, int vertical, World world) {
      this(
            Math.floorMod(horizontal, world.getHorizontalCellCount()),
            Math.floorMod(vertical, world.getVerticalCellCount()));
   }
   
   protected WorldLocation(int horizontal, int vertical) {
      this.horizontal = horizontal;
      this.vertical = vertical;
   }

   public int getHorizontal() {
      return horizontal;
   }

   public int getVertical() {
      return vertical;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || !(o instanceof WorldLocation)) {
         return false;
      }
      WorldLocation that = (WorldLocation) o;
      return getHorizontal() == that.getHorizontal() && getVertical() == that.getVertical();
   }

   @Override
   public int hashCode() {
      return Objects.hash(getHorizontal(), getVertical());
   }

   public WorldLocation difference(WorldLocation worldLocation, World world) {
      int horizontal = getHorizontal() - worldLocation.getHorizontal();
      int vertical = getVertical() - worldLocation.getVertical();
      return new WorldLocation(horizontal, vertical, world);
   }

   public WorldLocation translate(int horizontalAdjustment, int verticalAdjustment, World world) {
      int horizontal = Math.floorMod(getHorizontal() + horizontalAdjustment, world.getHorizontalCellCount());
      int vertical = Math.floorMod(getVertical() + verticalAdjustment, world.getVerticalCellCount());
      return new WorldLocation(horizontal, vertical);
   }

   @Override
   public String toString() {
      return String.format("Location: {%d, %d}", getHorizontal(), getVertical());
   }
   
   public String wrapedCoordinates(){
      return String.format("{%d, %d}", getHorizontal(), getVertical());
   }

   public static WorldLocation defaultLocation() {
      return new WorldLocation(0, 0);
   }
   
   
}
