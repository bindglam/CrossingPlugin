/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ 
/*    */ 
/*    */ public class FishingRodHooked
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("entity")
/*    */   private EntityData entity;
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private ItemData item;
/*    */   @Expose
/*    */   @SerializedName("rod")
/*    */   private ItemData rod;
/*    */   
/*    */   public void setEntity(Consumer<EntityData> consumer) {
/* 24 */     this.entity = new EntityData();
/* 25 */     consumer.accept(this.entity);
/*    */   }
/*    */   
/*    */   public void setItem(Consumer<ItemData> consumer) {
/* 29 */     this.item = new ItemData();
/* 30 */     consumer.accept(this.item);
/*    */   }
/*    */   
/*    */   public void setRod(Consumer<ItemData> consumer) {
/* 34 */     this.rod = new ItemData();
/* 35 */     consumer.accept(this.rod);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\FishingRodHooked.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */