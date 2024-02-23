/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.AdvancementException;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ import org.bukkit.Material;
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
/*    */ public class BeeNestDestroyed
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("block")
/*    */   private Material block;
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private ItemData item;
/*    */   @Expose
/*    */   @SerializedName("numBeesInside")
/*    */   private int numBeesInside;
/*    */   
/*    */   public void setBlock(@NotNull Material block) {
/* 34 */     Preconditions.checkNotNull(block);
/*    */     
/* 36 */     if (!block.isBlock()) {
/* 37 */       throw new AdvancementException("" + block.getKey() + " is not a block");
/*    */     }
/*    */     
/* 40 */     if (block != Material.BEE_NEST && block != Material.BEEHIVE) {
/* 41 */       throw new AdvancementException("" + block.getKey() + " is not a bee nest or beehive");
/*    */     }
/*    */     
/* 44 */     this.block = block;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void withItem(@NotNull Consumer<ItemData> consumer) {
/* 53 */     Preconditions.checkNotNull(consumer);
/* 54 */     this.item = new ItemData();
/* 55 */     consumer.accept(this.item);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void withItem(@NotNull Material material) {
/* 65 */     Preconditions.checkNotNull(material);
/* 66 */     withItem(item -> item.setType(material));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void withBeeCount(int amount) {
/* 75 */     this.numBeesInside = amount;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\BeeNestDestroyed.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */