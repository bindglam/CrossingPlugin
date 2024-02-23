/*    */ package net.roxeez.advancement.data;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
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
/*    */ public class DamageType
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("bypasses_armor")
/*    */   private boolean bypassesArmor;
/*    */   @Expose
/*    */   @SerializedName("bypasses_invulnerability")
/*    */   private boolean bypassesInvulnerability;
/*    */   @Expose
/*    */   @SerializedName("bypasses_magic")
/*    */   private boolean bypassesMagic;
/*    */   @Expose
/*    */   @SerializedName("direct_entity")
/*    */   private EntityData directEntity;
/*    */   @Expose
/*    */   @SerializedName("is_explosion")
/*    */   private boolean isExplosion;
/*    */   @Expose
/*    */   @SerializedName("is_fire")
/*    */   private boolean isFire;
/*    */   @Expose
/*    */   @SerializedName("is_projectile")
/*    */   private boolean isProjectile;
/*    */   @Expose
/*    */   @SerializedName("is_lightning")
/*    */   private boolean isLightning;
/*    */   @Expose
/*    */   @SerializedName("source_entity")
/*    */   private EntityData sourceEntity;
/*    */   
/*    */   public void setDirectEntity(Consumer<EntityData> consumer) {
/* 47 */     this.directEntity = new EntityData();
/* 48 */     consumer.accept(this.directEntity);
/*    */   }
/*    */   
/*    */   public void setSourceEntity(Consumer<EntityData> consumer) {
/* 52 */     this.sourceEntity = new EntityData();
/* 53 */     consumer.accept(this.sourceEntity);
/*    */   }
/*    */   
/*    */   public void setBypassesArmor(boolean bypassesArmor) {
/* 57 */     this.bypassesArmor = bypassesArmor;
/*    */   }
/*    */   
/*    */   public void setBypassesInvulnerability(boolean bypassesInvulnerability) {
/* 61 */     this.bypassesInvulnerability = bypassesInvulnerability;
/*    */   }
/*    */   
/*    */   public void setBypassesMagic(boolean bypassesMagic) {
/* 65 */     this.bypassesMagic = bypassesMagic;
/*    */   }
/*    */   
/*    */   public void setExplosion(boolean explosion) {
/* 69 */     this.isExplosion = explosion;
/*    */   }
/*    */   
/*    */   public void setFire(boolean fire) {
/* 73 */     this.isFire = fire;
/*    */   }
/*    */   
/*    */   public void setProjectile(boolean projectile) {
/* 77 */     this.isProjectile = projectile;
/*    */   }
/*    */   
/*    */   public void setLightning(boolean lightning) {
/* 81 */     this.isLightning = lightning;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\data\DamageType.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */