/*    */ package io.github.bindglam.core.commands;
/*    */ 
/*    */ import io.github.bindglam.core.Core;
/*    */ import io.github.bindglam.core.managers.UserShopManager;
/*    */ import io.github.bindglam.core.menu.shops.UserShopMenu;
/*    */ import io.github.bindglam.core.utils.AdvancementUtil;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import net.kyori.adventure.text.TextComponent;
/*    */ import net.kyori.adventure.text.format.TextColor;
/*    */ import net.kyori.adventure.text.format.TextDecoration;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class UserShopCommand implements CommandExecutor {
/*    */   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
/* 27 */     if (sender instanceof Player) {
/* 27 */       Player player = (Player)sender;
/* 27 */       if (command.getLabel().equalsIgnoreCase("usershop")) {
/* 29 */         if (args.length >= 2) {
/* 30 */           if (args[0].equalsIgnoreCase("등록")) {
/*    */             int cost;
/* 31 */             ItemStack itemStack = player.getInventory().getItemInMainHand();
/* 32 */             if (itemStack.getType() == Material.AIR) {
/* 33 */               player.sendMessage(((TextComponent)Component.text("손에 들고있는 아이템이 없습니다!").color(TextColor.color(255, 0, 0))));
/* 34 */               return false;
/*    */             } 
/*    */             try {
/* 38 */               cost = Integer.parseInt(args[1]);
/* 39 */             } catch (NumberFormatException e) {
/* 40 */               player.sendMessage(Component.text("숫자가 아닙니다!").color(TextColor.color(255, 0, 0)));
/* 41 */               return false;
/*    */             } 
/* 44 */             UserShopManager.data.put(UUID.randomUUID(), List.of(itemStack, Integer.valueOf(cost), player.getUniqueId()));
/* 45 */             player.sendMessage(((TextComponent)Component.text("성공적으로 아이템을 등록시켰습니다!").color(TextColor.color(0, 255, 0))));
/* 46 */             player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
/* 48 */             AdvancementUtil.awardAdvancement(player, new NamespacedKey((Plugin)Core.INSTANCE, "usershop"), "complete");
/* 49 */           } else if (args[0].equalsIgnoreCase("삭제")) {
/*    */             int id;
/*    */             try {
/* 52 */               id = Integer.parseInt(args[1]);
/* 53 */             } catch (NumberFormatException e) {
/*    */               NumberFormatException numberFormatException1;
/* 54 */               player.sendMessage(Component.text("숫자가 아닙니다!").color(TextColor.color(255, 0, 0)));
/* 55 */               return false;
/*    */             } 
/* 58 */             List<Integer> ids = UserShopManager.getItemIDList(player.getUniqueId());
/* 59 */             if (!ids.contains(Integer.valueOf(id))) {
/* 60 */               player.sendMessage(Component.text("당신이 등록한 아이템의 ID가 아닙니다!").color(TextColor.color(255, 0, 0)));
/* 61 */               return false;
/*    */             } 
/* 64 */             player.getInventory().addItem(new ItemStack[] {(ItemStack) UserShopManager.data.get(UserShopManager.getItemUUID(id)).get(0)});
/* 65 */             player.sendMessage(((TextComponent)Component.text("성공적으로 아이템을 삭제했습니다!").color(TextColor.color(255, 255, 0))));
/* 66 */             UserShopManager.data.remove(UserShopManager.getItemUUID(id));
/*    */           } 
/* 69 */         } else if (args.length == 1) {
/* 70 */           if (args[0].equalsIgnoreCase("목록")) {
/* 71 */             player.sendMessage(((TextComponent)Component.text("- 등록한 아이템 ID 목록 -").color(TextColor.color(255, 255, 255))));
/* 72 */             List<Integer> ids = UserShopManager.getItemIDList(player.getUniqueId());
/* 73 */             for (Integer id : ids) {
/* 74 */               ItemStack itemStack = (ItemStack) UserShopManager.data.get(UserShopManager.getItemUUID(id.intValue())).get(0);
/* 75 */               if (itemStack.hasDisplayName()) {
/* 76 */                 player.sendMessage(((TextComponent)Component.text(id.intValue()).color(TextColor.color(255, 255, 255)))
/* 77 */                     .append(Component.text(" - " + itemStack.getDisplayName()).color(TextColor.color(0, 255, 255))));
/*    */                 continue;
/*    */               } 
/* 79 */               player.sendMessage(((TextComponent)((TextComponent)Component.text(id.intValue()).color(TextColor.color(255, 255, 255)))
/* 80 */                   .append(Component.text(" - ").color(TextColor.color(0, 255, 255))))
/* 81 */                   .append(Component.translatable(itemStack.getType().translationKey()).color(TextColor.color(0, 255, 255))));
/*    */             } 
/*    */           } 
/*    */         } else {
/* 85 */           (new UserShopMenu()).open(player, 0);
/*    */         } 
/* 88 */         return true;
/*    */       } 
/*    */     } 
/*    */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\barsw\Downloads\Core.jar!\io\github\bindglam\core\commands\UserShopCommand.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */