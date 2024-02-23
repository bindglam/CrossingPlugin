/*    */ package net.roxeez.advancement.utility;
/*    */ 
/*    */ public final class KeyValue<K, V>
/*    */ {
/*    */   private final K key;
/*    */   private final V value;
/*    */   
/*    */   public KeyValue(K key, V value) {
/*  9 */     this.key = key;
/* 10 */     this.value = value;
/*    */   }
/*    */   
/*    */   public K getKey() {
/* 14 */     return this.key;
/*    */   }
/*    */   
/*    */   public V getValue() {
/* 18 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancemen\\utility\KeyValue.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */