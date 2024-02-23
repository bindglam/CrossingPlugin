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
/*    */ public class BigCheckAdvancement
/*    */   implements AdvancementCreator {
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 18 */     Advancement advancement = new Advancement(context.getPlugin(), "big_check");
/*    */     
/* 20 */     advancement.setParent(new NamespacedKey(context.getPlugin(), "check"));
/*    */     
/* 22 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("부자가 된 당신");
/*    */           
/*    */           x.setDescription("50000000원 이상의 수표를 제작하세요");
/*    */           x.setIcon(Material.BOOK);
/*    */           x.setFrame(FrameType.CHALLENGE);
/*    */         });
/* 29 */     advancement.addCriteria("complete", TriggerType.IMPOSSIBLE, trigger -> {
/*    */         
/*    */         });
/* 32 */     return advancement;
/*    */   }
/*    */   
/*    */   public static final String ID = "big_check";
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\BigCheckAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */