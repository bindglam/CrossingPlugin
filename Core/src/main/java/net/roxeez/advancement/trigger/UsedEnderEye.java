/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.DistanceData;
/*    */ 
/*    */ public class UsedEnderEye
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("distance")
/*    */   private DistanceData distance;
/*    */   
/*    */   public void setDistance(Consumer<DistanceData> consumer) {
/* 16 */     this.distance = new DistanceData();
/* 17 */     consumer.accept(this.distance);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\UsedEnderEye.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */