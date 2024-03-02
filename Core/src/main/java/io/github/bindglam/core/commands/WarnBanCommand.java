package io.github.bindglam.core.commands;

import io.github.bindglam.core.Core;
import io.github.bindglam.core.managers.BanManager;
import io.github.bindglam.core.utils.TeleportUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class WarnBanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("warn") && !command.getLabel().equalsIgnoreCase("ban") || args.length < 2) return false;

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
        if(command.getLabel().equalsIgnoreCase("warn")){
            BanManager.warnPlayer(offlinePlayer, args[1]);
            sender.sendMessage("성공적으로 경고를 주었습니다. ( 1회 )");
        } else if(command.getLabel().equalsIgnoreCase("ban-player")){
            BanManager.banPlayer(offlinePlayer, args[1]);
            sender.sendMessage("성공적으로 밴를 주었습니다.");
        }
        return true;
    }
}
