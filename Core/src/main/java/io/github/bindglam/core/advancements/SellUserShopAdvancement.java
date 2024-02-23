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
/*    */ public class SellUserShopAdvancement
/*    */   implements AdvancementCreator {
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 18 */     Advancement advancement = new Advancement(context.getPlugin(), "sell_usershop");
/*    */     
/* 20 */     advancement.setParent(new NamespacedKey(context.getPlugin(), "usershop"));
/*    */     
/* 22 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("장사로 부자되기의 첫 발걸음");
/*    */           
/*    */           x.setDescription("유저 상점에 아이템을 등록하고, 판매하세요");
/*    */           x.setIcon(Material.GOLDEN_APPLE);
/*    */           x.setFrame(FrameType.CHALLENGE);
/*    */         });
/* 29 */     advancement.addCriteria("complete", TriggerType.IMPOSSIBLE, trigger -> {
/*    */         
/*    */         });
/* 32 */     return advancement;
/*    */   }
/*    */   
/*    */   public static final String ID = "sell_usershop";
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\SellUserShopAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */