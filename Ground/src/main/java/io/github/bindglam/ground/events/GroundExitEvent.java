/*    */ package io.github.bindglam.ground.events;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class GroundExitEvent
/*    */   extends PlayerEvent
/*    */ {
/* 12 */   private static final HandlerList HANDLER_LIST = new HandlerList();
/*    */   
/*    */   public GroundExitEvent(@NotNull Player who) {
/* 15 */     super(who);
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 19 */     return HANDLER_LIST;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 24 */     return HANDLER_LIST;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Ground.jar!\io\github\bindglam\ground\events\GroundExitEvent.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */