/*    */ package net.roxeez.advancement.trigger;
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ import org.bukkit.Material;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class InventoryChanged implements Trigger {
/*    */   @Expose
/*    */   @SerializedName("items")
/* 15 */   private final List<ItemData> items = new ArrayList<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void hasItemMatching(@NotNull Consumer<ItemData> consumer) {
/* 25 */     Preconditions.checkNotNull(consumer);
/* 26 */     ItemData predicate = new ItemData();
/* 27 */     consumer.accept(predicate);
/*    */     
/* 29 */     this.items.add(predicate);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void hasItem(@NotNull Material material) {
/* 39 */     Preconditions.checkNotNull(material);
/* 40 */     ItemData predicate = new ItemData();
/* 41 */     predicate.setType(material);
/*    */     
/* 43 */     this.items.add(predicate);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\InventoryChanged.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */