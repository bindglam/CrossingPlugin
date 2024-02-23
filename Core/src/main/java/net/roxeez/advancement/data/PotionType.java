/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.util.Arrays;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum PotionType
/*    */ {
/* 14 */   UNCRAFTABLE("empty"),
/* 15 */   WATER("water"),
/* 16 */   MUNDANE("mundane"),
/* 17 */   THICK("thick"),
/* 18 */   AWKWARD("awkward"),
/* 19 */   NIGHT_VISION("night_vision"),
/* 20 */   NIGHT_VISION_EXTENDED("long_night_vision"),
/* 21 */   INVISIBILITY("invisibility"),
/* 22 */   INVISIBILITY_EXTENDED("long_invisibility"),
/* 23 */   LEAPING("leaping"),
/* 24 */   LEAPING_II("strong_leaping"),
/* 25 */   LEAPING_EXTENDED("long_leaping"),
/* 26 */   FIRE_RESISTANCE("fire_resistance"),
/* 27 */   FIRE_RESISTANCE_EXTENDED("fire_resistance_extended"),
/* 28 */   SWIFTNESS("swiftness"),
/* 29 */   SWIFTNESS_II("strong_swiftness"),
/* 30 */   SWIFTNESS_EXTENDED("long_swiftness"),
/* 31 */   SLOWNESS("slowness"),
/* 32 */   SLOWNESS_II("strong_slowness"),
/* 33 */   SLOWNESS_EXTENDED("long_slowness"),
/* 34 */   WATER_BREATHING("water_breathing"),
/* 35 */   WATER_BREATHING_EXTENDED("long_water_breathing"),
/* 36 */   INSTANT_HEALTH("healing"),
/* 37 */   INSTANT_HEALTH_II("strong_healing"),
/* 38 */   HARMING("harming"),
/* 39 */   HARMING_II("strong_harming"),
/* 40 */   POISON("poison"),
/* 41 */   POISON_II("strong_poison"),
/* 42 */   POISON_EXTENDED("long_poison"),
/* 43 */   REGENERATION("regeneration"),
/* 44 */   REGENERATION_II("strong_regeneration"),
/* 45 */   REGENERATION_EXTENDED("long_regeneration"),
/* 46 */   STRENGTH("strength"),
/* 47 */   STRENGTH_II("strong_strength"),
/* 48 */   STRENGTH_EXTENDED("long_strength"),
/* 49 */   WEAKNESS("weakness"),
/* 50 */   WEAKNESS_EXTENDED("long_weakness"),
/* 51 */   LUCK("luck"),
/* 52 */   THE_TURTLE_MASTER("turtle_master"),
/* 53 */   THE_TURTLE_MASTER_II("strong_turtle_master"),
/* 54 */   THE_TURTLE_MASTER_EXTENDED("long_turtle_master"),
/* 55 */   SLOW_FALLING("slow_falling"),
/* 56 */   SLOW_FALLING_EXTENDED("long_slow_falling");
/*    */   
/*    */   private final NamespacedKey key;
/*    */   
/*    */   PotionType(String name) {
/* 61 */     this.key = NamespacedKey.minecraft(name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public NamespacedKey getKey() {
/* 71 */     return this.key;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static PotionType getByName(@NotNull String name) {
/* 76 */     Preconditions.checkNotNull(name);
/* 77 */     return Arrays.<PotionType>stream(values()).filter(x -> x.getKey().getKey().equals(name)).findFirst().orElse(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\PotionType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */