/*    */ package net.roxeez.advancement;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import net.roxeez.advancement.trigger.Trigger;
/*    */ import net.roxeez.advancement.trigger.TriggerWrapper;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Criteria
/*    */ {
/*    */   private final String name;
/*    */   @Expose
/*    */   @SerializedName("trigger")
/*    */   private final NamespacedKey trigger;
/*    */   @Expose
/*    */   @SerializedName("conditions")
/*    */   private final Trigger data;
/*    */   
/*    */   public Criteria(@NotNull String name, @NotNull TriggerWrapper<?> trigger, @NotNull Trigger data) {
/* 24 */     Preconditions.checkNotNull(name);
/* 25 */     Preconditions.checkNotNull(trigger);
/* 26 */     Preconditions.checkNotNull(data);
/*    */     
/* 28 */     this.name = name;
/* 29 */     this.trigger = trigger.getKey();
/* 30 */     this.data = data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 39 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\Criteria.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */