/*    */ package net.roxeez.advancement.display;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum FrameType
/*    */ {
/*  7 */   CHALLENGE("challenge"),
/*  8 */   TASK("task"),
/*  9 */   GOAL("goal");
/*    */   
/*    */   private final String name;
/*    */   
/*    */   FrameType(String name) {
/* 14 */     this.name = name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 23 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\display\FrameType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */