package io.github.bindglam.core.menu.shops;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class OreShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Integer> items = new LinkedHashMap<>() {{
        put(Material.COAL, 2);
        put(Material.CHARCOAL, 3);
        put(Material.IRON_INGOT, 7);
        put(Material.GOLD_INGOT, 7);
        put(Material.LAPIS_LAZULI, 10);
        put(Material.REDSTONE, 10);
        put(Material.DIAMOND, 400);
        put(Material.EMERALD, 400);
        put(Material.QUARTZ, 5);
        put(Material.COPPER_INGOT, 5);
        put(Material.NETHERITE_SCRAP, 120);
        put(Material.NETHERITE_INGOT, 600);
        put(Material.AMETHYST_SHARD, 50);
        put(Material.AMETHYST_BLOCK, 70);
        put(Material.ECHO_SHARD, 120);
    }};

    public static LinkedHashMap<ItemStack, Integer> itemStacks = new LinkedHashMap<>() {{
        put(CustomStack.getInstance("minecraftcross:ruby").getItemStack(), 400);
    }};

    public OreShopMenu(){
        super("광물 상점");
    }

    @Override
    public LinkedHashMap<Material, Integer> getItems() {
        return items;
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getItemStacks() {
        return itemStacks;
    }
}
