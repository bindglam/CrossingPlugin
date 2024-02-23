/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.AdvancementException;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ import net.roxeez.advancement.data.LocationData;
/*    */ import org.bukkit.Material;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlacedBlock
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("block")
/*    */   private Material block;
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private ItemData item;
/*    */   @Expose
/*    */   @SerializedName("location")
/*    */   private LocationData location;
/*    */   
/*    */   public void setBlock(@NotNull Material material) {
/* 35 */     Preconditions.checkNotNull(material);
/* 36 */     if (!material.isBlock()) {
/* 37 */       throw new AdvancementException("Calling PlacedBlock#is with a non block Material");
/*    */     }
/*    */     
/* 40 */     this.block = material;
/*    */   }
/*    */   
/*    */   public void setItem(Consumer<ItemData> consumer) {
/* 44 */     this.item = new ItemData();
/* 45 */     consumer.accept(this.item);
/*    */   }
/*    */   
/*    */   public void setLocation(Consumer<LocationData> consumer) {
/* 49 */     this.location = new LocationData();
/* 50 */     consumer.accept(this.location);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\PlacedBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */