/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ public class BlockData
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("block")
/*    */   private Material block;
/*    */   
/*    */   public void setType(Material material) {
/* 15 */     this.block = material;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\BlockData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */