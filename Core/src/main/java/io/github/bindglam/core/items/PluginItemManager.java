package io.github.bindglam.core.items;

import dev.lone.itemsadder.api.CustomStack;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.items.blocks.EnhanceBlock;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class PluginItemManager implements Listener {
    public static final List<PluginItem> pluginItems = List.of(new GoldenHeadItem(), new EnhanceBlock());

    public static void init() {
        for (PluginItem pluginItem : pluginItems) {
            if(pluginItem.getCustomStack() == null){ continue; }
            Bukkit.getPluginManager().registerEvents(pluginItem, Core.INSTANCE);
        }
    }

    public static PluginItem getPluginItem(CustomStack customStack){
        for(PluginItem pi : pluginItems){
            if(pi.getCustomStack() == null) continue;
            if(pi.getCustomStack().matchNamespacedID(customStack)) return pi;
        }
        return null;
    }

    @EventHandler
    @Deprecated
    public void onInteract(PlayerInteractEvent event){
        ItemStack itemStack = event.getItem();
        if(itemStack == null) return;
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null) return;
        if(!meta.hasDisplayName()) return;

        if(meta.getDisplayName().contains("황금머리")){
            new GoldenHeadItem().onInteract(event);
            return;
        }

        CustomStack stack = CustomStack.byItemStack(itemStack);
        if(stack == null) return;
        PluginItem pluginItem = getPluginItem(stack);
        if(pluginItem == null) return;
        if(Objects.equals(stack.getDisplayName(), meta.getDisplayName())) return;

        pluginItem.onInteract(event);
    }
}
