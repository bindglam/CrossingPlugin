/*    */ package net.roxeez.advancement.trigger;
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.EntityData;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ChanneledLightning implements Trigger {
/*    */   @Expose
/*    */   @SerializedName("victims")
/* 15 */   private final List<EntityData> victims = new ArrayList<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addVictim(@NotNull EntityType entityType) {
/* 25 */     Preconditions.checkNotNull(entityType);
/* 26 */     addVictim(entity -> entity.setType(entityType));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addVictim(@NotNull Consumer<EntityData> consumer) {
/* 35 */     Preconditions.checkNotNull(consumer);
/* 36 */     EntityData entity = new EntityData();
/* 37 */     consumer.accept(entity);
/*    */     
/* 39 */     this.victims.add(entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\ChanneledLightning.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */