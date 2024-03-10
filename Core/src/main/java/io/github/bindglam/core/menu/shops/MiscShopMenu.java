package io.github.bindglam.core.menu.shops;

import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.LinkedHashMap;

public class MiscShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Double> items = new LinkedHashMap<>() {{
        put(Material.BOOKSHELF, 1000.0);
        put(Material.EXPERIENCE_BOTTLE, 200000.0);
        put(Material.FIREWORK_ROCKET, 100.0);
        put(Material.LEAD, 50000.0);
        put(Material.SPAWNER, 20000000.0);
        put(Material.PHANTOM_MEMBRANE, 70000.0);
        put(Material.FERMENTED_SPIDER_EYE, 10000.0);
        put(Material.SPIDER_EYE, 100.0);
        put(Material.BONE, 100.0);
        put(Material.ROTTEN_FLESH, 100.0);
        put(Material.ENDER_PEARL, 1000.0);
        put(Material.SLIME_BALL, 100000.0);
        put(Material.WITHER_SKELETON_SKULL, 3000000.0);
        put(Material.ZOMBIE_SPAWN_EGG, 10000000.0);
        put(Material.CREEPER_SPAWN_EGG, 10000000.0);
        put(Material.SKELETON_SPAWN_EGG, 10000000.0);
        put(Material.ENDERMAN_SPAWN_EGG, 70000000.0);
        put(Material.WITHER_SPAWN_EGG, 1000000000.0);
        put(Material.VILLAGER_SPAWN_EGG, 10000000.0);
        put(Material.PIG_SPAWN_EGG, 800000.0);
        put(Material.CHICKEN_SPAWN_EGG, 800000.0);
        put(Material.COW_SPAWN_EGG, 800000.0);
        put(Material.SHULKER_SPAWN_EGG, 1500000000.0);
        put(Material.TNT, 10000.0);
        put(Material.SNOWBALL, 10.0);
        put(Material.EGG, 10.0);
        put(Material.BLAZE_ROD, 200000.0);
        put(Material.BREWING_STAND, 250000.0);
        put(Material.TOTEM_OF_UNDYING, 10000000.0);
        put(Material.WITHER_ROSE, 100000.0);
        put(Material.END_CRYSTAL, 100000.0);
        put(Material.TRIDENT, 38000000.0);
        put(Material.ENCHANTING_TABLE, 30000.0);
        put(Material.ANVIL, 30000.0);
        put(Material.GRINDSTONE, 10000.0);
        put(Material.BAMBOO, 500.0);
        put(Material.NAME_TAG, 30000.0);
        put(Material.WRITABLE_BOOK, 50000.0);
        for(Material material : Material.values()){
            if(material.name().contains("SMITHING_TEMPLATE")){
                put(material, 300000000.0);
            } else if(material.name().contains("_BANNER_PATTERN")){
                put(material, 1000000.0);
            }
        }
        put(Material.INK_SAC, 4000.0);
        put(Material.GLOW_INK_SAC, 70000.0);
        put(Material.TROPICAL_FISH_BUCKET, 100.0);
        put(Material.SALMON_BUCKET, 100.0);
        put(Material.PUFFERFISH_BUCKET, 100.0);
        put(Material.COD_BUCKET, 100.0);
        put(Material.SADDLE, 5000000.0);
    }};

    public static LinkedHashMap<ItemStack, Double> itemStacks = new LinkedHashMap<>() {{
        put(AdvItemCreator.getCustomPotion(new PotionEffect(PotionEffectType.BAD_OMEN, 300*20, 1, false, true)), 100000000.0);
        put(AdvItemCreator.getEnchantedBook(Enchantment.PROTECTION_ENVIRONMENTAL, 1), 100000.0);
        put(AdvItemCreator.getEnchantedBook(Enchantment.DAMAGE_ALL, 1), 150000.0);
        put(AdvItemCreator.getEnchantedBook(Enchantment.PROTECTION_EXPLOSIONS, 1), 100000.0);
        put(AdvItemCreator.getEnchantedBook(Enchantment.MENDING, 1), 9000000.0);
        put(AdvItemCreator.getEnchantedBook(Enchantment.DIG_SPEED, 1), 1000000.0);
        put(AdvItemCreator.getEnchantedBook(Enchantment.RIPTIDE, 1), 50000000.0);
        put(AdvItemCreator.getEnchantedBook(Enchantment.LOYALTY, 1), 50000000.0);
    }};

    public MiscShopMenu(){
        super("기타 상점");
    }

    @Override
    public LinkedHashMap<Material, Double> getItems() {
        return items;
    }

    @Override
    public LinkedHashMap<ItemStack, Double> getItemStacks() {
        return itemStacks;
    }
}
