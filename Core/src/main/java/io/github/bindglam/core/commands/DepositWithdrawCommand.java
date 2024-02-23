package io.github.bindglam.core.commands;

import io.github.bindglam.economy.EconomyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DepositWithdrawCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("deposit") && !command.getLabel().equalsIgnoreCase("withdraw") || args.length < 1 || !sender.isOp()) return false;

        if(command.getLabel().equalsIgnoreCase("deposit")){
            EconomyManager.deposit(player.getUniqueId(), Integer.parseInt(args[0]));
        } else {
            EconomyManager.withdraw(player.getUniqueId(), Integer.parseInt(args[0]));
        }
        return true;
    }
}
