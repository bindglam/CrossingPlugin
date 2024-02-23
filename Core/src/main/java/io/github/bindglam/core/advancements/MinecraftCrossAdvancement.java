/*    */ package io.github.bindglam.core.advancements;
/*    */ 
/*    */ import net.roxeez.advancement.Advancement;
/*    */ import net.roxeez.advancement.AdvancementCreator;
/*    */ import net.roxeez.advancement.Context;
/*    */ import net.roxeez.advancement.display.BackgroundType;
/*    */ import net.roxeez.advancement.display.Display;
/*    */ import net.roxeez.advancement.trigger.Tick;
/*    */ import net.roxeez.advancement.trigger.TriggerType;
/*    */ import org.bukkit.Material;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class MinecraftCrossAdvancement
/*    */   implements AdvancementCreator {
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 17 */     Advancement advancement = new Advancement(context.getPlugin(), "minecraftcross");
/*    */     
/* 19 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("마크의 숲");
/*    */           
/*    */           x.setDescription("새로운 야생");
/*    */           x.setBackground(BackgroundType.STONE);
/*    */           x.setIcon(Material.GRASS_BLOCK);
/*    */         });
/* 26 */     advancement.addCriteria("auto_complete", TriggerType.TICK, trigger -> {
/*    */         
/*    */         });
/* 29 */     return advancement;
/*    */   }
/*    */   
/*    */   public static final String ID = "minecraftcross";
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\MinecraftCrossAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */