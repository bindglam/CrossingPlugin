package io.github.bindglam.core.menu.shops;

import dev.lone.itemsadder.api.CustomFurniture;
import io.github.bindglam.core.items.PvPLvResetItem;
import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class FurnitureShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Integer> items = new LinkedHashMap<>() {{
    }};

    public static LinkedHashMap<ItemStack, Integer> itemStacks = new LinkedHashMap<>() {{
        put(CustomFurniture.getInstance("furniture:tv").getItemStack(), 5000000);
        put(CustomFurniture.getInstance("furniture:chair").getItemStack(), 2000000);
    }};

    public FurnitureShopMenu(){
        super("가구 상점");
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
