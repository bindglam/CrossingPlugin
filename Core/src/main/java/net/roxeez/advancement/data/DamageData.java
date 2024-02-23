/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DamageData
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("blocked")
/*    */   private boolean blocked;
/*    */   @Expose
/*    */   @SerializedName("dealt")
/*    */   private Range<Double> dealt;
/*    */   @Expose
/*    */   @SerializedName("source")
/*    */   private EntityData source;
/*    */   @Expose
/*    */   @SerializedName("taken")
/*    */   private Range<Double> taken;
/*    */   @Expose
/*    */   @SerializedName("type")
/*    */   private DamageType type;
/*    */   
/*    */   public void setBlocked(boolean blocked) {
/* 31 */     this.blocked = blocked;
/*    */   }
/*    */   
/*    */   public void setDamageDealt(double minimum, double maximum) {
/* 35 */     this.dealt = new Range<>(Double.valueOf(minimum), Double.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setDamageDealt(double damage) {
/* 39 */     this.dealt = new Range<>(Double.valueOf(damage));
/*    */   }
/*    */   
/*    */   public void setSource(Consumer<EntityData> consumer) {
/* 43 */     this.source = new EntityData();
/* 44 */     consumer.accept(this.source);
/*    */   }
/*    */   
/*    */   public void setSource(EntityType entityType) {
/* 48 */     setSource(entity -> entity.setType(entityType));
/*    */   }
/*    */   
/*    */   public void setDamageTaken(double minimum, double maximum) {
/* 52 */     this.dealt = new Range<>(Double.valueOf(minimum), Double.valueOf(maximum));
/*    */   }
/*    */   
/*    */   public void setDamageTaken(double damage) {
/* 56 */     this.dealt = new Range<>(Double.valueOf(damage));
/*    */   }
/*    */   
/*    */   public void setType(Consumer<DamageType> consumer) {
/* 60 */     this.type = new DamageType();
/* 61 */     consumer.accept(this.type);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\DamageData.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */