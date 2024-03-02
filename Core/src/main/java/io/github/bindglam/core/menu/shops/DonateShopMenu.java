package io.github.bindglam.core.menu.shops;

import dev.lone.itemsadder.api.CustomStack;
import io.github.bindglam.core.items.PvPLvResetItem;
import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class DonateShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Double> items = new LinkedHashMap<>() {{
    }};

    public static LinkedHashMap<ItemStack, Double> itemStacks = new LinkedHashMap<>() {{
        put(new AdvItemCreator(CustomStack.getInstance("minecraftcross:devil_sword").getItemStack())
                .addEnchantment(Enchantment.DAMAGE_ALL, 15)
                .addEnchantment(Enchantment.SWEEPING_EDGE, 10)
                .addEnchantment(Enchantment.FIRE_ASPECT, 10)
                .addEnchantment(Enchantment.LOOT_BONUS_MOBS, 10)
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.DURABILITY, 10)
                .addPersistentData("is-donate-item", true)
                .getItemStack(), 1500.0);

        put(new AdvItemCreator(CustomStack.getInstance("minecraftcross:devil_helmet").getItemStack())
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.DURABILITY, 5)
                .addPersistentData("is-donate-item", true)
                .getItemStack(), 2000.0);
        put(new AdvItemCreator(CustomStack.getInstance("minecraftcross:devil_chestplate").getItemStack())
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.DURABILITY, 5)
                .addPersistentData("is-donate-item", true)
                .getItemStack(), 6000.0);
        put(new AdvItemCreator(CustomStack.getInstance("minecraftcross:devil_leggings").getItemStack())
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.DURABILITY, 5)
                .addPersistentData("is-donate-item", true)
                .getItemStack(), 5000.0);
        put(new AdvItemCreator(CustomStack.getInstance("minecraftcross:devil_boots").getItemStack())
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.DURABILITY, 5)
                .addPersistentData("is-donate-item", true)
                .getItemStack(), 2000.0);
    }};

    public DonateShopMenu(){
        super("마일리지 상점");
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
        return EconomyUnit.MILEAGE;
    }
}
