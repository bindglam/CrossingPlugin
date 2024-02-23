package io.github.bindglam.core.items;

import dev.lone.itemsadder.api.CustomStack;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.utils.AdvItemCreator;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

@Getter
public abstract class PluginItem implements Listener {
    private final CustomStack customStack;

    public PluginItem(CustomStack customStack) {
        this.customStack = customStack;
    }

    public abstract void onInteract(PlayerInteractEvent event);
}
