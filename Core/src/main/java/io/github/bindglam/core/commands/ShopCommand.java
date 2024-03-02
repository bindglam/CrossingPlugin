package io.github.bindglam.core.commands;

import io.github.bindglam.core.menu.shops.ShopMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("shop")) return false;

        if(args.length < 1) {
            new ShopMenu().open(player, 0);
        }
        return true;
    }
}
