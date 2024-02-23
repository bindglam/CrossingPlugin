/*    */ package net.roxeez.advancement.display;
/*    */ 
/*    */ import org.bukkit.NamespacedKey;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum BackgroundType
/*    */ {
/* 10 */   BEDROCK("bedrock"),
/* 11 */   STONE("stone"),
/* 12 */   SMOOTH_STONE("smooth_stone"),
/* 13 */   SMOOTH_STONE_SLAB("smooth_stone_slab"),
/* 14 */   STONE_BRICKS("stone_bricks"),
/* 15 */   CRACKED_STONE_BRICKS("cracked_stone_bricks"),
/* 16 */   MOSSY_STONE_BRICKS("mossy_stone_bricks"),
/* 17 */   CHISELED_STONE_BRICKS("chiseled_stone_bricks"),
/* 18 */   COBBLESTONE("cobblestone"),
/* 19 */   MOSSY_COBBLESTONE("mossy_cobblestone"),
/* 20 */   GRANITE("granite"),
/* 21 */   POLISHED_GRANITE("polished_granite"),
/* 22 */   DIORITE("diorite"),
/* 23 */   POLISHED_DIORITE("polished_diorite"),
/* 24 */   ANDESITE("andesite"),
/* 25 */   POLISHED_ANDESITE("polished_andesite");
/*    */   
/*    */   private final NamespacedKey texture;
/*    */   
/*    */   BackgroundType(String texture) {
/* 30 */     this.texture = NamespacedKey.minecraft("textures/block/" + texture + ".png");
/*    */   }
/*    */   
/*    */   public NamespacedKey getTexture() {
/* 34 */     return this.texture;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\display\BackgroundType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */