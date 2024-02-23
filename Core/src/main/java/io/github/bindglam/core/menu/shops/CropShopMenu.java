package io.github.bindglam.core.menu.shops;

import org.bukkit.Material;

import java.util.LinkedHashMap;

public class CropShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Integer> items = new LinkedHashMap<>() {{
        put(Material.MELON, 50);
        put(Material.PUMPKIN, 55);
        put(Material.CARVED_PUMPKIN, 60);
        put(Material.JACK_O_LANTERN, 60);
        put(Material.HAY_BLOCK, 100);
        put(Material.WHEAT, 20);
        put(Material.CARROT, 20);
        put(Material.POTATO, 20);
        put(Material.BEETROOT, 25);
        put(Material.MELON_SEEDS, 80);
        put(Material.PUMPKIN_SEEDS, 80);
        put(Material.WHEAT_SEEDS, 50);
        put(Material.BEETROOT_SEEDS, 55);
        put(Material.BONE_MEAL, 50);
        put(Material.BONE, 450);
        put(Material.SUGAR_CANE, 50);

        put(Material.RED_TULIP, 10);
        put(Material.ORANGE_TULIP, 10);
        put(Material.PINK_TULIP, 10);
        put(Material.WHITE_TULIP, 10);
        put(Material.SUNFLOWER, 10);
    }};

    public CropShopMenu(){
        super("장식및 농작물 상점");
    }

    @Override
    public LinkedHashMap<Material, Integer> getItems() {
        return items;
    }
}
