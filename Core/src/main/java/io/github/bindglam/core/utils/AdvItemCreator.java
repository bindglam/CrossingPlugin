/*     */ package io.github.bindglam.core.utils;
/*     */ 
/*     */ import io.github.bindglam.core.Core;
/*     */ import java.util.List;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.EnchantmentStorageMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.PotionMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ import org.bukkit.persistence.PersistentDataContainer;
/*     */ import org.bukkit.persistence.PersistentDataType;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ 
/*     */ public class AdvItemCreator {
/*     */   private ItemStack itemStack;
/*     */   
/*     */   public AdvItemCreator(Material material) {
/*  23 */     this.itemStack = new ItemStack(material);
/*     */   }
/*     */   
/*     */   public AdvItemCreator(ItemStack item) {
/*  27 */     this.itemStack = item.clone();
/*     */   }
/*     */   
/*     */   public AdvItemCreator setDisplayName(Component displayName) {
/*  31 */     ItemMeta meta = getItemMeta();
/*  32 */     meta.displayName(displayName);
/*  33 */     this.itemStack.setItemMeta(meta);
/*  34 */     return this;
/*     */   }
/*     */   
/*     */   public AdvItemCreator setDisplayName(String displayName) {
/*  38 */     ItemMeta meta = getItemMeta();
/*  39 */     meta.setDisplayName(displayName);
/*  40 */     this.itemStack.setItemMeta(meta);
/*  41 */     return this;
/*     */   }
/*     */   
/*     */   public AdvItemCreator setLore(Component... lore) {
/*  45 */     ItemMeta meta = getItemMeta();
/*  46 */     meta.lore(List.of(lore));
/*  47 */     this.itemStack.setItemMeta(meta);
/*  48 */     return this;
/*     */   }
/*     */   
/*     */   public AdvItemCreator setLore(List<String> lore) {
/*  52 */     ItemMeta meta = getItemMeta();
/*  53 */     meta.setLore(lore);
/*  54 */     this.itemStack.setItemMeta(meta);
/*  55 */     return this;
/*     */   }
/*     */   
/*     */   public AdvItemCreator addEnchantment(Enchantment ench, int level) {
/*  59 */     this.itemStack.addUnsafeEnchantment(ench, level);
/*  60 */     return this;
/*     */   }
/*     */   
/*     */   public AdvItemCreator addPersistentData(String key, int value) {
/*  64 */     ItemMeta meta = getItemMeta();
/*  65 */     PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
/*  66 */     dataContainer.set(new NamespacedKey((Plugin)Core.INSTANCE, key), PersistentDataType.INTEGER, Integer.valueOf(value));
/*  67 */     this.itemStack.setItemMeta(meta);
/*  68 */     return this;
/*     */   }
/*     */   
/*     */   public AdvItemCreator addPersistentData(String key, String value) {
/*  72 */     ItemMeta meta = getItemMeta();
/*  73 */     PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
/*  74 */     dataContainer.set(new NamespacedKey((Plugin)Core.INSTANCE, key), PersistentDataType.STRING, value);
/*  75 */     this.itemStack.setItemMeta(meta);
/*  76 */     return this;
/*     */   }
/*     */   
/*     */   public AdvItemCreator addPersistentData(String key, boolean value) {
/*  80 */     ItemMeta meta = getItemMeta();
/*  81 */     PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
/*  82 */     dataContainer.set(new NamespacedKey((Plugin)Core.INSTANCE, key), PersistentDataType.BOOLEAN, Boolean.valueOf(value));
/*  83 */     this.itemStack.setItemMeta(meta);
/*  84 */     return this;
/*     */   }
/*     */   
/*     */   public AdvItemCreator makeHead(String name) {
/*  88 */     this.itemStack = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
/*  89 */     SkullMeta skull = (SkullMeta)this.itemStack.getItemMeta();
/*  90 */     skull.setOwner(name);
/*  91 */     this.itemStack.setItemMeta((ItemMeta)skull);
/*  92 */     return this;
/*     */   }
/*     */   
/*     */   public ItemStack getItemStack() {
/*  96 */     return this.itemStack;
/*     */   }
/*     */   
/*     */   public ItemMeta getItemMeta() {
/* 100 */     return this.itemStack.getItemMeta();
/*     */   }
/*     */   
/*     */   public static ItemStack getEnchantedBook(Enchantment ench, int level) {
/* 104 */     ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
/* 105 */     EnchantmentStorageMeta meta = (EnchantmentStorageMeta)itemStack.getItemMeta();
/* 106 */     meta.addStoredEnchant(ench, level, true);
/* 107 */     itemStack.setItemMeta((ItemMeta)meta);
/* 108 */     return itemStack;
/*     */   }
/*     */   
/*     */   public static ItemStack getCustomPotion(PotionEffect effect) {
/* 112 */     ItemStack itemStack = new ItemStack(Material.POTION);
/* 113 */     PotionMeta meta = (PotionMeta)itemStack.getItemMeta();
/* 114 */     meta.addCustomEffect(effect, true);
/* 115 */     itemStack.setItemMeta((ItemMeta)meta);
/* 116 */     return itemStack;
/*     */   }
/*     */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\cor\\utils\AdvItemCreator.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */