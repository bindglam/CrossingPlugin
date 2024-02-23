package io.github.bindglam.core.menu.shops;

import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class EventShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Integer> items = new LinkedHashMap<>() {{
    }};

    public static LinkedHashMap<ItemStack, Integer> itemStacks = new LinkedHashMap<>() {{
        put(new AdvItemCreator(Material.PAPER).setDisplayName("§f§l인벤토리 §a§l세이브권")
                .addEnchantment(Enchantment.DURABILITY, 5).getItemStack(), 1000);
    }};

    public EventShopMenu(){
        super("이벤트 상점");
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
        return EconomyUnit.COIN;
    }
}
