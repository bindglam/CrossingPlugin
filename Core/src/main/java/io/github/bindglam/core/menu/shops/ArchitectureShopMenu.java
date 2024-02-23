package io.github.bindglam.core.menu.shops;

import net.minecraft.world.item.CreativeModeTab;
import org.bukkit.Material;

import java.util.LinkedHashMap;

public class ArchitectureShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Integer> items = new LinkedHashMap<>() {{
        for(Material material : Material.values()){
            if(material.name().contains("PLANKS")){
                put(material, 2);
            } else if(material.name().contains("WOOL")){
                put(material, 10);
            } else if(material.name().contains("CONCRETE")){
                put(material, 80);
            } else if(material.name().contains("PRESSURE")){
                put(material, 100);
            }
        }

        put(Material.MAGMA_BLOCK, 200);
        put(Material.TORCH, 6);
        put(Material.SOUL_TORCH, 50);
        put(Material.DRAGON_HEAD, 1000000);
        put(Material.SOUL_SAND, 10000);
        put(Material.SOUL_SOIL, 10000);
    }};

    public ArchitectureShopMenu(){
        super("건축 블럭 상점");
    }

    @Override
    public LinkedHashMap<Material, Integer> getItems() {
        return items;
    }
}
