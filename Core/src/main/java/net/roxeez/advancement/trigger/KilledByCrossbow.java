/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ import net.roxeez.advancement.data.Range;
/*    */ 
/*    */ public class KilledByCrossbow
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("unique_entity_types")
/*    */   private Range<Integer> uniqueEntityTypes;
/*    */   @Expose
/*    */   @SerializedName("victims")
/*    */   private List<EntityData> victims;
/*    */   
/*    */   public void addVictim(Consumer<EntityData> consumer) {
/* 22 */     if (this.victims == null) {
/* 23 */       this.victims = new ArrayList<>();
/*    */     }
/* 25 */     EntityData entity = new EntityData();
/* 26 */     consumer.accept(entity);
/* 27 */     this.victims.add(entity);
/*    */   }
/*    */   
/*    */   public void setUniqueEntityTypes(int value) {
/* 31 */     this.uniqueEntityTypes = new Range(Integer.valueOf(value));
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\KilledByCrossbow.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */