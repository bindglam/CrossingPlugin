/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import net.roxeez.advancement.data.PotionType;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BrewedPotion
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("potion")
/*    */   private PotionType potion;
/*    */   
/*    */   public void setPotion(@NotNull PotionType type) {
/* 21 */     Preconditions.checkNotNull(type);
/* 22 */     this.potion = type;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\BrewedPotion.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */