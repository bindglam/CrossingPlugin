package io.github.bindglam.core.items;

import dev.lone.itemsadder.api.CustomStack;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.items.blocks.EnhanceBlock;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class PluginItemManager implements Listener {
    public static final List<PluginItem> pluginItems = List.of(new GoldenHeadItem("qlsdn516"), new EnhanceBlock(), new PvPLvResetItem(), new CheckItem(0.0),
            new CosmeticGetItem("", ""));

    public static void init() {
        Bukkit.broadcast(Component.text("커스텀 아이템 초기화중...").color(NamedTextColor.WHITE));
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

    public static PluginItem getPluginItem(ItemStack itemStack){
        for(PluginItem pi : pluginItems){
            if(pi.getItemStack() == null) continue;
            if(!pi.getItemStack().hasDisplayName()) continue;
            if(!itemStack.hasDisplayName()) continue;
            if(pi.getItemStack().getDisplayName().equals(itemStack.getDisplayName())) return pi;
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

        CustomStack stack = CustomStack.byItemStack(itemStack);
        PluginItem pluginItem;
        if(stack == null){
            pluginItem = getPluginItem(itemStack);
            if (pluginItem == null) return;
        } else {
            pluginItem = getPluginItem(stack);
            if (pluginItem == null) return;
            if (Objects.equals(stack.getDisplayName(), meta.getDisplayName())) return;
        }
        pluginItem.onInteract(event);
    }
}
