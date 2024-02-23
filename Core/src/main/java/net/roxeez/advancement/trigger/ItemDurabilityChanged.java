/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ import net.roxeez.advancement.data.Range;
/*    */ 
/*    */ 
/*    */ public class ItemDurabilityChanged
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("delta")
/*    */   private Range<Integer> delta;
/*    */   @Expose
/*    */   @SerializedName("durability")
/*    */   private Range<Integer> durability;
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private ItemData item;
/*    */   
/*    */   public void setDelta(int value) {
/* 24 */     this.delta = new Range(Integer.valueOf(value));
/*    */   }
/*    */   
/*    */   public void setDelta(int min, int max) {
/* 28 */     this.delta = new Range(Integer.valueOf(min), Integer.valueOf(max));
/*    */   }
/*    */   
/*    */   public void setDurability(int value) {
/* 32 */     this.durability = new Range(Integer.valueOf(value));
/*    */   }
/*    */   
/*    */   public void setDurability(int min, int max) {
/* 36 */     this.durability = new Range(Integer.valueOf(min), Integer.valueOf(max));
/*    */   }
/*    */   
/*    */   public void setItem(Consumer<ItemData> consumer) {
/* 40 */     this.item = new ItemData();
/* 41 */     consumer.accept(this.item);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\ItemDurabilityChanged.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */