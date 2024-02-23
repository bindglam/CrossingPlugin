/*    */ package io.github.bindglam.core.advancements;
/*    */ 
/*    */ import net.roxeez.advancement.Advancement;
/*    */ import net.roxeez.advancement.AdvancementCreator;
/*    */ import net.roxeez.advancement.Context;
/*    */ import net.roxeez.advancement.display.Display;
/*    */ import net.roxeez.advancement.display.FrameType;
/*    */ import net.roxeez.advancement.trigger.Impossible;
/*    */ import net.roxeez.advancement.trigger.TriggerType;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class TenEnhanceAdvancement
/*    */   implements AdvancementCreator {
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 18 */     Advancement advancement = new Advancement(context.getPlugin(), "ten_enhance");
/*    */     
/* 20 */     advancement.setParent(new NamespacedKey(context.getPlugin(), "enhance"));
/*    */     
/* 22 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("헉! 10강이라니!");
/*    */           
/*    */           x.setDescription("강화로 10강을 찍으세요");
/*    */           x.setIcon(Material.TORCH);
/*    */           x.setFrame(FrameType.CHALLENGE);
/*    */         });
/* 29 */     advancement.addCriteria("complete", TriggerType.IMPOSSIBLE, trigger -> {
/*    */         
/*    */         });
/* 32 */     return advancement;
/*    */   }
/*    */   
/*    */   public static final String ID = "ten_enhance";
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\TenEnhanceAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */