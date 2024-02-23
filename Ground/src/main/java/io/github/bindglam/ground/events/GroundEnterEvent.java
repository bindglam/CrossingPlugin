/*    */ package io.github.bindglam.ground.events;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class GroundEnterEvent
/*    */   extends PlayerEvent
/*    */ {
/* 13 */   private static final HandlerList HANDLER_LIST = new HandlerList();
/*    */   
/*    */   private final Location loc;
/*    */   private final List<Object> data;
/*    */   
/*    */   public GroundEnterEvent(@NotNull Player who, Location loc, List<Object> data) {
/* 19 */     super(who);
/* 20 */     this.loc = loc;
/* 21 */     this.data = data;
/*    */   }
/*    */   
/*    */   public Location getLoc() {
/* 25 */     return this.loc;
/*    */   }
/*    */   
/*    */   public List<Object> getData() {
/* 29 */     return this.data;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 33 */     return HANDLER_LIST;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 38 */     return HANDLER_LIST;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Ground.jar!\io\github\bindglam\ground\events\GroundEnterEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */