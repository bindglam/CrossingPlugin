/*    */ package net.roxeez.advancement.serialization;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import org.bukkit.GameMode;
/*    */ 
/*    */ public class GameModeAdapter
/*    */   implements JsonSerializer<GameMode>
/*    */ {
/*    */   public JsonElement serialize(GameMode src, Type typeOfSrc, JsonSerializationContext context) {
/* 14 */     return (JsonElement)new JsonPrimitive(src.name().toLowerCase());
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\serialization\GameModeAdapter.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */