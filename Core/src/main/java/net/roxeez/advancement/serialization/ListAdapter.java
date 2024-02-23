/*    */ package net.roxeez.advancement.serialization;
/*    */ 
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.List;
/*    */ 
/*    */ public final class ListAdapter
/*    */   implements JsonSerializer<List<?>>
/*    */ {
/*    */   public JsonElement serialize(List<?> src, Type typeOfSrc, JsonSerializationContext context) {
/* 14 */     if (src == null || src.isEmpty()) {
/* 15 */       return null;
/*    */     }
/* 17 */     JsonArray array = new JsonArray();
/*    */     
/* 19 */     for (Object child : src) {
/* 20 */       JsonElement element = context.serialize(child);
/* 21 */       array.add(element);
/*    */     } 
/*    */     
/* 24 */     return (JsonElement)array;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\serialization\ListAdapter.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */