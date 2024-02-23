/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.LocationData;
/*    */ 
/*    */ public class Location implements Trigger {
/*    */   @Expose
/*    */   @SerializedName("location")
/*    */   private LocationData location;
/*    */   
/*    */   public void setLocation(Consumer<LocationData> consumer) {
/* 14 */     this.location = new LocationData();
/* 15 */     consumer.accept(this.location);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\Location.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */