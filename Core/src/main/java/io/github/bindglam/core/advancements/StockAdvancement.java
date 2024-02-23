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
/*    */ public class StockAdvancement
/*    */   implements AdvancementCreator {
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 17 */     Advancement advancement = new Advancement(context.getPlugin(), "stock");
/*    */     
/* 19 */     advancement.setParent(new NamespacedKey(context.getPlugin(), "minecraftcross"));
/*    */     
/* 21 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("나는 주식 부자가 될테야!");
/*    */           
/*    */           x.setDescription("주식을 구매하세요");
/*    */           x.setIcon(Material.IRON_INGOT);
/*    */         });
/* 27 */     advancement.addCriteria("complete", TriggerType.IMPOSSIBLE, trigger -> {
/*    */         
/*    */         });
/* 30 */     return advancement;
/*    */   }
/*    */   
/*    */   public static final String ID = "stock";
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\StockAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */