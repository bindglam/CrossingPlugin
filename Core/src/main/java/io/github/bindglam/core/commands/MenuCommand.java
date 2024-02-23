package io.github.bindglam.core.commands;

import io.github.bindglam.core.menu.core.CoreMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("menu")) return false;

        new CoreMenu(player).open(player, 0);
        return true;
    }
}
