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
/*    */ public class EnhanceAdvancement implements AdvancementCreator {
/*    */   public static final String ID = "enhance";
/*    */   
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 18 */     Advancement advancement = new Advancement(context.getPlugin(), "enhance");
/*    */     
/* 20 */     advancement.setParent(new NamespacedKey(context.getPlugin(), "minecraftcross"));
/*    */     
/* 22 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("강화로 시작하는 이 세계 생활");
/*    */           
/*    */           x.setDescription("강화를 하세요");
/*    */           x.setIcon(Material.ANVIL);
/*    */         });
/* 28 */     advancement.addCriteria("complete", TriggerType.IMPOSSIBLE, trigger -> {
/*    */         
/*    */         });
/* 31 */     return advancement;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\EnhanceAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */