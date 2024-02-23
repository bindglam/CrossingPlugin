package io.github.bindglam.core.commands;

import io.github.bindglam.economy.EconomyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("money")) return false;

        player.sendMessage("");
        player.sendMessage("§8§l==================================");
        player.sendMessage("");
        player.sendMessage("§e 현재 소지금 §7: §6§l" + EconomyManager.getAmount(player.getUniqueId()) + "원");
        player.sendMessage("");
        player.sendMessage("§8§l==================================");
        player.sendMessage("");
        return true;
    }
}
