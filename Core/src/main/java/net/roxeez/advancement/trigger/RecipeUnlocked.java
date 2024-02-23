/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import org.bukkit.NamespacedKey;
/*    */ 
/*    */ public class RecipeUnlocked
/*    */   implements Trigger {
/*    */   @Expose
/*    */   @SerializedName("recipe")
/*    */   private NamespacedKey recipe;
/*    */   
/*    */   public void setRecipe(NamespacedKey recipe) {
/* 14 */     this.recipe = recipe;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\RecipeUnlocked.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */