package io.github.bindglam.core.menu.shops;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.Objects;

public class ShopMenu extends Menu {
    @Override
    public String getName() {
        return "상점";
    }

    @Override
    public Page[] getPages() {
        Page[] pages = new Page[getPageCount()];

        pages[0] = new Page() {
            @Override
            public String getName() {
                return "상점";
            }

            @Override
            public ItemStack[] getContent() {
                ItemStack[][] content = getPageType().getBlank2DArray();

                for(int y = 0; y < getPageType().getRow(); y++){
                    for(int x = 0; x < 9; x++){
                        content[y][x] = new AdvItemCreator(Material.GRAY_STAINED_GLASS_PANE).getItemStack();
                    }
                }

                content[1][0] = new AdvItemCreator(Material.DIAMOND_ORE).setDisplayName("§b§l광물 상점").getItemStack();
                content[1][2] = new AdvItemCreator(Material.PUMPKIN).setDisplayName("§6§l장식및 농작물 상점").getItemStack();
                content[1][4] = new AdvItemCreator(Material.COOKED_BEEF).setDisplayName("§e§l음식 상점").getItemStack();
                content[1][6] = new AdvItemCreator(Material.LIGHT_BLUE_CONCRETE).setDisplayName("§a§l건축 상점").getItemStack();
                content[1][8] = new AdvItemCreator(Material.END_PORTAL_FRAME).setDisplayName("§d§l기타 상점").getItemStack();
                content[3][0] = new AdvItemCreator(Material.GLASS).setDisplayName("§f§l가구 상점").getItemStack();
                content[3][2] = new AdvItemCreator(Material.END_CRYSTAL).setDisplayName("§d§l이벤트 상점").getItemStack();
                content[3][4] = new AdvItemCreator(Material.GOLD_INGOT).setDisplayName("§5§l마일리지 상점").getItemStack();

                return getPageType().flatten(content);
            }

            @Override
            public PageType getPageType() {
                return PageType.CHEST_PLUS_PLUS;
            }

            @Override
            public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
                if(itemStack == null) return;
                switch (itemStack.getType()) {
                    case DIAMOND_ORE:
                        new OreShopMenu().open(view.getPlayer(), 0);
                        break;

                    case PUMPKIN:
                        new CropShopMenu().open(view.getPlayer(), 0);
                        break;

                    case COOKED_BEEF:
                        new FoodShopMenu().open(view.getPlayer(), 0);
                        break;

                    case END_PORTAL_FRAME:
                        new MiscShopMenu().open(view.getPlayer(), 0);
                        break;

                    case LIGHT_BLUE_CONCRETE:
                        new ArchitectureShopMenu().open(view.getPlayer(), 0);
                        break;

                    case GLASS:
                        new FurnitureShopMenu().open(view.getPlayer(), 0);
                        break;

                    case END_CRYSTAL:
                        new EventShopMenu().open(view.getPlayer(), 0);
                        break;

                    case GOLD_INGOT:
                        new DonateShopMenu().open(view.getPlayer(), 0);
                        break;
                }
            }
        };
        return pages;
    }

    @Override
    public int getPageCount() {
        return 1;
    }

    public static void load(){
        LinkedHashMap<Material, Integer> oreitems = new LinkedHashMap<>();
        LinkedHashMap<Material, Integer> miscitems = new LinkedHashMap<>();
        LinkedHashMap<Material, Integer> fooditems = new LinkedHashMap<>();
        LinkedHashMap<Material, Integer> cropitems = new LinkedHashMap<>();
        LinkedHashMap<Material, Integer> architectureitems = new LinkedHashMap<>();
        for(int i = 0; i < 5*9+9; i++) {
            if(Core.INSTANCE.getConfig().get("OreShop." + i + ".material") != null){
                oreitems.put(Material.getMaterial(Objects.requireNonNull(Core.INSTANCE.getConfig().getString("OreShop." + i + ".material"))),
                        (Integer) Core.INSTANCE.getConfig().get("OreShop." + i + ".cost"));
            }
            if(Core.INSTANCE.getConfig().get("MiscShop." + i + ".material") != null){
                miscitems.put(Material.getMaterial(Objects.requireNonNull(Core.INSTANCE.getConfig().getString("MiscShop." + i + ".material"))),
                        (Integer) Core.INSTANCE.getConfig().get("MiscShop." + i + ".cost"));
            }
            if(Core.INSTANCE.getConfig().get("FoodShop." + i + ".material") != null){
                fooditems.put(Material.getMaterial(Objects.requireNonNull(Core.INSTANCE.getConfig().getString("FoodShop." + i + ".material"))),
                        (Integer) Core.INSTANCE.getConfig().get("FoodShop." + i + ".cost"));
            }
            if(Core.INSTANCE.getConfig().get("CropShop." + i + ".material") != null){
                cropitems.put(Material.getMaterial(Objects.requireNonNull(Core.INSTANCE.getConfig().getString("CropShop." + i + ".material"))),
                        (Integer) Core.INSTANCE.getConfig().get("CropShop." + i + ".cost"));
            }
            if(Core.INSTANCE.getConfig().get("ArchitectureShop." + i + ".material") != null){
                architectureitems.put(Material.getMaterial(Objects.requireNonNull(Core.INSTANCE.getConfig().getString("ArchitectureShop." + i + ".material"))),
                        (Integer) Core.INSTANCE.getConfig().get("ArchitectureShop." + i + ".cost"));
            }
        }

        if(Core.INSTANCE.getConfig().get("ArchitectureShop.0.material") != null) {
            OreShopMenu.items = oreitems;
            MiscShopMenu.items = miscitems;
            FoodShopMenu.items = fooditems;
            CropShopMenu.items = cropitems;
            ArchitectureShopMenu.items = architectureitems;
            Core.INSTANCE.getLogger().info("Loaded Shop items successfully!");
        }
    }

    public static void save(){
        for(int i = 0; i < 5*9+9; i++) {
            if(new OreShopMenu().getItems().keySet().size() > i) {
                Core.INSTANCE.getConfig().set("OreShop." + i + ".material", new OreShopMenu().getItems().keySet().stream().toList().get(i).toString());
                Core.INSTANCE.getConfig().set("OreShop." + i + ".cost", new OreShopMenu().getItems().values().stream().toList().get(i));
            }
            if(new MiscShopMenu().getItems().keySet().size() > i) {
                Core.INSTANCE.getConfig().set("MiscShop." + i + ".material", new MiscShopMenu().getItems().keySet().stream().toList().get(i).toString());
                Core.INSTANCE.getConfig().set("MiscShop." + i + ".cost", new MiscShopMenu().getItems().values().stream().toList().get(i));
            }
            if(new FoodShopMenu().getItems().keySet().size() > i) {
                Core.INSTANCE.getConfig().set("FoodShop." + i + ".material", new FoodShopMenu().getItems().keySet().stream().toList().get(i).toString());
                Core.INSTANCE.getConfig().set("FoodShop." + i + ".cost", new FoodShopMenu().getItems().values().stream().toList().get(i));
            }
            if(new CropShopMenu().getItems().keySet().size() > i) {
                Core.INSTANCE.getConfig().set("CropShop." + i + ".material", new CropShopMenu().getItems().keySet().stream().toList().get(i).toString());
                Core.INSTANCE.getConfig().set("CropShop." + i + ".cost", new CropShopMenu().getItems().values().stream().toList().get(i));
            }
            if(new ArchitectureShopMenu().getItems().keySet().size() > i) {
                Core.INSTANCE.getConfig().set("ArchitectureShop." + i + ".material", new ArchitectureShopMenu().getItems().keySet().stream().toList().get(i).toString());
                Core.INSTANCE.getConfig().set("ArchitectureShop." + i + ".cost", new ArchitectureShopMenu().getItems().values().stream().toList().get(i));
            }
        }
    }
}
