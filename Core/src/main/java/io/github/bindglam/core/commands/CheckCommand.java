package io.github.bindglam.core.commands;

import io.github.bindglam.core.Core;
import io.github.bindglam.core.advancements.BigCheckAdvancement;
import io.github.bindglam.core.advancements.CheckAdvancement;
import io.github.bindglam.core.advancements.LostHealthAdvancement;
import io.github.bindglam.core.utils.AdvancementUtil;
import io.github.bindglam.economy.EconomyManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.Objects;

public class CheckCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("check") || !(sender instanceof Player player) || args.length < 1) return false;

        int cost;
        try {
            cost = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            player.sendMessage("§c§l숫자가 아닙니다!");
            return false;
        }

        int count = 1;
        if(args.length == 2)
            count = Integer.parseInt(args[1]);

        if (EconomyManager.getAmount(player.getUniqueId()) < cost*count) {
            player.sendMessage("§c§l입력한 액수가 소지금보다 많습니다!");
            return false;
        }
        for(int i = 0; i < count; i++) {
            ItemStack checkItem = new ItemStack(Material.PAPER);
            ItemMeta meta = checkItem.getItemMeta();
            meta.setDisplayName("§f§l수표");
            meta.setLore(Collections.singletonList("§b금액: " + cost + "원"));
            checkItem.setItemMeta(meta);
            checkItem.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 10);
            player.getInventory().addItem(checkItem);
        }
        player.sendMessage("§a수표를 성공적으로 지급하였습니다.");
        EconomyManager.withdraw(player.getUniqueId(), cost*count);
        AdvancementUtil.awardAdvancement(player, new NamespacedKey(Core.INSTANCE, CheckAdvancement.ID), "complete");
        if(cost >= 50000000){
            AdvancementUtil.awardAdvancement(player, new NamespacedKey(Core.INSTANCE, BigCheckAdvancement.ID), "complete");
        }
        return true;
    }

    @EventHandler
    @Deprecated
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        if(itemStack == null) return;
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null) return;
        if(!meta.hasDisplayName()) return;
        if(!meta.getDisplayName().equals("§f§l수표")) return;

        int amount = Integer.parseInt(Objects.requireNonNull(meta.getLore()).get(0).split("§b금액: ")[1].replace("원", "")) * itemStack.getAmount();
        EconomyManager.deposit(player.getUniqueId(), amount);
        player.sendMessage("§a§l수표를 사용하였습니다!");
        itemStack.setAmount(0);
        player.setItemInHand(null);
        player.playSound(player.getLocation(), "entity.item.pickup", 100f, 2f);
    }
}
