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
/*    */ public enum DimensionType
/*    */ {
/* 14 */   OVERWORLD("overworld"),
/* 15 */   THE_NETHER("the_nether"),
/* 16 */   THE_END("the_end");
/*    */   
/*    */   private final NamespacedKey name;
/*    */   
/*    */   DimensionType(String name) {
/* 21 */     this.name = NamespacedKey.minecraft(name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NamespacedKey getKey() {
/* 30 */     return this.name;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static DimensionType getByName(@NotNull String name) {
/* 35 */     Preconditions.checkNotNull(name);
/* 36 */     return Arrays.<DimensionType>stream(values()).filter(x -> x.getKey().getKey().equals(name.toLowerCase())).findFirst().orElse(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\DimensionType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */