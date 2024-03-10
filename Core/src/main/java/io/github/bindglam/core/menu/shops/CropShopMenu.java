package io.github.bindglam.core.menu.shops;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class CropShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Double> items = new LinkedHashMap<>() {{
        put(Material.MELON, 50.0);
        put(Material.PUMPKIN, 55.0);
        put(Material.CARVED_PUMPKIN, 60.0);
        put(Material.JACK_O_LANTERN, 60.0);
        put(Material.HAY_BLOCK, 100.0);
        put(Material.WHEAT, 20.0);
        put(Material.CARROT, 20.0);
        put(Material.POTATO, 20.0);
        put(Material.BEETROOT, 25.0);
        put(Material.MELON_SEEDS, 80.0);
        put(Material.PUMPKIN_SEEDS, 80.0);
        put(Material.WHEAT_SEEDS, 50.0);
        put(Material.BEETROOT_SEEDS, 55.0);
        put(Material.BONE_MEAL, 50.0);
        put(Material.BONE, 450.0);
        put(Material.SUGAR_CANE, 50.0);

        put(Material.SUNFLOWER, 10.0);
        put(Material.RED_TULIP, 15.0);
        put(Material.ORANGE_TULIP, 15.0);
        put(Material.WHITE_TULIP, 15.0);
        put(Material.PINK_TULIP, 15.0);

        put(Material.OAK_SAPLING, 15.0);
        put(Material.BIRCH_SAPLING, 15.0);
        put(Material.DARK_OAK_SAPLING, 15.0);
        put(Material.SPRUCE_SAPLING, 15.0);
        put(Material.CHERRY_SAPLING, 15.0);
        put(Material.JUNGLE_SAPLING, 15.0);
        put(Material.ACACIA_SAPLING, 15.0);
    }};

    public static LinkedHashMap<ItemStack, Double> itemStacks = new LinkedHashMap<>() {{
        put(CustomStack.getInstance("customcrops:dry_pot").getItemStack(), 100.0);

        put(CustomStack.getInstance("customcrops:tomato_seeds").getItemStack(), 100.0);
        put(CustomStack.getInstance("customcrops:garlic_seeds").getItemStack(), 100.0);
        put(CustomStack.getInstance("customcrops:pepper_seeds").getItemStack(), 100.0);
        put(CustomStack.getInstance("customcrops:cabbage_seeds").getItemStack(), 100.0);
        put(CustomStack.getInstance("customcrops:chinese_cabbage_seeds").getItemStack(), 100.0);
        put(CustomStack.getInstance("customcrops:corn_seeds").getItemStack(), 100.0);
        put(CustomStack.getInstance("customcrops:eggplant_seeds").getItemStack(), 100.0);
        put(CustomStack.getInstance("customcrops:grape_seeds").getItemStack(), 100.0);
        put(CustomStack.getInstance("customcrops:hop_seeds").getItemStack(), 100.0);
        put(CustomStack.getInstance("customcrops:pineapple_seeds").getItemStack(), 100.0);
        put(CustomStack.getInstance("customcrops:pitaya_seeds").getItemStack(), 100.0);
        put(CustomStack.getInstance("customcrops:redpacket_seeds").getItemStack(), 100.0);

        put(CustomStack.getInstance("customcrops:watering_can_1").getItemStack(), 2000.0);
        put(CustomStack.getInstance("customcrops:watering_can_2").getItemStack(), 10000.0);
        put(CustomStack.getInstance("customcrops:watering_can_3").getItemStack(), 80000.0);
        put(CustomStack.getInstance("customcrops:watering_can_4").getItemStack(), 200000.0);

        put(CustomStack.getInstance("customcrops:tomato").getItemStack(), 70.0);
        put(CustomStack.getInstance("customcrops:garlic").getItemStack(), 70.0);
        put(CustomStack.getInstance("customcrops:pepper").getItemStack(), 70.0);
        put(CustomStack.getInstance("customcrops:cabbage").getItemStack(), 70.0);
        put(CustomStack.getInstance("customcrops:chinese_cabbage").getItemStack(), 70.0);
        put(CustomStack.getInstance("customcrops:corn").getItemStack(), 70.0);
        put(CustomStack.getInstance("customcrops:eggplant").getItemStack(), 70.0);
        put(CustomStack.getInstance("customcrops:grape").getItemStack(), 70.0);
        put(CustomStack.getInstance("customcrops:hop").getItemStack(), 70.0);
        put(CustomStack.getInstance("customcrops:pineapple").getItemStack(), 70.0);
        put(CustomStack.getInstance("customcrops:pitaya").getItemStack(), 70.0);
        put(CustomStack.getInstance("customcrops:redpacket").getItemStack(), 70.0);

        put(CustomStack.getInstance("customcrops:greenhouse_glass").getItemStack(), 5000.0);
        put(CustomStack.getInstance("customcrops:scarecrow").getItemStack(), 10000.0);

        put(CustomStack.getInstance("customcrops:sprinkler_1_item").getItemStack(), 100000.0);
        put(CustomStack.getInstance("customcrops:sprinkler_2_item").getItemStack(), 500000.0);
        put(CustomStack.getInstance("customcrops:sprinkler_3_item").getItemStack(), 8000000.0);
    }};

    public CropShopMenu(){
        super("장식및 농작물 상점");
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
