/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.ItemData;
/*    */ 
/*    */ public class FilledBucket
/*    */   implements Trigger {
/*    */   @Expose
/*    */   @SerializedName("item")
/*    */   private ItemData item;
/*    */   
/*    */   public void setItem(Consumer<ItemData> consumer) {
/* 15 */     this.item = new ItemData();
/* 16 */     consumer.accept(this.item);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\FilledBucket.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */