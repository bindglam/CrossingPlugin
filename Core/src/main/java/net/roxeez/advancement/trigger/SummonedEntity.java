/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ 
/*    */ public class SummonedEntity
/*    */   implements Trigger {
/*    */   @Expose
/*    */   @SerializedName("entity")
/*    */   private EntityData entity;
/*    */   
/*    */   public void setEntity(Consumer<EntityData> consumer) {
/* 15 */     this.entity = new EntityData();
/* 16 */     consumer.accept(this.entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\SummonedEntity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */