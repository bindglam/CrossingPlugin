/*    */ package io.github.bindglam.core.advancements;
/*    */ 
/*    */ import net.roxeez.advancement.Advancement;
/*    */ import net.roxeez.advancement.AdvancementCreator;
/*    */ import net.roxeez.advancement.Context;
/*    */ import net.roxeez.advancement.display.Display;
/*    */ import net.roxeez.advancement.trigger.Impossible;
/*    */ import net.roxeez.advancement.trigger.TriggerType;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class RecoveryHealthAdvancement
/*    */   implements AdvancementCreator {
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 17 */     Advancement advancement = new Advancement(context.getPlugin(), "recovery_health");
/*    */     
/* 19 */     advancement.setParent(new NamespacedKey(context.getPlugin(), "lost_health"));
/*    */     
/* 21 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("돌아온 나의 체력");
/*    */           
/*    */           x.setDescription("한번 죽어서 잃은 체력을 여관에서 복구하세요");
/*    */           x.setIcon(Material.REDSTONE_BLOCK);
/*    */         });
/* 27 */     advancement.addCriteria("complete", TriggerType.IMPOSSIBLE, trigger -> {
/*    */         
/*    */         });
/* 30 */     return advancement;
/*    */   }
/*    */   
/*    */   public static final String ID = "recovery_health";
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\RecoveryHealthAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */