package io.github.bindglam.core.commands;

import io.github.bindglam.core.managers.HomeManager;
import io.github.bindglam.core.utils.TeleportUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) return false;

        if(command.getLabel().equalsIgnoreCase("home")){
            if(!HomeManager.homes.containsKey(player.getUniqueId())){
                player.sendMessage(Component.text("가지고 있는 홈이 없습니다!").color(TextColor.color(255, 0, 0)));
                return false;
            }
            TeleportUtil.teleportAfterCooldown(player, HomeManager.homes.get(player.getUniqueId()), 5, false, true);
        } else if(command.getLabel().equalsIgnoreCase("sethome")){
            HomeManager.homes.put(player.getUniqueId(), player.getLocation());
            player.sendMessage(Component.text("홈을 만들었습니다!").color(TextColor.color(0, 255, 0)));
        }
        return true;
    }
}
