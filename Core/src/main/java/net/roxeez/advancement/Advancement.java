/*     */ package net.roxeez.advancement;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Consumer;
/*     */ import net.roxeez.advancement.display.Display;
/*     */ import net.roxeez.advancement.trigger.Trigger;
/*     */ import net.roxeez.advancement.trigger.TriggerWrapper;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Advancement
/*     */ {
/*     */   private final NamespacedKey key;
/*     */   @Expose
/*     */   @SerializedName("criteria")
/*  33 */   private final Map<String, Criteria> criteria = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Expose
/*     */   @SerializedName("requirements")
/*  40 */   private final List<String[]> requirements = (List)new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Expose
/*     */   @SerializedName("display")
/*     */   private Display display;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Expose
/*     */   @SerializedName("parent")
/*     */   private NamespacedKey parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Advancement(@NotNull NamespacedKey key) {
/*  64 */     Preconditions.checkNotNull(key);
/*  65 */     this.key = key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Advancement(@NotNull Plugin plugin, @NotNull String id) {
/*  75 */     Preconditions.checkNotNull(plugin);
/*  76 */     Preconditions.checkNotNull(id);
/*  77 */     this.key = new NamespacedKey(plugin, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParent(@NotNull NamespacedKey key) {
/*  87 */     Preconditions.checkNotNull(key);
/*  88 */     this.parent = key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisplay(@NotNull Consumer<Display> display) {
/*  98 */     Preconditions.checkNotNull(display);
/*     */     
/* 100 */     Display object = new Display();
/* 101 */     display.accept(object);
/*     */     
/* 103 */     this.display = object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRequirement(@NotNull Criteria criteria) {
/* 116 */     Preconditions.checkNotNull(criteria);
/* 117 */     this.requirements.add(new String[] { criteria.getName() });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRequirements(@NotNull Criteria... criterion) {
/* 130 */     Preconditions.checkNotNull(criterion);
/* 131 */     this.requirements.add((String[])Arrays.<Criteria>stream(criterion).map(Criteria::getName).toArray(x$0 -> new String[x$0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public <T extends Trigger> Criteria addCriteria(@NotNull String name, @NotNull TriggerWrapper<T> trigger, @NotNull Consumer<T> data) {
/*     */     Trigger trigger1;
/* 145 */     Preconditions.checkNotNull(name);
/* 146 */     Preconditions.checkNotNull(trigger);
/* 147 */     Preconditions.checkNotNull(data);
/*     */     
/* 149 */     if (this.criteria.containsKey(name)) {
/* 150 */       return this.criteria.get(name);
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 155 */       trigger1 = trigger.getClazz().newInstance();
/*     */     }
/* 157 */     catch (Exception e) {
/* 158 */       throw new RuntimeException(e);
/*     */     } 
/*     */     
/* 161 */     data.accept((T)trigger1);
/*     */     
/* 163 */     Criteria criteria = new Criteria(name, trigger, trigger1);
/*     */     
/* 165 */     this.criteria.put(name, criteria);
/*     */     
/* 167 */     return criteria;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamespacedKey getKey() {
/* 176 */     return this.key;
/*     */   }
/*     */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\Advancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */