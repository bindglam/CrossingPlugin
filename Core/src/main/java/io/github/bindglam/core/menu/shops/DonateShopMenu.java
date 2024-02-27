package io.github.bindglam.core.menu.shops;

import io.github.bindglam.core.items.PvPLvResetItem;
import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class DonateShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Integer> items = new LinkedHashMap<>() {{
    }};

    public static LinkedHashMap<ItemStack, Integer> itemStacks = new LinkedHashMap<>() {{
    }};

    public DonateShopMenu(){
        super("마일리지 상점");
    }

    @Override
    public LinkedHashMap<Material, Integer> getItems() {
        return items;
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getItemStacks() {
        return itemStacks;
    }

    @Override
    public EconomyUnit getEconomyUnit() {
        return EconomyUnit.MILEAGE;
    }
}
