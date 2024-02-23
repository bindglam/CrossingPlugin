/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EquipmentData
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("mainhand")
/*    */   private ItemData mainHand;
/*    */   @Expose
/*    */   @SerializedName("offhand")
/*    */   private ItemData offHand;
/*    */   @Expose
/*    */   @SerializedName("head")
/*    */   private ItemData head;
/*    */   @Expose
/*    */   @SerializedName("chest")
/*    */   private ItemData chest;
/*    */   @Expose
/*    */   @SerializedName("legs")
/*    */   private ItemData legs;
/*    */   @Expose
/*    */   @SerializedName("feet")
/*    */   private ItemData feet;
/*    */   
/*    */   public void setMainHand(Consumer<ItemData> consumer) {
/* 34 */     this.mainHand = new ItemData();
/* 35 */     consumer.accept(this.mainHand);
/*    */   }
/*    */   
/*    */   public void setOffHand(Consumer<ItemData> consumer) {
/* 39 */     this.offHand = new ItemData();
/* 40 */     consumer.accept(this.offHand);
/*    */   }
/*    */   
/*    */   public void setHead(Consumer<ItemData> consumer) {
/* 44 */     this.head = new ItemData();
/* 45 */     consumer.accept(this.head);
/*    */   }
/*    */   
/*    */   public void setChest(Consumer<ItemData> consumer) {
/* 49 */     this.chest = new ItemData();
/* 50 */     consumer.accept(this.chest);
/*    */   }
/*    */   
/*    */   public void setLegs(Consumer<ItemData> consumer) {
/* 54 */     this.legs = new ItemData();
/* 55 */     consumer.accept(this.legs);
/*    */   }
/*    */   
/*    */   public void setFeet(Consumer<ItemData> consumer) {
/* 59 */     this.feet = new ItemData();
/* 60 */     consumer.accept(this.feet);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\EquipmentData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */