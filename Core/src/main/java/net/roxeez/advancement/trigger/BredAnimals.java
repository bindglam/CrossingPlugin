/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BredAnimals
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("child")
/*    */   private EntityData child;
/*    */   @Expose
/*    */   @SerializedName("parent")
/*    */   private EntityData parent;
/*    */   @Expose
/*    */   @SerializedName("partner")
/*    */   private EntityData partner;
/*    */   
/*    */   public void setChild(@NotNull Consumer<EntityData> consumer) {
/* 31 */     Preconditions.checkNotNull(consumer);
/* 32 */     this.child = new EntityData();
/* 33 */     consumer.accept(this.child);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setChild(@NotNull EntityType entityType) {
/* 43 */     Preconditions.checkNotNull(entityType);
/* 44 */     setChild(entity -> entity.setType(entityType));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setParent(@NotNull EntityType entityType) {
/* 54 */     Preconditions.checkNotNull(entityType);
/* 55 */     setParent(entity -> entity.setType(entityType));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPartner(@NotNull EntityType entityType) {
/* 65 */     Preconditions.checkNotNull(entityType);
/* 66 */     setPartner(entity -> entity.setType(entityType));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setParent(@NotNull Consumer<EntityData> consumer) {
/* 75 */     Preconditions.checkNotNull(consumer);
/* 76 */     this.parent = new EntityData();
/* 77 */     consumer.accept(this.parent);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPartner(@NotNull Consumer<EntityData> consumer) {
/* 86 */     Preconditions.checkNotNull(consumer);
/* 87 */     this.partner = new EntityData();
/* 88 */     consumer.accept(this.partner);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\BredAnimals.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */