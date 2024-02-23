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
/*    */ public class UserShopAdvancement
/*    */   implements AdvancementCreator {
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 17 */     Advancement advancement = new Advancement(context.getPlugin(), "usershop");
/*    */     
/* 19 */     advancement.setParent(new NamespacedKey(context.getPlugin(), "minecraftcross"));
/*    */     
/* 21 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("장사로 시작하는 이 세계 생활");
/*    */           
/*    */           x.setDescription("유저 상점에 아이템을 등록하세요");
/*    */           x.setIcon(Material.APPLE);
/*    */         });
/* 27 */     advancement.addCriteria("complete", TriggerType.IMPOSSIBLE, trigger -> {
/*    */         
/*    */         });
/* 30 */     return advancement;
/*    */   }
/*    */   
/*    */   public static final String ID = "usershop";
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\UserShopAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */