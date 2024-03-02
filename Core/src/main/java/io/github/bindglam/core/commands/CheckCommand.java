package io.github.bindglam.core.commands;

import io.github.bindglam.core.Core;
import io.github.bindglam.core.advancements.BigCheckAdvancement;
import io.github.bindglam.core.advancements.CheckAdvancement;
import io.github.bindglam.core.advancements.LostHealthAdvancement;
import io.github.bindglam.core.items.CheckItem;
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

public class CheckCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("check") || !(sender instanceof Player player) || args.length < 1) return false;

        double cost;
        try {
            cost = Double.parseDouble(args[0]);
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
            player.getInventory().addItem(new CheckItem(cost).getItemStack());
        }
        player.sendMessage("§a수표를 성공적으로 지급하였습니다.");
        EconomyManager.withdraw(player.getUniqueId(), cost*count);
        AdvancementUtil.awardAdvancement(player, new NamespacedKey(Core.INSTANCE, CheckAdvancement.ID), "complete");
        if(cost >= 50000000){
            AdvancementUtil.awardAdvancement(player, new NamespacedKey(Core.INSTANCE, BigCheckAdvancement.ID), "complete");
        }
        return true;
    }
}
