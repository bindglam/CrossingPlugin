/*    */ package net.roxeez.advancement.serialization;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.roxeez.advancement.data.DimensionType;
/*    */ import net.roxeez.advancement.data.EffectType;
/*    */ import net.roxeez.advancement.data.PotionType;
/*    */ import net.roxeez.advancement.display.FrameType;
/*    */ import net.roxeez.advancement.utility.KeyValue;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.StructureType;
/*    */ import org.bukkit.block.Biome;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class ObjectSerializer
/*    */ {
/* 21 */   private static final Gson GSON = (new GsonBuilder())
/* 22 */     .setPrettyPrinting()
/* 23 */     .excludeFieldsWithoutExposeAnnotation()
/* 24 */     .registerTypeHierarchyAdapter(Material.class, new MaterialAdapter())
/* 25 */     .registerTypeHierarchyAdapter(EntityType.class, new EntityTypeAdapter())
/* 26 */     .registerTypeHierarchyAdapter(PotionType.class, new PotionTypeAdapter())
/* 27 */     .registerTypeHierarchyAdapter(DimensionType.class, new DimensionTypeAdapter())
/* 28 */     .registerTypeHierarchyAdapter(NamespacedKey.class, new NamespacedKeyAdapter())
/* 29 */     .registerTypeHierarchyAdapter(List.class, new ListAdapter())
/* 30 */     .registerTypeHierarchyAdapter(FrameType.class, new FrameTypeAdapter())
/* 31 */     .registerTypeHierarchyAdapter(Map.class, new MapAdapter())
/* 32 */     .registerTypeHierarchyAdapter(EffectType.class, new EffectTypeAdapter())
/* 33 */     .registerTypeHierarchyAdapter(KeyValue.class, new KeyValueAdapter())
/* 34 */     .registerTypeHierarchyAdapter(Biome.class, new BiomeAdapter())
/* 35 */     .registerTypeHierarchyAdapter(StructureType.class, new StructureTypeAdapter())
/* 36 */     .registerTypeHierarchyAdapter(GameMode.class, new GameModeAdapter())
/* 37 */     .create();
/*    */   
/*    */   public String serialize(Object object) {
/* 40 */     return GSON.toJson(object);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\serialization\ObjectSerializer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */