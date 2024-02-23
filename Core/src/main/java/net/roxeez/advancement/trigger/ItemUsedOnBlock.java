/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ import net.roxeez.advancement.data.LocationData;
/*    */ 
/*    */ public class ItemUsedOnBlock
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("location")
/*    */   private LocationData location;
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private ItemData item;
/*    */   
/*    */   public void setLocation(Consumer<LocationData> consumer) {
/* 20 */     this.location = new LocationData();
/* 21 */     consumer.accept(this.location);
/*    */   }
/*    */   
/*    */   public void setItem(Consumer<ItemData> consumer) {
/* 25 */     this.item = new ItemData();
/* 26 */     consumer.accept(this.item);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\ItemUsedOnBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */