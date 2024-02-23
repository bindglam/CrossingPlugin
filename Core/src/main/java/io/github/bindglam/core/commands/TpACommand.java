package io.github.bindglam.core.commands;

import io.github.bindglam.core.utils.TeleportUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class TpACommand implements CommandExecutor {
    private static final HashMap<String, String> tpaPlayers = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player) || (!command.getLabel().equalsIgnoreCase("tpa") && !command.getLabel().equalsIgnoreCase("tpaccept")) || args.length < 1) return false;

        Player sendPlayer = Bukkit.getPlayer(args[0]);
        if(sendPlayer == null){
            player.sendMessage(Component.text("[ TPA ] ").color(TextColor.color(255, 255, 255)).decorate(TextDecoration.BOLD).append(Component.text(args[0] + "님은 오프라인입니다!").color(TextColor.color(255, 0, 0))));
            return false;
        }

        if(command.getLabel().equalsIgnoreCase("tpa") && !sendPlayer.getName().equals(player.getName())){
            tpaPlayers.put(player.getName(), sendPlayer.getName());
            player.sendMessage(Component.text("[ TPA ] ").color(TextColor.color(255, 255, 255)).decorate(TextDecoration.BOLD)
                    .append(Component.text(args[0] + "님에게 TpA를 보냈습니다!").color(TextColor.color(0, 255, 0))));
            sendPlayer.sendMessage(Component.text("[ TPA ] ").color(TextColor.color(255, 255, 255)).decorate(TextDecoration.BOLD)
                    .append(Component.text(player.getName() + "님께서 TpA를 보냈습니다!").color(TextColor.color(204, 153, 255)).append(
                            Component.text(" [ 수락하기 ]").color(TextColor.color(0, 255, 0)).decorate(TextDecoration.BOLD).clickEvent(ClickEvent.runCommand("/tpaccept " + player.getName())))));
        } else if(command.getLabel().equalsIgnoreCase("tpaccept") && tpaPlayers.containsValue(player.getName())){
            TeleportUtil.teleportAfterCooldown(sendPlayer, player.getLocation(), 5, false, true);
            player.sendMessage(Component.text("[ TPA ] ").color(TextColor.color(255, 255, 255)).decorate(TextDecoration.BOLD)
                    .append(Component.text(args[0] + "님의 Tpa를 수락했습니다!").color(TextColor.color(0, 255, 0))));
            tpaPlayers.remove(sendPlayer.getName());
        }
        return false;
    }
}
