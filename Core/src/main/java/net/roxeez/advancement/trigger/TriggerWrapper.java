/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TriggerWrapper<T extends Trigger>
/*    */ {
/*    */   private final NamespacedKey key;
/*    */   private final Class<T> clazz;
/*    */   
/*    */   public TriggerWrapper(@NotNull String key, @NotNull Class<T> clazz) {
/* 18 */     Preconditions.checkNotNull(key);
/* 19 */     Preconditions.checkNotNull(clazz);
/*    */     
/* 21 */     this.key = NamespacedKey.minecraft(key);
/* 22 */     this.clazz = clazz;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NamespacedKey getKey() {
/* 31 */     return this.key;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Class<T> getClazz() {
/* 40 */     return this.clazz;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\TriggerWrapper.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */