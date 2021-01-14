
package uk.dangrew.dinosaurs.game.model.water;

/**
 * Individual properties of water at a specific world location.
 */
public class WaterLocationProperties {
   
   private final WaterDepth depth;
   private final WaterTileType tileType;
   
   public WaterLocationProperties(int depth, WaterTileType tileType){
      this.depth = new WaterDepth(depth);
      this.tileType = tileType;
   }

   public WaterDepth getDepth() {
      return depth;
   }

   public WaterTileType getTileType() {
      return tileType;
   }
}