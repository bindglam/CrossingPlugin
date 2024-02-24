/*    */ package io.github.bindglam.core.managers;
/*    */ 
/*    */ import io.github.bindglam.core.Core;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
import java.util.List;
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ 
/*    */ public class DivingPointManager
/*    */ {
/* 12 */   public static final HashMap<UUID, Integer> divingPoints = new HashMap<>();
/* 13 */   public static final HashMap<UUID, Integer> divingTimes = new HashMap<>();
/*    */   
/*    */   public static void init() {
/* 16 */     Core.INSTANCE.getConfig().getStringList("divingPoints").forEach(str -> {
/*    */           UUID uuid = UUID.fromString(str.split(":")[0]);
                    try {
                        /*    */
                        Integer point = Integer.valueOf(Integer.parseInt(str.split(":")[1]));
                        /*    */
                        divingPoints.put(uuid, point);
                    } catch (NumberFormatException ignored){
                    }
/*    */         });
/*    */   }
/*    */   
/*    */   public static void save() {
/* 24 */     List<String> data = new ArrayList<>();
/* 25 */     for (UUID uuid : divingPoints.keySet()) {
/* 26 */       data.add(uuid.toString() + ":" + divingPoints.get(uuid));
/*    */     }
/* 28 */     Core.INSTANCE.getConfig().set("divingPoints", data);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\managers\DivingPointManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */