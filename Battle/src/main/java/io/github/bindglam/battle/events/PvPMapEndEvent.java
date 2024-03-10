package io.github.bindglam.battle.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PvPMapEndEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final String mapName;
    private final Player winner;

    public PvPMapEndEvent(Player winner, String mapName) {
        this.winner = winner;
        this.mapName = mapName;
    }

    public Player getWinner(){
        return winner;
    }

    public String getMapName(){
        return mapName;
    }

    public static HandlerList getHandlerList(){
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
