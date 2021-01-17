
package uk.dangrew.dinosaurs.game.model.water;

/**
 * Individual properties of water at a specific world location.
 */
public class WaterLocationProperties {
   
   private static final WaterTileType DEFAULT_TILE_TYPE = WaterTileType.SURROUNDED;
   private static final int DEFAULT_DEPTH = 0;
   
   private WaterDepth depth;
   private WaterTileType tileType;
   
   public WaterLocationProperties() {
      this(DEFAULT_DEPTH, DEFAULT_TILE_TYPE);
   }
   
   public WaterLocationProperties(int depth, WaterTileType tileType) {
      this.depth = new WaterDepth(depth);
      this.tileType = tileType;
   }
   
   public WaterDepth getDepth() {
      return depth;
   }

   public void setDepth(WaterDepth depth) {
      this.depth = depth;
   }

   public WaterTileType getTileType() {
      return tileType;
   }

   public void setTileType(WaterTileType tileType) {
      this.tileType = tileType;
   }
}
