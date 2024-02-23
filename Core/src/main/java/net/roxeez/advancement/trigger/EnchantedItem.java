/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ import net.roxeez.advancement.data.Range;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantedItem
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private ItemData item;
/*    */   @Expose
/*    */   @SerializedName("levels")
/*    */   private Range<Integer> levels;
/*    */   
/*    */   public void setItem(Material material) {
/* 26 */     setItem(x -> x.setType(material));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setItem(Consumer<ItemData> consumer) {
/* 35 */     this.item = new ItemData();
/* 36 */     consumer.accept(this.item);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLevels(int levels) {
/* 45 */     this.levels = new Range(Integer.valueOf(levels));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLevels(int minimum, int maximum) {
/* 55 */     this.levels = new Range(Integer.valueOf(minimum), Integer.valueOf(maximum));
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\EnchantedItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */