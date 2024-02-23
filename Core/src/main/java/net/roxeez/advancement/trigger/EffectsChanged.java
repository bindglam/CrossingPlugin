/*    */ package net.roxeez.advancement.trigger;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Consumer;
/*    */ import net.roxeez.advancement.data.EffectData;
/*    */ import net.roxeez.advancement.data.EffectType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectsChanged
/*    */   implements Trigger
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("effects")
/*    */   private Map<EffectType, EffectData> effects;
/*    */   
/*    */   public void addEffect(EffectType effectType, Consumer<EffectData> consumer) {
/* 24 */     if (this.effects == null) {
/* 25 */       this.effects = new HashMap<>();
/*    */     }
/*    */     
/* 28 */     EffectData effect = new EffectData();
/* 29 */     consumer.accept(effect);
/*    */     
/* 31 */     this.effects.put(effectType, effect);
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\net\roxeez\advancement\trigger\EffectsChanged.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */