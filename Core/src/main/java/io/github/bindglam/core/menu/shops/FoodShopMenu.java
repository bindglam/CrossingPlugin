package io.github.bindglam.core.menu.shops;

import org.bukkit.Material;

import java.util.LinkedHashMap;

public class FoodShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Integer> items = new LinkedHashMap<>() {{
        put(Material.COOKED_BEEF, 10);
        put(Material.COOKED_PORKCHOP, 10);
        put(Material.COOKED_CHICKEN, 8);
        put(Material.GOLDEN_CARROT, 100);
        put(Material.APPLE, 80);
        put(Material.GOLDEN_APPLE, 800);
        put(Material.ENCHANTED_GOLDEN_APPLE, 999999);
    }};

    public FoodShopMenu(){
        super("음식 상점");
    }

    @Override
    public LinkedHashMap<Material, Integer> getItems() {
        return items;
    }
}
