/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ 
/*    */ public class ThrownItemPickedUpByEntity
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private ItemData item;
/*    */   @Expose
/*    */   @SerializedName("entity")
/*    */   private EntityData entity;
/*    */   
/*    */   public void setEntity(Consumer<EntityData> consumer) {
/* 20 */     this.entity = new EntityData();
/* 21 */     consumer.accept(this.entity);
/*    */   }
/*    */   
/*    */   public void setItem(Consumer<ItemData> consumer) {
/* 25 */     this.item = new ItemData();
/* 26 */     consumer.accept(this.item);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\ThrownItemPickedUpByEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */