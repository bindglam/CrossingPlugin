/*    */ package net.roxeez.advancement.display;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Icon
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private NamespacedKey item;
/*    */   @Expose
/*    */   @SerializedName("nbt")
/*    */   private String nbt;
/*    */   
/*    */   public Icon() {}
/*    */   
/*    */   public Icon(@NotNull NamespacedKey item, String nbt) {
/* 33 */     Preconditions.checkNotNull(item);
/* 34 */     this.item = item;
/* 35 */     this.nbt = nbt;
/*    */   }
/*    */   
/*    */   public Icon(@NotNull Material material, String nbt) {
/* 39 */     Preconditions.checkNotNull(material);
/* 40 */     this.item = material.getKey();
/* 41 */     this.nbt = nbt;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setItem(@NotNull Material material) {
/* 50 */     Preconditions.checkNotNull(material);
/* 51 */     this.item = material.getKey();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setNbt(String nbt) {
/* 60 */     this.nbt = nbt;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\display\Icon.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */