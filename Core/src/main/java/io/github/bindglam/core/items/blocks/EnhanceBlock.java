package io.github.bindglam.core.items.blocks;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.menu.blocks.EnhanceMenu;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ShapedRecipe;

public class EnhanceBlock extends PluginBlock {
    public EnhanceBlock() {
        super(CustomBlock.getInstance("minecraftcross:enhance_block"));
    }

    @Override
    public void onUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        player.getInventory().getItemInMainHand();
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR)
            return;
        if (!player.getInventory().getItemInMainHand().getType().name().contains("SWORD") && !player.getInventory().getItemInMainHand().getType().name().contains("PICKAXE") &&
                !player.getInventory().getItemInMainHand().getType().name().contains("SHOVEL") &&
                !player.getInventory().getItemInMainHand().getType().name().contains("AXE") &&
                !player.getInventory().getItemInMainHand().getType().isArmor())
            if (player.getInventory().getItemInMainHand().hasDisplayName()) {
                if (!EnhanceMenu.additionalEnhanceAbleArmors.contains(player.getInventory().getItemInMainHand().getDisplayName()) &&
                        !EnhanceMenu.additionalEnhanceAbleWeapons.contains(player.getInventory().getItemInMainHand().getDisplayName()))
                    return;
            } else {
                return;
            }
        (new EnhanceMenu(player)).open(player, 0);
    }
}
