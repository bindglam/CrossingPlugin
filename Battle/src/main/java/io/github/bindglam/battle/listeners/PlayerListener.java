package io.github.bindglam.battle.listeners;

import io.github.bindglam.battle.MapManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class PlayerListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getPlayer();

        for(String mapName : MapManager.maps.keySet()){
            MapManager.Map map = MapManager.maps.get(mapName);
            if(map.isPlaying){
                for(Player joiner : map.players){
                    if(joiner.getUniqueId() == player.getUniqueId()){
                        event.setCancelled(true);
                        event.setKeepInventory(true);
                        player.setGameMode(GameMode.SPECTATOR);
                        player.teleportAsync(new Location(player.getWorld(), 0, 0, 0));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        for(String mapName : MapManager.maps.keySet()){
            MapManager.Map map = MapManager.maps.get(mapName);
            if(map.isPlaying){
                for (int i = map.players.size() - 1; i >= 0; i--) {
                    Player joiner = map.players.get(i);
                    if(joiner.getUniqueId() == player.getUniqueId()){
                        player.setGameMode(GameMode.SURVIVAL);
                        player.teleportOffline(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
                        map.players.remove(joiner);
                    }
                }
            }
        }
    }
}
