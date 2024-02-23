/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PositionData
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("x")
/*    */   private Range<Double> x;
/*    */   @Expose
/*    */   @SerializedName("y")
/*    */   private Range<Double> y;
/*    */   @Expose
/*    */   @SerializedName("z")
/*    */   private Range<Double> z;
/*    */   
/*    */   public void setX(double x) {
/* 21 */     this.x = new Range<>(Double.valueOf(x));
/*    */   }
/*    */   
/*    */   public void setY(double y) {
/* 25 */     this.y = new Range<>(Double.valueOf(y));
/*    */   }
/*    */   
/*    */   public void setZ(double z) {
/* 29 */     this.z = new Range<>(Double.valueOf(z));
/*    */   }
/*    */   
/*    */   public void setX(double minimum, double maximum) {
/* 33 */     this.x = new Range<>(Double.valueOf(minimum), Double.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setY(double minimum, double maximum) {
/* 37 */     this.y = new Range<>(Double.valueOf(minimum), Double.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setZ(double minimum, double maximum) {
/* 41 */     this.z = new Range<>(Double.valueOf(minimum), Double.valueOf(maximum));
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\PositionData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */