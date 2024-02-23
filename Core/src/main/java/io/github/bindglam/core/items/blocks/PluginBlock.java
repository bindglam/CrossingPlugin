package io.github.bindglam.core.items.blocks;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import io.github.bindglam.core.items.PluginItem;
import io.github.bindglam.core.items.PluginItemManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public abstract class PluginBlock extends PluginItem {
    public PluginBlock(CustomStack customStack) {
        super(customStack);
    }

    public abstract void onUse(PlayerInteractEvent event);

    @Override
    public void onInteract(PlayerInteractEvent event) {
        event.setCancelled(false);
    }

    @EventHandler
    public void onInteractBlock(PlayerInteractEvent event){
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(event.getClickedBlock());
        if(customBlock == null) return;
        if(PluginItemManager.getPluginItem(customBlock) == null) return;
        event.setCancelled(true);

        onUse(event);
    }
}
