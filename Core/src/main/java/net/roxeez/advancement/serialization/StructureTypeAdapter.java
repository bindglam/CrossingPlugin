/*    */ package net.roxeez.advancement.serialization;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import org.bukkit.StructureType;
/*    */ 
/*    */ public class StructureTypeAdapter
/*    */   implements JsonSerializer<StructureType>
/*    */ {
/*    */   public JsonElement serialize(StructureType src, Type typeOfSrc, JsonSerializationContext context) {
/* 14 */     return (JsonElement)new JsonPrimitive(src.getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\serialization\StructureTypeAdapter.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */