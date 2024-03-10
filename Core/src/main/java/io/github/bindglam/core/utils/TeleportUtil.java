/*    */ package io.github.bindglam.core.utils;
/*    */ 
/*    */ import io.github.bindglam.battle.MapManager;
import io.github.bindglam.core.commands.BackToCommand;
/*    */ import java.util.Objects;
/*    */ import java.util.concurrent.CopyOnWriteArrayList;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.api.chat.ClickEvent;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class TeleportUtil
/*    */ {
/* 21 */   public static final CopyOnWriteArrayList<String> teleportingPlayers = new CopyOnWriteArrayList<>();
/*    */   
/*    */   public static void teleportAfterCooldown(Player player, Location loc, Integer time, boolean canBack) {
/* 24 */     teleportAfterCooldown(player, loc, time, canBack, false);
/*    */   }
/*    */   
/*    */   public static void teleportAfterCooldown(Player player, Location loc, Integer time, boolean canBack, boolean canGoSameWorld) {
    if(MapManager.isInAnyMaps(player) != null){
        player.sendMessage("§cPvP장에서는 순간이동이 불가능합니다.");
        return;
    }
/* 28 */     if (teleportingPlayers.contains(player.getName())) {
/* 29 */       player.sendMessage("§c이미 순간이동을 하고 있습니다.");
/*    */       return;
/*    */     } 
/* 32 */     if (!canGoSameWorld && Objects.equals(loc.getWorld().getName(), player.getLocation().getWorld().getName())) {
/* 33 */       player.sendMessage("§c이미 같은 월드에 있어 순간이동이 불가능합니다.");
/*    */       return;
/*    */     } 
/* 36 */     Location location = player.getLocation();
/* 37 */     AtomicInteger i = new AtomicInteger(time.intValue());
/*    */     
/* 39 */     teleportingPlayers.add(player.getName());
/*    */     
/* 41 */     (new Thread(() -> {
/*    */           for (int j = 0; j < time.intValue(); j++) {
/*    */             player.sendMessage("§e§l" + i.get() + "§f초 후에 이동합니다.");
/*    */             
/*    */             player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100.0F, 3.0F);
/*    */             i.getAndDecrement();
/*    */             if (j == time.intValue() - 1) {
/*    */               try {
/*    */                 Thread.sleep(1000L);
/* 50 */               } catch (InterruptedException e) {
/*    */                 throw new RuntimeException(e);
/*    */               } 
/*    */               
/*    */               player.teleportAsync(loc);
/*    */               
/*    */               player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 100.0F, 1.2F);
/*    */               teleportingPlayers.remove(player.getName());
/*    */               if (canBack) {
/*    */                 TextComponent message = new TextComponent("[ 되돌아가기 ]");
/*    */                 message.setColor(ChatColor.YELLOW);
/*    */                 message.setBold(Boolean.valueOf(true));
/*    */                 message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/backto"));
/*    */                 player.spigot().sendMessage((BaseComponent)message);
/*    */                 BackToCommand.backPlayers.put(player.getName(), location);
/*    */               } 
/*    */             } 
/*    */             try {
/*    */               Thread.sleep(1000L);
/* 69 */             } catch (InterruptedException e) {
/*    */               throw new RuntimeException(e);
/*    */             } 
/*    */           } 
/* 73 */         })).start();
/*    */   }
/*    */   
/*    */   public static Location getRandomLoc() {
/* 77 */     int x = (int)(Math.random() * 2001.0D + -1000.0D);
/* 78 */     int z = (int)(Math.random() * 2001.0D + -1000.0D);
/* 79 */     int maxY = 0;
/*    */     
/* 81 */     for (int y = 30; y < 256; y++) {
/* 82 */       Block block = Bukkit.getWorld("world").getBlockAt(x, y, z);
/* 83 */       Block blockUp = Bukkit.getWorld("world").getBlockAt(x, y + 1, z);
/* 84 */       if (block.getType() != Material.AIR && blockUp.getType() == Material.AIR) maxY = y;
/*    */     
/*    */     } 
/* 87 */     return new Location(Bukkit.getWorld("world"), x, maxY, z);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\cor\\utils\TeleportUtil.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */