/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemData
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private Material item;
/*    */   @Expose
/*    */   @SerializedName("count")
/*    */   private Range<Integer> count;
/*    */   @Expose
/*    */   @SerializedName("durability")
/*    */   private Range<Integer> durability;
/*    */   @Expose
/*    */   @SerializedName("potion")
/*    */   private PotionType potion;
/*    */   @Expose
/*    */   @SerializedName("nbt")
/*    */   private String nbt;
/*    */   
/*    */   public void setType(Material material) {
/* 31 */     this.item = material;
/*    */   }
/*    */   
/*    */   public void setCount(int minimum) {
/* 35 */     this.count = new Range<>(Integer.valueOf(minimum));
/*    */   }
/*    */   
/*    */   public void setCount(int minimum, int maximum) {
/* 39 */     this.count = new Range<>(Integer.valueOf(minimum), Integer.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setDurability(int minimum) {
/* 43 */     this.durability = new Range<>(Integer.valueOf(minimum));
/*    */   }
/*    */   
/*    */   public void setDurability(int minimum, int maximum) {
/* 47 */     this.durability = new Range<>(Integer.valueOf(minimum), Integer.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setPotion(PotionType potion) {
/* 51 */     this.potion = potion;
/*    */   }
/*    */   
/*    */   public void setNbt(String nbt) {
/* 55 */     this.nbt = nbt;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\ItemData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */