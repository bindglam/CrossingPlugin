package io.github.bindglam.core.menu.shops;

import org.bukkit.Material;

import java.util.LinkedHashMap;

public class FoodShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Double> items = new LinkedHashMap<>() {{
        put(Material.COOKED_BEEF, 10.0);
        put(Material.COOKED_PORKCHOP, 10.0);
        put(Material.COOKED_CHICKEN, 8.0);
        put(Material.GOLDEN_CARROT, 100.0);
        put(Material.APPLE, 80.0);
        put(Material.GOLDEN_APPLE, 800.0);
        put(Material.ENCHANTED_GOLDEN_APPLE, 999999.0);
    }};

    public FoodShopMenu(){
        super("음식 상점");
    }

    @Override
    public LinkedHashMap<Material, Double> getItems() {
        return items;
    }
}
