/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ 
/*    */ public class VillagerTrade
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private ItemData item;
/*    */   @Expose
/*    */   @SerializedName("villager")
/*    */   private EntityData villager;
/*    */   
/*    */   public void setItem(Consumer<ItemData> consumer) {
/* 20 */     this.item = new ItemData();
/* 21 */     consumer.accept(this.item);
/*    */   }
/*    */   
/*    */   public void setVillager(Consumer<EntityData> consumer) {
/* 25 */     this.villager = new EntityData();
/* 26 */     consumer.accept(this.villager);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\VillagerTrade.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */