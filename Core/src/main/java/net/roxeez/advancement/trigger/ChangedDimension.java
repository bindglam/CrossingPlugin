/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import net.roxeez.advancement.data.DimensionType;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChangedDimension
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("from")
/*    */   private DimensionType from;
/*    */   @Expose
/*    */   @SerializedName("to")
/*    */   private DimensionType to;
/*    */   
/*    */   public void setFrom(@NotNull DimensionType type) {
/* 25 */     Preconditions.checkNotNull(type);
/* 26 */     this.from = type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTo(DimensionType type) {
/* 35 */     Preconditions.checkNotNull(type);
/* 36 */     this.to = type;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\ChangedDimension.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */