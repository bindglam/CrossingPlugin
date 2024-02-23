/*    */ package net.roxeez.advancement.serialization;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import net.roxeez.advancement.display.FrameType;
/*    */ 
/*    */ public class FrameTypeAdapter
/*    */   implements JsonSerializer<FrameType>
/*    */ {
/*    */   public JsonElement serialize(FrameType src, Type typeOfSrc, JsonSerializationContext context) {
/* 14 */     return (JsonElement)new JsonPrimitive(src.getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\serialization\FrameTypeAdapter.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */