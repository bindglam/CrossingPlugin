/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import net.roxeez.advancement.data.Range;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConstructBeacon
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("level")
/*    */   private Range<Integer> level;
/*    */   
/*    */   public void setLevel(int minimum, int maximum) {
/* 20 */     this.level = new Range(Integer.valueOf(minimum), Integer.valueOf(maximum));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLevel(int level) {
/* 29 */     this.level = new Range(Integer.valueOf(level));
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\ConstructBeacon.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */