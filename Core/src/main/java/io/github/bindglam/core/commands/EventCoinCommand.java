package io.github.bindglam.core.commands;

import io.github.bindglam.core.Core;
import io.github.bindglam.core.advancements.BigCheckAdvancement;
import io.github.bindglam.core.advancements.CheckAdvancement;
import io.github.bindglam.core.managers.EventCoinManager;
import io.github.bindglam.core.utils.AdvancementUtil;
import io.github.bindglam.economy.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
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

public class EventCoinCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("eventcoin") || args.length < 2) return false;

        int cost;
        try {
            cost = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            sender.sendMessage("§c§l숫자가 아닙니다!");
            return false;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);

        sender.sendMessage("§a이벤트 코인을 성공적으로 지급하였습니다.");
        EventCoinManager.eventCoins.put(offlinePlayer.getUniqueId(), EventCoinManager.eventCoins.get(offlinePlayer.getUniqueId()) + cost);
        return true;
    }
}
