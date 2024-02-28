package io.github.bindglam.core.commands;

import io.github.bindglam.core.managers.DonatePointManager;
import io.github.bindglam.core.menu.core.StatsMenu;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DonatePointCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("donatepoint") || !sender.isOp() || args.length < 2) return false;

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
        DonatePointManager.donatePoints.put(offlinePlayer.getUniqueId(), DonatePointManager.donatePoints.get(offlinePlayer.getUniqueId())+Integer.parseInt(args[1]));
        return true;
    }
}
