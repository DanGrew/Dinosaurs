package uk.dangrew.dinosaurs.game.model.rubble;

public class RockLocationProperties {
   
   private RockTileType tileType;
   
   public RockLocationProperties() {
      this(RockTileType.ROCK);
   } 
   
   public RockLocationProperties(RockTileType rockTileType){
      this.tileType = rockTileType;
   }

   public void setTileType(RockTileType tileType) {
      this.tileType = tileType;
   }

   public RockTileType getTileType() {
      return tileType;
   }
}