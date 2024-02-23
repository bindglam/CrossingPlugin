/*    */ package net.roxeez.advancement.serialization;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ public class MaterialAdapter
/*    */   implements JsonSerializer<Material>
/*    */ {
/*    */   public JsonElement serialize(Material src, Type typeOfSrc, JsonSerializationContext context) {
/* 14 */     return context.serialize(src.getKey());
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\serialization\MaterialAdapter.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */