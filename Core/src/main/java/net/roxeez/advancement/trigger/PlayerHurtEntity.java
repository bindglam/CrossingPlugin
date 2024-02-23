/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.DamageData;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ 
/*    */ public class PlayerHurtEntity
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("damage")
/*    */   private DamageData damage;
/*    */   @Expose
/*    */   @SerializedName("entity")
/*    */   private EntityData entity;
/*    */   
/*    */   public void setDamage(Consumer<DamageData> consumer) {
/* 20 */     this.damage = new DamageData();
/* 21 */     consumer.accept(this.damage);
/*    */   }
/*    */   
/*    */   public void setEntity(Consumer<EntityData> consumer) {
/* 25 */     this.entity = new EntityData();
/* 26 */     consumer.accept(this.entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\PlayerHurtEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */