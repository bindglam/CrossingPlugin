/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlagsData
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("is_on_fire")
/*    */   private boolean isOnFire;
/*    */   @Expose
/*    */   @SerializedName("is_sneaking")
/*    */   private boolean isSneaking;
/*    */   @Expose
/*    */   @SerializedName("is_sprinting")
/*    */   private boolean isSprinting;
/*    */   @Expose
/*    */   @SerializedName("is_swimming")
/*    */   private boolean isSwimming;
/*    */   @Expose
/*    */   @SerializedName("is_baby")
/*    */   private boolean isBaby;
/*    */   
/*    */   public void setOnFire(boolean onFire) {
/* 29 */     this.isOnFire = onFire;
/*    */   }
/*    */   
/*    */   public void setSneaking(boolean sneaking) {
/* 33 */     this.isSneaking = sneaking;
/*    */   }
/*    */   
/*    */   public void setSprinting(boolean sprinting) {
/* 37 */     this.isSprinting = sprinting;
/*    */   }
/*    */   
/*    */   public void setSwimming(boolean swimming) {
/* 41 */     this.isSwimming = swimming;
/*    */   }
/*    */   
/*    */   public void setBaby(boolean baby) {
/* 45 */     this.isBaby = baby;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\FlagsData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */