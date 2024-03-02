package io.github.bindglam.core.menu.shops;

import dev.lone.itemsadder.api.CustomStack;
import io.github.bindglam.core.items.CosmeticGetItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class CosmeticsShopMenu extends AbstractShopMenu {
    public static LinkedHashMap<Material, Double> items = new LinkedHashMap<>() {{
    }};

    public static LinkedHashMap<ItemStack, Double> itemStacks = new LinkedHashMap<>() {{
        put(new CosmeticGetItem("gentleman_hat", "신사 모자").getItemStack(), 1000.0);
        put(new CosmeticGetItem("chef_hat", "요리사 모자").getItemStack(), 1500.0);
        put(new CosmeticGetItem("cowboy_hat", "카우보이 모자").getItemStack(), 1500.0);
        put(new CosmeticGetItem("snow_fox_sleeping", "겨울잠 여우 모자").getItemStack(), 5000.0);
        put(new CosmeticGetItem("witch_hat", "마녀 모자").getItemStack(), 1500.0);
        put(new CosmeticGetItem("leather_backpack", "가죽 가방").getItemStack(), 2000.0);
        put(new CosmeticGetItem("red_parrot", "빨간 앵무새").getItemStack(), 2000.0);
        put(new CosmeticGetItem("squirrel_tail", "다람쥐 꼬리").getItemStack(), 2000.0);
        put(new CosmeticGetItem("bee_nest", "벌집 가방").getItemStack(), 2500.0);
        put(new CosmeticGetItem("backpack", "가방").getItemStack(), 2000.0);
        put(new CosmeticGetItem("wings_enderdragon", "엔더드래곤의 날개").getItemStack(), 10000.0);
    }};

    public CosmeticsShopMenu(){
        super("치장품 상점");
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
