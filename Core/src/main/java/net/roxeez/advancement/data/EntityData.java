/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Consumer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityData
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("type")
/*    */   private EntityType type;
/*    */   @Expose
/*    */   @SerializedName("distance")
/*    */   private DistanceData distance;
/*    */   @Expose
/*    */   @SerializedName("effects")
/*    */   private Map<EffectType, EffectData> effects;
/*    */   @Expose
/*    */   @SerializedName("equipments")
/*    */   private EquipmentData equipments;
/*    */   @Expose
/*    */   @SerializedName("flags")
/*    */   private FlagsData flags;
/*    */   @Expose
/*    */   @SerializedName("location")
/*    */   private LocationData location;
/*    */   @Expose
/*    */   @SerializedName("player")
/*    */   private PlayerData player;
/*    */   @Expose
/*    */   @SerializedName("targeted_entity")
/*    */   private EntityData targetedEntity;
/*    */   @Expose
/*    */   @SerializedName("vehicle")
/*    */   private EntityData vehicle;
/*    */   
/*    */   public void setType(EntityType type) {
/* 50 */     this.type = type;
/*    */   }
/*    */   
/*    */   public void setDistance(Consumer<DistanceData> consumer) {
/* 54 */     this.distance = new DistanceData();
/* 55 */     consumer.accept(this.distance);
/*    */   }
/*    */   
/*    */   public void addEffect(EffectType effectType, Consumer<EffectData> consumer) {
/* 59 */     if (this.effects == null) {
/* 60 */       this.effects = new HashMap<>();
/*    */     }
/*    */     
/* 63 */     EffectData effectData = new EffectData();
/* 64 */     consumer.accept(effectData);
/* 65 */     this.effects.put(effectType, effectData);
/*    */   }
/*    */   
/*    */   public void setEquipments(Consumer<EquipmentData> consumer) {
/* 69 */     this.equipments = new EquipmentData();
/* 70 */     consumer.accept(this.equipments);
/*    */   }
/*    */   
/*    */   public void setFlags(Consumer<FlagsData> consumer) {
/* 74 */     this.flags = new FlagsData();
/* 75 */     consumer.accept(this.flags);
/*    */   }
/*    */   
/*    */   public void setLocation(Consumer<LocationData> consumer) {
/* 79 */     this.location = new LocationData();
/* 80 */     consumer.accept(this.location);
/*    */   }
/*    */   
/*    */   public void setTargetedEntity(Consumer<EntityData> consumer) {
/* 84 */     this.targetedEntity = new EntityData();
/* 85 */     consumer.accept(this.targetedEntity);
/*    */   }
/*    */   
/*    */   public void setVehicle(Consumer<EntityData> consumer) {
/* 89 */     this.vehicle = new EntityData();
/* 90 */     consumer.accept(this.vehicle);
/*    */   }
/*    */   
/*    */   public void setPlayer(Consumer<PlayerData> consumer) {
/* 94 */     this.player = new PlayerData();
/* 95 */     consumer.accept(this.player);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\EntityData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */