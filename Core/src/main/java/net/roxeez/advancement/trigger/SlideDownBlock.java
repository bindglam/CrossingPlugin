/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ public class SlideDownBlock
/*    */   implements Trigger {
/*    */   @Expose
/*    */   @SerializedName("block")
/*    */   private Material block;
/*    */   
/*    */   public void setBlock(Material block) {
/* 14 */     this.block = block;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\SlideDownBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */