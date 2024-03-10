package io.github.bindglam.core.menu.shops;

import io.github.bindglam.core.items.FlyItem;
import io.github.bindglam.core.items.PvPLvResetItem;
import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class EventShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Double> items = new LinkedHashMap<>() {{
    }};

    public static LinkedHashMap<ItemStack, Double> itemStacks = new LinkedHashMap<>() {{
        put(new AdvItemCreator(Material.PAPER).setDisplayName("§f§l인벤토리 §a§l세이브권")
                .addEnchantment(Enchantment.DURABILITY, 5).getItemStack(), 1000.0);
        put(new AdvItemCreator(Material.PAPER).setDisplayName("§b§l강화 §f§l보호권")
                .addEnchantment(Enchantment.DURABILITY, 5).getItemStack(), 1000.0);
        put(new PvPLvResetItem().getItemStack(), 2500.0);
        put(new FlyItem(60 * 60).getItemStack(), 1000.0);
        put(new FlyItem(2 * 60 * 60).getItemStack(), 2000.0);
        put(new FlyItem(4 * 60 * 60).getItemStack(), 3800.0);
    }};

    public EventShopMenu(){
        super("이벤트 상점");
    }

    @Override
    public LinkedHashMap<Material, Double> getItems() {
        return items;
    }

    @Override
    public LinkedHashMap<ItemStack, Double> getItemStacks() {
        return itemStacks;
    }

    @Override
    public EconomyUnit getEconomyUnit() {
        return EconomyUnit.COIN;
    }
}
