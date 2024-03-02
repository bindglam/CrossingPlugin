package io.github.bindglam.core.menu.shops;

import org.bukkit.Material;

import java.util.LinkedHashMap;

public class CropShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Double> items = new LinkedHashMap<>() {{
        put(Material.MELON, 50.0);
        put(Material.PUMPKIN, 55.0);
        put(Material.CARVED_PUMPKIN, 60.0);
        put(Material.JACK_O_LANTERN, 60.0);
        put(Material.HAY_BLOCK, 100.0);
        put(Material.WHEAT, 20.0);
        put(Material.CARROT, 20.0);
        put(Material.POTATO, 20.0);
        put(Material.BEETROOT, 25.0);
        put(Material.MELON_SEEDS, 80.0);
        put(Material.PUMPKIN_SEEDS, 80.0);
        put(Material.WHEAT_SEEDS, 50.0);
        put(Material.BEETROOT_SEEDS, 55.0);
        put(Material.BONE_MEAL, 50.0);
        put(Material.BONE, 450.0);
        put(Material.SUGAR_CANE, 50.0);

        put(Material.SUNFLOWER, 10.0);

        for(Material material : Material.values()){
            if(material.name().contains("SAPLING")){
                put(material, 50.0);
            } else if(material.name().contains("TULIP")){
                put(material, 50.0);
            }
        }
    }};

    public CropShopMenu(){
        super("장식및 농작물 상점");
    }

    @Override
    public LinkedHashMap<Material, Double> getItems() {
        return items;
    }
}
