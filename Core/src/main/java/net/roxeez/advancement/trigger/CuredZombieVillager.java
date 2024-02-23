/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CuredZombieVillager
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("villager")
/*    */   private EntityData villager;
/*    */   @Expose
/*    */   @SerializedName("zombie")
/*    */   private EntityData zombie;
/*    */   
/*    */   public void setVillager(Consumer<EntityData> consumer) {
/* 23 */     this.villager = new EntityData();
/* 24 */     consumer.accept(this.villager);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setZombie(Consumer<EntityData> consumer) {
/* 32 */     this.zombie = new EntityData();
/* 33 */     consumer.accept(this.zombie);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\CuredZombieVillager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */