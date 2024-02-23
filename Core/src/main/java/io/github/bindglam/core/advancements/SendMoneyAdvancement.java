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
/*    */ public class SendMoneyAdvancement
/*    */   implements AdvancementCreator {
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 17 */     Advancement advancement = new Advancement(context.getPlugin(), "send_money");
/*    */     
/* 19 */     advancement.setParent(new NamespacedKey(context.getPlugin(), "minecraftcross"));
/*    */     
/* 21 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("너에게 보내는 편지..대신에 돈...");
/*    */           
/*    */           x.setDescription("어느 플레이어에게 돈을 송금하세요");
/*    */           x.setIcon(Material.IRON_INGOT);
/*    */         });
/* 27 */     advancement.addCriteria("complete", TriggerType.IMPOSSIBLE, trigger -> {
/*    */         
/*    */         });
/* 30 */     return advancement;
/*    */   }
/*    */   
/*    */   public static final String ID = "send_money";
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\SendMoneyAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */