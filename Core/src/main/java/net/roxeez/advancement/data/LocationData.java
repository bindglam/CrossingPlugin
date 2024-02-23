/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import org.bukkit.StructureType;
/*    */ import org.bukkit.block.Biome;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LocationData
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("biome")
/*    */   private Biome biome;
/*    */   @Expose
/*    */   @SerializedName("block")
/*    */   private BlockData block;
/*    */   @Expose
/*    */   @SerializedName("dimension")
/*    */   private DimensionType dimension;
/*    */   @Expose
/*    */   @SerializedName("feature")
/*    */   private StructureType feature;
/*    */   @Expose
/*    */   @SerializedName("light")
/*    */   private Range<Integer> light;
/*    */   @Expose
/*    */   @SerializedName("position")
/*    */   private PositionData position;
/*    */   @Expose
/*    */   @SerializedName("smokey")
/*    */   private boolean smokey;
/*    */   
/*    */   public void setBlock(Consumer<BlockData> consumer) {
/* 40 */     this.block = new BlockData();
/* 41 */     consumer.accept(this.block);
/*    */   }
/*    */   
/*    */   public void setLight(int light) {
/* 45 */     this.light = new Range<>(Integer.valueOf(light));
/*    */   }
/*    */   
/*    */   public void setLight(int minimum, int maximum) {
/* 49 */     this.light = new Range<>(Integer.valueOf(minimum), Integer.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setPosition(Consumer<PositionData> consumer) {
/* 53 */     this.position = new PositionData();
/* 54 */     consumer.accept(this.position);
/*    */   }
/*    */   
/*    */   public void setBiome(Biome biome) {
/* 58 */     this.biome = biome;
/*    */   }
/*    */   
/*    */   public void setDimension(DimensionType dimension) {
/* 62 */     this.dimension = dimension;
/*    */   }
/*    */   
/*    */   public void setFeature(StructureType feature) {
/* 66 */     this.feature = feature;
/*    */   }
/*    */   
/*    */   public void setSmokey(boolean smokey) {
/* 70 */     this.smokey = smokey;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\LocationData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */