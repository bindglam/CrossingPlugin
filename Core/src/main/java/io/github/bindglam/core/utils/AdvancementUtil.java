/*    */ package io.github.bindglam.core.utils;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.advancement.Advancement;
/*    */ import org.bukkit.advancement.AdvancementProgress;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class AdvancementUtil {
/*    */   public static void awardAdvancement(Player player, NamespacedKey key, String name) {
/* 12 */     AdvancementProgress progress = player.getAdvancementProgress(Objects.<Advancement>requireNonNull(Bukkit.getAdvancement(key)));
/* 13 */     progress.awardCriteria(name);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\cor\\utils\AdvancementUtil.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */