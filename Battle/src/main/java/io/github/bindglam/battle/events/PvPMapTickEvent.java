package io.github.bindglam.battle.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PvPMapTickEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final String mapName;

    public PvPMapTickEvent(String mapName) {
        this.mapName = mapName;
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
