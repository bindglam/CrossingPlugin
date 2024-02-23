/*    */ package io.github.bindglam.core.listeners;
/*    */ 
/*    */ import dev.lone.itemsadder.api.CustomBlock;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.world.ChunkLoadEvent;
/*    */ 
/*    */ public class WorldListener implements Listener {
/*    */   @EventHandler
/*    */   public void onChunkLoading(ChunkLoadEvent event) {
/* 12 */     if (event.isNewChunk() && 
/* 13 */       Math.random() < 0.02D)
/* 14 */       for (int i = 0; i < (int)(Math.random() * 30.0D); i++) {
/* 15 */         int x = (int)(Math.random() * 16.0D);
/* 16 */         int y = (int)(Math.random() * 23.0D + 1.0D);
/* 17 */         int z = (int)(Math.random() * 16.0D);
/*    */         
/* 19 */         if (event.getChunk().getBlock(x, y, z).getType() != Material.AIR)
/* 20 */           CustomBlock.place("minecraftcross:ruby_ore", event.getChunk().getBlock(x, y, z).getLocation()); 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\listeners\WorldListener.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */