/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.DistanceData;
/*    */ import net.roxeez.advancement.data.LocationData;
/*    */ 
/*    */ public class NetherTravel
/*    */   implements Trigger {
/*    */   private LocationData entered;
/*    */   private LocationData exited;
/*    */   private DistanceData distance;
/*    */   
/*    */   public void setEntered(Consumer<LocationData> consumer) {
/* 14 */     this.entered = new LocationData();
/* 15 */     consumer.accept(this.entered);
/*    */   }
/*    */   
/*    */   public void setExited(Consumer<LocationData> consumer) {
/* 19 */     this.exited = new LocationData();
/* 20 */     consumer.accept(this.exited);
/*    */   }
/*    */   
/*    */   public void setDistance(Consumer<DistanceData> consumer) {
/* 24 */     this.distance = new DistanceData();
/* 25 */     consumer.accept(this.distance);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\NetherTravel.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */