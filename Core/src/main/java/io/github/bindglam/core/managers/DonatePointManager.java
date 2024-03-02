/*    */ package io.github.bindglam.core.managers;
/*    */ 
/*    */ import io.github.bindglam.core.Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
import java.util.List;
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ public class DonatePointManager
/*    */ {
/* 11 */   public static final HashMap<UUID, Integer> donatePoints = new HashMap<>();
/*    */   
/*    */   public static void init() {
    Bukkit.broadcast(Component.text("마일리지 로드중...").color(NamedTextColor.WHITE));
/* 14 */     Core.INSTANCE.getConfig().getStringList("donatePoints").forEach(str -> {
/*    */           UUID uuid = UUID.fromString(str.split(":")[0]);
/*    */           Integer point = Integer.valueOf(Integer.parseInt(str.split(":")[1]));
/*    */           donatePoints.put(uuid, point);
/*    */         });
/*    */   }
/*    */   
/*    */   public static void save() {
    Bukkit.broadcast(Component.text("마일리지 저장중...").color(NamedTextColor.WHITE));
/* 22 */     List<String> data = new ArrayList<>();
/* 23 */     for (UUID uuid : donatePoints.keySet()) {
/* 24 */       data.add(uuid.toString() + ":" + donatePoints.get(uuid));
/*    */     }
/* 26 */     Core.INSTANCE.getConfig().set("donatePoints", data);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\managers\DonatePointManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */