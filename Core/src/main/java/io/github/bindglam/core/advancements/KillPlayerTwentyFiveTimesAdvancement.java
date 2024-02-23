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
/*    */ public class KillPlayerTwentyFiveTimesAdvancement
/*    */   implements AdvancementCreator {
/*    */   @NotNull
/*    */   public Advancement create(@NotNull Context context) {
/* 18 */     Advancement advancement = new Advancement(context.getPlugin(), "kill_player_25");
/*    */     
/* 20 */     advancement.setParent(new NamespacedKey(context.getPlugin(), "spawn_boss"));
/*    */     
/* 22 */     advancement.setDisplay(x -> {
/*    */           x.setTitle("연쇄살인마?");
/*    */           
/*    */           x.setDescription("다른 플레이어를 25번 죽이세요");
/*    */           x.setIcon(Material.PLAYER_HEAD);
/*    */           x.setFrame(FrameType.CHALLENGE);
/*    */         });
/* 29 */     advancement.addCriteria("complete", TriggerType.IMPOSSIBLE, trigger -> {
/*    */         
/*    */         });
/* 32 */     return advancement;
/*    */   }
/*    */   
/*    */   public static final String ID = "kill_player_25";
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\advancements\KillPlayerTwentyFiveTimesAdvancement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */