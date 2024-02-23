/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.DamageType;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ 
/*    */ public class EntityKilledPlayer
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("entity")
/*    */   private EntityData entity;
/*    */   @Expose
/*    */   @SerializedName("killing_blow")
/*    */   private DamageType killingBlow;
/*    */   
/*    */   public void setEntity(Consumer<EntityData> consumer) {
/* 20 */     this.entity = new EntityData();
/* 21 */     consumer.accept(this.entity);
/*    */   }
/*    */   
/*    */   public void setKillingBlow(Consumer<DamageType> consumer) {
/* 25 */     this.killingBlow = new DamageType();
/* 26 */     consumer.accept(this.killingBlow);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\EntityKilledPlayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */