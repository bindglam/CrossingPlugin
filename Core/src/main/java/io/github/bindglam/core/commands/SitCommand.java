package io.github.bindglam.core.commands;

import io.github.bindglam.core.Core;
import io.github.bindglam.core.utils.TeleportUtil;
import io.papermc.paper.event.entity.EntityToggleSitEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.HashMap;
import java.util.UUID;

public class SitCommand implements CommandExecutor, Listener {
    private static final HashMap<UUID, ArmorStand> sitStands = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("sit")) return false;
        if(sitStands.containsKey(player.getUniqueId())) return false;

        ArmorStand sitStand = player.getWorld().spawn(player.getLocation().subtract(0.0, 0.0, 0.0), ArmorStand.class);
        sitStand.setGravity(false);
        sitStand.setInvisible(true);
        sitStand.setMarker(true);
        sitStand.setInvulnerable(true);
        sitStand.addPassenger(player);
        sitStands.put(player.getUniqueId(), sitStand);
        return true;
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event){
        Player player = event.getPlayer();
        if(sitStands.containsKey(player.getUniqueId())){
            sitStands.get(player.getUniqueId()).remove();
            sitStands.remove(player.getUniqueId());
        }
    }
}
