/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import org.bukkit.NamespacedKey;
/*    */ 
/*    */ public enum EffectType {
/*  6 */   ABSORPTION("absorption"),
/*  7 */   BAD_OMEN("bad_omen"),
/*  8 */   BLINDNESS("blindness"),
/*  9 */   CONDUIT_POWER("conduit_power"),
/* 10 */   DOLPHINS_GRACE("dolphins_grace"),
/* 11 */   FIRE_RESISTANCE("fire_resistance"),
/* 12 */   GLOWING("glowing"),
/* 13 */   HASTE("haste"),
/* 14 */   HEALTH_BOOST("health_boost"),
/* 15 */   HERO_OF_THE_VILLAGE("hero_of_the_village"),
/* 16 */   HUNGER("hunger"),
/* 17 */   INSTANT_HEALTH("instant_health"),
/* 18 */   INSTANT_DAMAGE("instant_damage"),
/* 19 */   INVISIBILITY("invisibility"),
/* 20 */   JUMP_BOOST("jump_boost"),
/* 21 */   LEVITATION("levitation"),
/* 22 */   LUCK("luck"),
/* 23 */   MINING_FATIGUE("mining_fatigue"),
/* 24 */   NAUSEA("nausea"),
/* 25 */   NIGHT_VISION("night_vision"),
/* 26 */   POISON("poison"),
/* 27 */   REGENERATION("regeneration"),
/* 28 */   RESISTANCE("resistance"),
/* 29 */   SATURATION("saturation"),
/* 30 */   SLOW_FALLING("slow_falling"),
/* 31 */   SLOWNESS("slowness"),
/* 32 */   SPEED("speed"),
/* 33 */   STRENGTH("strength"),
/* 34 */   BAD_LUCK("bad_luck"),
/* 35 */   WATER_BREATHING("water_breathing"),
/* 36 */   WEAKNESS("weakness"),
/* 37 */   WITHER("wither");
/*    */   
/*    */   private final NamespacedKey key;
/*    */   
/*    */   EffectType(String name) {
/* 42 */     this.key = NamespacedKey.minecraft(name);
/*    */   }
/*    */   
/*    */   public NamespacedKey getKey() {
/* 46 */     return this.key;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\EffectType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */