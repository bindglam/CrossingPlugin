/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import org.bukkit.NamespacedKey;
/*    */ 
/*    */ public class PlayerGeneratesContainerLoot
/*    */   implements Trigger {
/*    */   @Expose
/*    */   @SerializedName("loot_table")
/*    */   private NamespacedKey lootTable;
/*    */   
/*    */   public void setLootTable(NamespacedKey lootTable) {
/* 14 */     this.lootTable = lootTable;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\PlayerGeneratesContainerLoot.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */