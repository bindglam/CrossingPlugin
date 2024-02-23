/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.DistanceData;
/*    */ import net.roxeez.advancement.data.Range;
/*    */ 
/*    */ public class Levitation
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("distance")
/*    */   private DistanceData distance;
/*    */   @Expose
/*    */   @SerializedName("duration")
/*    */   private Range<Integer> duration;
/*    */   
/*    */   public void setDistance(Consumer<DistanceData> consumer) {
/* 20 */     this.distance = new DistanceData();
/* 21 */     consumer.accept(this.distance);
/*    */   }
/*    */   
/*    */   public void setDuration(int value) {
/* 25 */     this.duration = new Range(Integer.valueOf(value));
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\Levitation.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */