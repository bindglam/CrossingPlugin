/*    */ package io.github.bindglam.core.managers;
/*    */ import io.github.bindglam.core.Core;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class UserShopManager {
/* 10 */   public static final HashMap<UUID, List<Object>> data = new HashMap<>();
/*    */   
/*    */   public static void load() {
/* 13 */     for (String strUUID : Core.INSTANCE.getConfig().getStringList("UserShopUUIDs")) {
/* 14 */       UUID uuid = UUID.fromString(strUUID);
/* 15 */       data.put(uuid, List.of(Objects.requireNonNull(Core.INSTANCE.getConfig().getItemStack("UserShopItemStacks." + uuid)), Objects.requireNonNull(Core.INSTANCE.getConfig().get("UserShopItemCosts." + uuid)), 
/* 16 */             UUID.fromString(Objects.<String>requireNonNull(Core.INSTANCE.getConfig().getString("UserShopItemOwners." + uuid)))));
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void save() {
/* 21 */     List<String> uuidData = new ArrayList<>();
/* 22 */     for (UUID uuid : data.keySet()) {
/* 23 */       uuidData.add(uuid.toString());
/* 24 */       Core.INSTANCE.getConfig().set("UserShopItemStacks." + uuid, data.get(uuid).get(0));
/* 25 */       Core.INSTANCE.getConfig().set("UserShopItemCosts." + uuid, data.get(uuid).get(1));
/* 26 */       Core.INSTANCE.getConfig().set("UserShopItemOwners." + uuid, data.get(uuid).get(2).toString());
/*    */     } 
/* 28 */     Core.INSTANCE.getConfig().set("UserShopUUIDs", uuidData);
/*    */   }
/*    */   
/*    */   public static List<Integer> getItemIDList(UUID uuid) {
/* 32 */     List<Integer> ids = new ArrayList<>();
/* 33 */     for (int i = 0; i < data.keySet().size(); i++) {
/* 34 */       UUID itemUUID = data.keySet().stream().toList().get(i);
/* 35 */       UUID otheruuid = (UUID) data.get(itemUUID).get(2);
/* 36 */       if (otheruuid == uuid) {
/* 37 */         ids.add(Integer.valueOf(i));
/*    */       }
/*    */     } 
/* 40 */     return ids;
/*    */   }
/*    */   
/*    */   public static UUID getItemUUID(int id) {
/* 44 */     for (int i = 0; i < data.keySet().size(); i++) {
/* 45 */       UUID itemUUID = data.keySet().stream().toList().get(i);
/* 46 */       if (i == id) {
/* 47 */         return itemUUID;
/*    */       }
/*    */     } 
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\managers\UserShopManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */