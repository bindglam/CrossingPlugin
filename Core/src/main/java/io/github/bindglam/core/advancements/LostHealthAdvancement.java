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
/*    */ public class LostHealthAdvancement implements AdvancementCreator {
/*    */   public static final String ID = "lost_health";
/*    */   
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 18 */     Advancement advancement = new Advancement(context.getPlugin(), "lost_health");
/*    */     
/* 20 */     advancement.setParent(new NamespacedKey(context.getPlugin(), "minecraftcross"));
/*    */     
/* 22 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("사라진 나의 체력");
/*    */           
/*    */           x.setDescription("한번 죽어서 체력을 잃으세요");
/*    */           x.setIcon(Material.REDSTONE);
/*    */         });
/* 28 */     advancement.addCriteria("complete", TriggerType.IMPOSSIBLE, trigger -> {
/*    */         
/*    */         });
/* 31 */     return advancement;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\LostHealthAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */