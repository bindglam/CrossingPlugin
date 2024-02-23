/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.LocationData;
/*    */ 
/*    */ public class HeroOfTheVillage
/*    */   implements Trigger {
/*    */   @Expose
/*    */   @SerializedName("location")
/*    */   private LocationData location;
/*    */   
/*    */   public void setLocation(Consumer<LocationData> consumer) {
/* 15 */     this.location = new LocationData();
/* 16 */     consumer.accept(this.location);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\HeroOfTheVillage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */