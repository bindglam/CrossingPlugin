/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ 
/*    */ 
/*    */ public class TargetHit
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("signal_strength")
/*    */   private int signalStrength;
/*    */   @Expose
/*    */   @SerializedName("shooter")
/*    */   private EntityData shooter;
/*    */   
/*    */   public void setShooter(Consumer<EntityData> consumer) {
/* 20 */     this.shooter = new EntityData();
/* 21 */     consumer.accept(this.shooter);
/*    */   }
/*    */   
/*    */   public void setSignalStrength(int strength) {
/* 25 */     this.signalStrength = strength;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\TargetHit.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */