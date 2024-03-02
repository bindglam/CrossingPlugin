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
                content[3][6] = new AdvItemCreator(Material.LEATHER_CHESTPLATE).setDisplayName("§d§l치장품 상점").getItemStack();

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

                    case LEATHER_CHESTPLATE:
                        new CosmeticsShopMenu().open(view.getPlayer(), 0);
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
}
