/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.NamespacedKey;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerData
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("gamemode")
/*    */   private GameMode gameMode;
/*    */   @Expose
/*    */   @SerializedName("level")
/*    */   private Range<Integer> level;
/*    */   @Expose
/*    */   @SerializedName("recipes")
/*    */   private Map<NamespacedKey, Boolean> recipes;
/*    */   
/*    */   public void setGameMode(GameMode gameMode) {
/* 26 */     this.gameMode = gameMode;
/*    */   }
/*    */   
/*    */   public void setLevel(int level) {
/* 30 */     this.level = new Range<>(Integer.valueOf(level));
/*    */   }
/*    */   
/*    */   public void setLevel(int minimum, int maximum) {
/* 34 */     this.level = new Range<>(Integer.valueOf(minimum), Integer.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setRecipe(NamespacedKey id, boolean known) {
/* 38 */     if (this.recipes == null) {
/* 39 */       this.recipes = new HashMap<>();
/*    */     }
/*    */     
/* 42 */     this.recipes.put(id, Boolean.valueOf(known));
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\PlayerData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */