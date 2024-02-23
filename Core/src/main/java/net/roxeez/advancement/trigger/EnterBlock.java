/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import net.roxeez.advancement.utility.KeyValue;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnterBlock
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("block")
/*    */   private Material block;
/*    */   @Expose
/*    */   @SerializedName("state")
/*    */   private KeyValue<String, String> state;
/*    */   
/*    */   public void setBlock(Material block) {
/* 24 */     this.block = block;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setState(String name, String value) {
/* 34 */     this.state = new KeyValue(name, value);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\EnterBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */