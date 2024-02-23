/*    */ package net.roxeez.advancement.serialization;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import net.roxeez.advancement.utility.KeyValue;
/*    */ 
/*    */ public class KeyValueAdapter
/*    */   implements JsonSerializer<KeyValue<?, ?>>
/*    */ {
/*    */   public JsonElement serialize(KeyValue<?, ?> src, Type typeOfSrc, JsonSerializationContext context) {
/* 14 */     JsonObject object = new JsonObject();
/* 15 */     object.add(context.serialize(src.getKey()).getAsString(), context.serialize(src.getValue()));
/* 16 */     return (JsonElement)object;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\serialization\KeyValueAdapter.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */