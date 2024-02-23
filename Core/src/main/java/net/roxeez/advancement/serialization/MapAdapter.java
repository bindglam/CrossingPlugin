/*    */ package net.roxeez.advancement.serialization;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class MapAdapter
/*    */   implements JsonSerializer<Map<?, ?>>
/*    */ {
/*    */   public JsonElement serialize(Map<?, ?> src, Type typeOfSrc, JsonSerializationContext context) {
/* 15 */     if (src == null || src.isEmpty()) {
/* 16 */       return null;
/*    */     }
/* 18 */     JsonObject object = new JsonObject();
/*    */     
/* 20 */     for (Map.Entry<?, ?> child : src.entrySet()) {
/* 21 */       JsonElement key = context.serialize(child.getKey());
/* 22 */       JsonElement value = context.serialize(child.getValue());
/*    */       
/* 24 */       object.add(key.getAsString(), value);
/*    */     } 
/*    */     
/* 27 */     return (JsonElement)object;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\serialization\MapAdapter.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */