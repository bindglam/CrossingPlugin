package io.github.bindglam.core.menu.shops;

import net.minecraft.world.item.CreativeModeTab;
import org.bukkit.Material;

import java.util.LinkedHashMap;

public class ArchitectureShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Integer> items = new LinkedHashMap<>() {{
        put(Material.GRASS_BLOCK, 10);
        put(Material.DIRT, 5);
        put(Material.STONE, 10);
        put(Material.COBBLESTONE, 1);
        put(Material.DEEPSLATE, 10);
        put(Material.COBBLED_DEEPSLATE, 1);

        for(Material material : Material.values()){
            if(material.name().contains("PLANKS")){
                put(material, 2);
            } else if(material.name().contains("LOG")){
                put(material, 3);
            } else if(material.name().contains("WOOL")){
                put(material, 10);
            } else if(material.name().contains("CONCRETE")){
                put(material, 80);
            } else if(material.name().contains("PRESSURE")){
                put(material, 100);
            } else if(material.name().contains("GLASS")){
                if(material == Material.SPYGLASS || material == Material.GLASS_BOTTLE) continue;
                put(material, 150);
            } else if(material.name().contains("FROGLIGHT")){
                put(material, 1000);
            } else if(material.name().contains("SANDSTONE")){
                put(material, 100);
            }
        }

        put(Material.MAGMA_BLOCK, 200);
        put(Material.TORCH, 6);
        put(Material.SOUL_TORCH, 50);
        put(Material.DRAGON_HEAD, 1000000);
        put(Material.SOUL_SAND, 10000);
        put(Material.SOUL_SOIL, 10000);
        put(Material.TURTLE_EGG, 5000000);
        put(Material.OBSIDIAN, 10000);
        put(Material.CRYING_OBSIDIAN, 30000);
        put(Material.GLOWSTONE, 25000);
        put(Material.SEA_LANTERN, 60000);
        put(Material.SCAFFOLDING, 10000);
        put(Material.CALCITE, 3000);
    }};

    public ArchitectureShopMenu(){
        super("건축 블럭 상점");
    }

    @Override
    public LinkedHashMap<Material, Integer> getItems() {
        return items;
    }
}
