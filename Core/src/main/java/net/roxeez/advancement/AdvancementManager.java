/*     */ package net.roxeez.advancement;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import net.roxeez.advancement.serialization.ObjectSerializer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdvancementManager
/*     */ {
/*     */   private final Plugin plugin;
/*  22 */   private final ObjectSerializer serializer = new ObjectSerializer();
/*  23 */   private final Map<NamespacedKey, Advancement> advancements = new LinkedHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AdvancementManager(@NotNull Plugin plugin) {
/*  31 */     Preconditions.checkNotNull(plugin);
/*  32 */     this.plugin = plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ImmutableList<Advancement> getAdvancements() {
/*  42 */     return ImmutableList.copyOf(this.advancements.values());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Advancement getAdvancement(@NotNull String id) {
/*  53 */     Preconditions.checkNotNull(id);
/*  54 */     return this.advancements.get(new NamespacedKey(this.plugin, id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(@NotNull AdvancementCreator creator) {
/*  63 */     Preconditions.checkNotNull(creator);
/*  64 */     Advancement advancement = creator.create(new Context(this.plugin, this.advancements));
/*  65 */     this.advancements.put(advancement.getKey(), advancement);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(@NotNull Advancement advancement) {
/*  74 */     Preconditions.checkNotNull(advancement);
/*  75 */     this.advancements.put(advancement.getKey(), advancement);
/*     */     
/*  77 */     if (advancement instanceof Listener) {
/*  78 */       Bukkit.getPluginManager().registerEvents((Listener)advancement, this.plugin);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createAll(boolean clean) {
/*  89 */     if (clean) {
/*  90 */       this.plugin.getLogger().info("[AdvancementAPI] Cleaning previously generated advancements");
/*  91 */       for (Advancement advancement : this.advancements.values()) {
/*  92 */         if (Bukkit.getAdvancement(advancement.getKey()) != null) {
/*  93 */           Bukkit.getUnsafe().removeAdvancement(advancement.getKey());
/*     */         }
/*     */       } 
/*     */       
/*  97 */       Bukkit.reloadData();
/*     */     } 
/*     */     
/* 100 */     this.plugin.getLogger().info("[AdvancementAPI] Generating advancements");
/* 101 */     for (Advancement advancement : this.advancements.values()) {
/* 102 */       if (Bukkit.getAdvancement(advancement.getKey()) == null) {
/* 103 */         Bukkit.getUnsafe().loadAdvancement(advancement.getKey(), this.serializer.serialize(advancement));
/*     */       }
/*     */     } 
/*     */     
/* 107 */     Bukkit.reloadData();
/*     */   }
/*     */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\AdvancementManager.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */