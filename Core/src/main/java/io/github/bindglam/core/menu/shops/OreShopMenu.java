package io.github.bindglam.core.menu.shops;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class OreShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Double> items = new LinkedHashMap<>() {{
        put(Material.COAL, 5.0);
        put(Material.CHARCOAL, 5.0);
        put(Material.IRON_INGOT, 14.0);
        put(Material.GOLD_INGOT, 14.0);
        put(Material.LAPIS_LAZULI, 20.0);
        put(Material.REDSTONE, 22.0);
        put(Material.DIAMOND, 100.0);
        put(Material.EMERALD, 200.0);
        put(Material.QUARTZ, 5.0);
        put(Material.COPPER_INGOT, 5.0);
        put(Material.NETHERITE_SCRAP, 120.0);
        put(Material.NETHERITE_INGOT, 600.0);
        put(Material.AMETHYST_SHARD, 50.0);
        put(Material.AMETHYST_BLOCK, 70.0);
        put(Material.ECHO_SHARD, 120.0);
        put(Material.FLINT, 2.0);
    }};

    public static LinkedHashMap<ItemStack, Double> itemStacks = new LinkedHashMap<>() {{
        put(CustomStack.getInstance("minecraftcross:ruby").getItemStack(), 400.0);
    }};

    public OreShopMenu(){
        super("광물 상점");
    }

    @Override
    public LinkedHashMap<Material, Double> getItems() {
        return items;
    }

    @Override
    public LinkedHashMap<ItemStack, Double> getItemStacks() {
        return itemStacks;
    }
}
