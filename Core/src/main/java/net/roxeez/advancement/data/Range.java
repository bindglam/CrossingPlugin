/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ public class Range<T>
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("min")
/*    */   private final T minimum;
/*    */   @Expose
/*    */   @SerializedName("max")
/*    */   private final T maximum;
/*    */   
/*    */   public Range(T minimum) {
/* 17 */     this.minimum = minimum;
/* 18 */     this.maximum = null;
/*    */   }
/*    */   
/*    */   public Range(T minimum, T maximum) {
/* 22 */     this.minimum = minimum;
/* 23 */     this.maximum = maximum;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\Range.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */