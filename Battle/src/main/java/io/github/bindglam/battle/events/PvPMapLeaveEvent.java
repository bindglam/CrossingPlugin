package io.github.bindglam.battle.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PvPMapLeaveEvent extends PlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public PvPMapLeaveEvent(@NotNull Player who) {
        super(who);
    }

    public static HandlerList getHandlerList(){
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
