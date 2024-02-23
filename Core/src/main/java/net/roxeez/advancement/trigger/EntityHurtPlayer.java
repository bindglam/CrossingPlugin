/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.DamageData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityHurtPlayer
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("damage")
/*    */   private DamageData damage;
/*    */   
/*    */   public void setDamage(Consumer<DamageData> consumer) {
/* 20 */     this.damage = new DamageData();
/* 21 */     consumer.accept(this.damage);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\EntityHurtPlayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */