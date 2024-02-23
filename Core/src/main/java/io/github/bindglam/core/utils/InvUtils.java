/*    */ package io.github.bindglam.core.utils;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvUtils
/*    */ {
/*    */   public static boolean consumeItem(Player player, int count, Material mat) {
/* 15 */     Map<Integer, ? extends ItemStack> ammo = player.getInventory().all(mat);
/*    */     
/* 17 */     int found = 0;
/* 18 */     for (ItemStack stack : ammo.values())
/* 19 */       found += stack.getAmount(); 
/* 20 */     if (count > found) {
/* 21 */       return false;
/*    */     }
/* 23 */     for (Integer index : ammo.keySet()) {
/* 24 */       ItemStack stack = ammo.get(index);
/*    */       
/* 26 */       int removed = Math.min(count, stack.getAmount());
/* 27 */       count -= removed;
/*    */       
/* 29 */       if (stack.getAmount() == removed) {
/* 30 */         player.getInventory().setItem(index.intValue(), null);
/*    */       } else {
/* 32 */         stack.setAmount(stack.getAmount() - removed);
/*    */       } 
/* 34 */       if (count <= 0) {
/*    */         break;
/*    */       }
/*    */     } 
/* 38 */     player.updateInventory();
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean consumeItem(Player player, int count, ItemStack mat) {
/* 44 */     Map<Integer, ? extends ItemStack> ammo = player.getInventory().all(mat);
/*    */     
/* 46 */     int found = 0;
/* 47 */     for (ItemStack stack : ammo.values())
/* 48 */       found += stack.getAmount(); 
/* 49 */     if (count > found) {
/* 50 */       return false;
/*    */     }
/* 52 */     for (Integer index : ammo.keySet()) {
/* 53 */       ItemStack stack = ammo.get(index);
/*    */       
/* 55 */       int removed = Math.min(count, stack.getAmount());
/* 56 */       count -= removed;
/*    */       
/* 58 */       if (stack.getAmount() == removed) {
/* 59 */         player.getInventory().setItem(index.intValue(), null);
/*    */       } else {
/* 61 */         stack.setAmount(stack.getAmount() - removed);
/*    */       } 
/* 63 */       if (count <= 0) {
/*    */         break;
/*    */       }
/*    */     } 
/* 67 */     player.updateInventory();
/* 68 */     return true;
/*    */   }
/*    */   
/*    */   public static boolean hasInventorySpace(Inventory inventory) {
/* 72 */     for (ItemStack itemStack : inventory.getContents()) {
/* 73 */       if (itemStack == null)
/* 74 */         return true; 
/*    */     } 
/* 76 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\cor\\utils\InvUtils.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */