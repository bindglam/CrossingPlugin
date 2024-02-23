/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConsumeItem
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private ItemData item;
/*    */   
/*    */   public void setItem(Consumer<ItemData> consumer) {
/* 20 */     this.item = new ItemData();
/* 21 */     consumer.accept(this.item);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setItem(Material material) {
/* 29 */     setItem(item -> item.setType(material));
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\ConsumeItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */