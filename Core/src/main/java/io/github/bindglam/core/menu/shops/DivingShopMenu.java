package io.github.bindglam.core.menu.shops;

import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.LinkedHashMap;

public class DivingShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Integer> items = new LinkedHashMap<>() {{
    }};

    public static LinkedHashMap<ItemStack, Integer> itemStacks = new LinkedHashMap<>() {{
        put(new AdvItemCreator(Material.NETHERITE_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.DURABILITY, 3)
                .addEnchantment(Enchantment.MENDING, 1).getItemStack(), 1000);
        put(new AdvItemCreator(Material.NETHERITE_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.DURABILITY, 3)
                .addEnchantment(Enchantment.MENDING, 1).getItemStack(), 1000);
        put(new AdvItemCreator(Material.NETHERITE_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.DURABILITY, 3)
                .addEnchantment(Enchantment.MENDING, 1).getItemStack(), 1000);
        put(new AdvItemCreator(Material.NETHERITE_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchantment(Enchantment.DURABILITY, 3)
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.PROTECTION_FALL, 4).getItemStack(), 1000);
        put(new AdvItemCreator(Material.NETHERITE_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 5)
                .addEnchantment(Enchantment.DURABILITY, 3)
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.FIRE_ASPECT, 2)
                .addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3).getItemStack(), 1500);
        put(new AdvItemCreator(Material.NETHERITE_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
                .addEnchantment(Enchantment.DURABILITY, 3)
                .addEnchantment(Enchantment.MENDING, 1)
                .addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).getItemStack(), 1500);
    }};

    public DivingShopMenu(){
        super("잠수 상점");
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
        return EconomyUnit.POINT;
    }
}
