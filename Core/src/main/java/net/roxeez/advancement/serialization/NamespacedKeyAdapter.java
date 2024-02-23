/*    */ package net.roxeez.advancement.serialization;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import org.bukkit.NamespacedKey;
/*    */ 
/*    */ public class NamespacedKeyAdapter
/*    */   implements JsonSerializer<NamespacedKey>
/*    */ {
/*    */   public JsonElement serialize(NamespacedKey src, Type typeOfSrc, JsonSerializationContext context) {
/* 14 */     return (JsonElement)new JsonPrimitive(src.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\serialization\NamespacedKeyAdapter.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */