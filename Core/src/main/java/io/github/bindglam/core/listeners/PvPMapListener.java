package io.github.bindglam.core.listeners;

import io.github.bindglam.battle.MapManager;
import io.github.bindglam.battle.events.PvPMapEndEvent;
import io.github.bindglam.battle.events.PvPMapTickEvent;
import io.github.bindglam.core.managers.PvPManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PvPMapListener implements Listener {
    @EventHandler
    public void onPvPMapEnd(PvPMapEndEvent event){
        MapManager.Map map = MapManager.maps.get(event.getMapName());
        Player winner = event.getWinner();

        if(!PvPManager.pvpRanks.containsKey(winner.getUniqueId()))
            PvPManager.pvpRanks.put(winner.getUniqueId(), 0);
        PvPManager.pvpRanks.put(winner.getUniqueId(), PvPManager.pvpRanks.get(winner.getUniqueId())+1);
    }
}
