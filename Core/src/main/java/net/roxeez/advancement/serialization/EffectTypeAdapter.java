/*    */ package net.roxeez.advancement.serialization;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import net.roxeez.advancement.data.EffectType;
/*    */ 
/*    */ 
/*    */ public class EffectTypeAdapter
/*    */   implements JsonSerializer<EffectType>
/*    */ {
/*    */   public JsonElement serialize(EffectType src, Type typeOfSrc, JsonSerializationContext context) {
/* 14 */     return context.serialize(src.getKey());
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\serialization\EffectTypeAdapter.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */