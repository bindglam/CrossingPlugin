package io.github.bindglam.core.commands;

import io.github.bindglam.core.managers.BankManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearBankCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("clearbank") || !sender.isOp()) return false;

        BankManager.hasBankPlayers.remove(player.getName());
        return true;
    }
}
