package io.github.bindglam.core.commands;

import io.github.bindglam.core.managers.BanManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnnouncementCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("announcement") || args.length < 1) return false;

        Bukkit.broadcast(Component.text(""));
        Bukkit.broadcast(Component.text(""));
        Bukkit.broadcast(Component.text("\uD83D\uDD0A 공지 ").color(NamedTextColor.AQUA)
                .append(Component.text("| ").color(NamedTextColor.WHITE))
                .append(Component.text(args[0].replace("_", " ")).color(NamedTextColor.YELLOW)));
        Bukkit.broadcast(Component.text(""));
        Bukkit.broadcast(Component.text(""));
        for(Player player : Bukkit.getOnlinePlayers()){
            player.playSound(player, Sound.EVENT_RAID_HORN, 100f, 1f);
        }
        return true;
    }
}
