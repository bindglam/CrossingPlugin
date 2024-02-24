package io.github.bindglam.core.menu.shops;

import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.LinkedHashMap;

public class MiscShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Integer> items = new LinkedHashMap<>() {{
        put(Material.BOOKSHELF, 1000);
        put(Material.EXPERIENCE_BOTTLE, 200000);
        put(Material.FIREWORK_ROCKET, 100);
        put(Material.LEAD, 50000);
        put(Material.SPAWNER, 20000000);
        put(Material.PHANTOM_MEMBRANE, 70000);
        put(Material.FERMENTED_SPIDER_EYE, 10000);
        put(Material.SPIDER_EYE, 100);
        put(Material.BONE, 100);
        put(Material.ROTTEN_FLESH, 100);
        put(Material.ENDER_PEARL, 1000);
        put(Material.SLIME_BALL, 100000);
        put(Material.WITHER_SKELETON_SKULL, 3000000);
        put(Material.ZOMBIE_SPAWN_EGG, 10000000);
        put(Material.CREEPER_SPAWN_EGG, 10000000);
        put(Material.SKELETON_SPAWN_EGG, 10000000);
        put(Material.ENDERMAN_SPAWN_EGG, 70000000);
        put(Material.WITHER_SPAWN_EGG, 1000000000);
        put(Material.VILLAGER_SPAWN_EGG, 10000000);
        put(Material.PIG_SPAWN_EGG, 800000);
        put(Material.CHICKEN_SPAWN_EGG, 800000);
        put(Material.COW_SPAWN_EGG, 800000);
        put(Material.TNT, 10000);
        put(Material.SNOWBALL, 10);
        put(Material.EGG, 1000);
        put(Material.BLAZE_ROD, 200000);
        put(Material.BREWING_STAND, 250000);
        put(Material.TOTEM_OF_UNDYING, 10000000);
        put(Material.WITHER_ROSE, 100000);
        put(Material.END_CRYSTAL, 100000);
        put(Material.TRIDENT, 38000000);
        put(Material.ENCHANTING_TABLE, 30000);
        put(Material.ANVIL, 30000);
        put(Material.GRINDSTONE, 10000);
    }};

    public static LinkedHashMap<ItemStack, Integer> itemStacks = new LinkedHashMap<>() {{
        put(AdvItemCreator.getCustomPotion(new PotionEffect(PotionEffectType.BAD_OMEN, 300*20, 1, false, true)), 100000000);
        put(AdvItemCreator.getEnchantedBook(Enchantment.PROTECTION_ENVIRONMENTAL, 1), 100000);
        put(AdvItemCreator.getEnchantedBook(Enchantment.DAMAGE_ALL, 1), 150000);
        put(AdvItemCreator.getEnchantedBook(Enchantment.PROTECTION_EXPLOSIONS, 1), 100000);
        put(AdvItemCreator.getEnchantedBook(Enchantment.MENDING, 1), 9000000);
        put(AdvItemCreator.getEnchantedBook(Enchantment.DIG_SPEED, 1), 1000000);
        put(AdvItemCreator.getEnchantedBook(Enchantment.RIPTIDE, 1), 50000000);
        put(AdvItemCreator.getEnchantedBook(Enchantment.LOYALTY, 1), 50000000);
        put(new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE), 50000);
    }};

    public MiscShopMenu(){
        super("기타 상점");
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
