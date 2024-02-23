/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ public class EffectData
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("amplifier")
/*    */   private Range<Integer> amplifier;
/*    */   @Expose
/*    */   @SerializedName("duration")
/*    */   private Range<Integer> duration;
/*    */   
/*    */   public void setAmplifier(int minimum) {
/* 17 */     this.amplifier = new Range<>(Integer.valueOf(minimum));
/*    */   }
/*    */   
/*    */   public void setDuration(int minimum) {
/* 21 */     this.duration = new Range<>(Integer.valueOf(minimum));
/*    */   }
/*    */   
/*    */   public void setAmplifier(int minimum, int maximum) {
/* 25 */     this.amplifier = new Range<>(Integer.valueOf(minimum), Integer.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setDuration(int minimum, int maximum) {
/* 29 */     this.duration = new Range<>(Integer.valueOf(minimum), Integer.valueOf(maximum));
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\EffectData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */