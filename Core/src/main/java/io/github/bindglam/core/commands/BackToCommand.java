package io.github.bindglam.core.commands;

import io.github.bindglam.core.utils.TeleportUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BackToCommand implements CommandExecutor {
    public static final HashMap<String, Location> backPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("backto")) return false;

        if(backPlayers.containsKey(player.getName())) {
            TeleportUtil.teleportAfterCooldown(player, backPlayers.get(player.getName()), 5, false);
            backPlayers.remove(player.getName());
        } else {
            player.sendMessage("§c되돌아갈 위치가 없습니다.");
        }
        return true;
    }
}
