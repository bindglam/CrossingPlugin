/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ 
/*    */ public class PlayerInteractedWithEntity
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private ItemData item;
/*    */   @Expose
/*    */   @SerializedName("entity")
/*    */   private EntityData entity;
/*    */   
/*    */   public void setItem(Consumer<ItemData> consumer) {
/* 20 */     this.item = new ItemData();
/* 21 */     consumer.accept(this.item);
/*    */   }
/*    */   
/*    */   public void setEntity(Consumer<EntityData> consumer) {
/* 25 */     this.entity = new EntityData();
/* 26 */     consumer.accept(this.entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\PlayerInteractedWithEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */