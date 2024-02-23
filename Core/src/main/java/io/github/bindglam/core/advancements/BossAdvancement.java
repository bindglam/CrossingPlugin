/*    */ package io.github.bindglam.core.advancements;
/*    */ 
/*    */ import net.roxeez.advancement.Advancement;
/*    */ import net.roxeez.advancement.AdvancementCreator;
/*    */ import net.roxeez.advancement.Context;
/*    */ import net.roxeez.advancement.display.BackgroundType;
/*    */ import net.roxeez.advancement.display.Display;
/*    */ import net.roxeez.advancement.trigger.Impossible;
/*    */ import net.roxeez.advancement.trigger.Tick;
/*    */ import net.roxeez.advancement.trigger.TriggerType;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BossAdvancement
/*    */   implements AdvancementCreator, Listener
/*    */ {
/*    */   public static final String ID = "boss_fighter";
/*    */   
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 33 */     Advancement advancement = new Advancement(context.getPlugin(), "boss_fighter");
/*    */     
/* 35 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("보스 도장 깨기");
/*    */           
/*    */           x.setDescription("강인한 나를 만드는 과정");
/*    */           x.setBackground(BackgroundType.BEDROCK);
/*    */           x.setIcon(Material.NETHERITE_AXE);
/*    */         });
/* 42 */     advancement.addCriteria("auto_complete", TriggerType.TICK, trigger -> {
/*    */         
/*    */         });
/* 45 */     return advancement;
/*    */   }
/*    */   
/*    */   public static class AncientFighter
/*    */     implements AdvancementCreator {
/*    */     public static final String ID = "ancient_fighter";
/*    */     
/*    */     @NotNull
/*    */     public Advancement create(@NotNull Context context) {
/* 54 */       Advancement advancement = new Advancement(context.getPlugin(), "ancient_fighter");
/*    */       
/* 56 */       advancement.setParent(new NamespacedKey(context.getPlugin(), "boss_fighter"));
/*    */       
/* 58 */       advancement.setDisplay(x -> {
/*    */             x.setTitle("고대 격투가 격파!");
/*    */             
/*    */             x.setDescription("고대 격투가를 쓰러뜨리세요");
/*    */             x.setIcon(Material.WOODEN_SWORD);
/*    */           });
/* 64 */       advancement.addCriteria("af", TriggerType.IMPOSSIBLE, trigger -> {
/*    */           
/*    */           });
/* 67 */       return advancement;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Hydra
/*    */     implements AdvancementCreator {
/*    */     public static final String ID = "hydra";
/*    */     
/*    */     @NotNull
/*    */     public Advancement create(@NotNull Context context) {
/* 77 */       Advancement advancement = new Advancement(context.getPlugin(), "hydra");
/*    */       
/* 79 */       advancement.setParent(new NamespacedKey(context.getPlugin(), "boss_fighter"));
/*    */       
/* 81 */       advancement.setDisplay(x -> {
/*    */             x.setTitle("히드라의 정수 추출");
/*    */             
/*    */             x.setDescription("히드라를 쓰러뜨리세요");
/*    */             x.setIcon(Material.STONE_SWORD);
/*    */           });
/* 87 */       advancement.addCriteria("hd", TriggerType.IMPOSSIBLE, trigger -> {
/*    */           
/*    */           });
/* 90 */       return advancement;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\BossAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */