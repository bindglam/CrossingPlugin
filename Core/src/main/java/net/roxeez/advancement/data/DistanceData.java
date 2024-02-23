/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DistanceData
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("absolute")
/*    */   private Range<Float> absolute;
/*    */   @Expose
/*    */   @SerializedName("horizontal")
/*    */   private Range<Float> horizontal;
/*    */   @Expose
/*    */   @SerializedName("x")
/*    */   private Range<Float> x;
/*    */   @Expose
/*    */   @SerializedName("y")
/*    */   private Range<Float> y;
/*    */   @Expose
/*    */   @SerializedName("z")
/*    */   private Range<Float> z;
/*    */   
/*    */   public void setAbsolute(float minimum, float maximum) {
/* 29 */     this.absolute = new Range<>(Float.valueOf(minimum), Float.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setAbsolute(float minimum) {
/* 33 */     this.absolute = new Range<>(Float.valueOf(minimum));
/*    */   }
/*    */   
/*    */   public void setHorizontal(float minimum, float maximum) {
/* 37 */     this.horizontal = new Range<>(Float.valueOf(minimum), Float.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setHorizontal(float minimum) {
/* 41 */     this.horizontal = new Range<>(Float.valueOf(minimum));
/*    */   }
/*    */   
/*    */   public void setX(float minimum, float maximum) {
/* 45 */     this.x = new Range<>(Float.valueOf(minimum), Float.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setX(float minimum) {
/* 49 */     this.x = new Range<>(Float.valueOf(minimum));
/*    */   }
/*    */   
/*    */   public void setY(float minimum, float maximum) {
/* 53 */     this.y = new Range<>(Float.valueOf(minimum), Float.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setY(float minimum) {
/* 57 */     this.y = new Range<>(Float.valueOf(minimum));
/*    */   }
/*    */   
/*    */   public void setZ(float minimum, float maximum) {
/* 61 */     this.z = new Range<>(Float.valueOf(minimum), Float.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setZ(float minimum) {
/* 65 */     this.z = new Range<>(Float.valueOf(minimum));
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\DistanceData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */