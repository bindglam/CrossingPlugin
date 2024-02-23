/*    */ package net.roxeez.advancement;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.util.Map;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Context
/*    */ {
/*    */   private final Plugin plugin;
/*    */   private final Map<NamespacedKey, Advancement> advancements;
/*    */   
/*    */   public Context(@NotNull Plugin plugin, @NotNull Map<NamespacedKey, Advancement> advancements) {
/* 26 */     this.plugin = plugin;
/* 27 */     this.advancements = advancements;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Plugin getPlugin() {
/* 37 */     return this.plugin;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Advancement getAdvancement(@NotNull String id) {
/* 49 */     Preconditions.checkNotNull(id);
/* 50 */     return this.advancements.get(new NamespacedKey(this.plugin, id));
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\Context.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */