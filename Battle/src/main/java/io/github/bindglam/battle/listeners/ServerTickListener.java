package io.github.bindglam.battle.listeners;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import io.github.bindglam.battle.BattlePlugin;
import io.github.bindglam.battle.MapManager;
import io.github.bindglam.battle.events.PvPMapEndEvent;
import io.github.bindglam.battle.events.PvPMapTickEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;

public class ServerTickListener implements Listener {
    @EventHandler
    public void onTick(ServerTickStartEvent event){
        for(String mapName : MapManager.maps.keySet()){
            MapManager.Map map = MapManager.maps.get(mapName);
            if(map.isPlaying){
                ArrayList<Player> alivePlayers = new ArrayList<>();
                for(Player joiner : map.players){
                    if(joiner.getGameMode() != GameMode.SPECTATOR) alivePlayers.add(joiner);
                }

                if(alivePlayers.size() == 1){
                    map.isPlaying = false;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(BattlePlugin.INSTANCE, () -> {
                        for (int i = map.players.size() - 1; i >= 0; i--) {
                            Player joiner = map.players.get(i);
                            joiner.setGameMode(GameMode.SURVIVAL);
                            joiner.teleportAsync(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
                            map.players.remove(joiner);
                        }
                    }, 5*20);

                    for(Player joiner : map.players){
                        joiner.showTitle(Title.title(
                                Component.text(alivePlayers.get(0).getName() + " 승리!"),
                                Component.empty(),
                                Title.Times.times(Duration.ofNanos(500), Duration.ofSeconds(5), Duration.ofNanos(500))
                        ));
                    }

                    new PvPMapEndEvent(alivePlayers.get(0), mapName).callEvent();
                } else if(alivePlayers.isEmpty()){
                    map.isPlaying = false;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(BattlePlugin.INSTANCE, () -> {
                        for (int i = map.players.size() - 1; i >= 0; i--) {
                            Player joiner = map.players.get(i);
                            joiner.setGameMode(GameMode.SURVIVAL);
                            joiner.teleportAsync(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
                            map.players.remove(joiner);
                        }
                    }, 5*20);

                    for(Player joiner : map.players){
                        joiner.showTitle(Title.title(
                                Component.text("무승부!"),
                                Component.empty(),
                                Title.Times.times(Duration.ofNanos(500), Duration.ofSeconds(5), Duration.ofNanos(500))
                        ));
                    }

                    new PvPMapEndEvent(null, mapName).callEvent();
                }

                new PvPMapTickEvent(mapName).callEvent();
            }
        }
    }
}
