package io.github.bindglam.core.menu.core;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import fr.dwightstudio.dsmapi.utils.ItemCreator;
import io.github.bindglam.core.menu.PrivateSettingMenu;
import io.github.bindglam.core.menu.shops.UserShopMenu;
import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CoreMenu extends Menu {
    private final Player player;

    public CoreMenu(Player player){
        this.player = player;
    }

    @Override
    public String getName() {
        return "메뉴";
    }

    @Override
    public Page[] getPages() {
        Page[] pages = new Page[getPageCount()];

        pages[0] = new Page() {
            @Override
            public String getName() {
                return "메뉴";
            }

            @Override
            public ItemStack[] getContent() {
                ItemStack[][] content = getPageType().getBlank2DArray();

                for(int y = 0; y < PageType.CHEST_PLUS_PLUS.getRow(); y++){
                    for(int x = 0; x < 9; x++){
                        content[y][x] = new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName("").getItem();
                    }
                }

                content[0][0] = new AdvItemCreator(Material.PLAYER_HEAD).makeHead("discord").setDisplayName("§9§l디스코드")
                        .getItemStack();
                content[0][8] = new AdvItemCreator(Material.PLAYER_HEAD).makeHead(player.getName()).setDisplayName(player.getName())
                        .getItemStack();
                content[1][1] = new AdvItemCreator(Material.ENDER_PEARL).setDisplayName("§3§l워프")
                        .getItemStack();
                content[1][3] = new AdvItemCreator(Material.EMERALD).setDisplayName("§a§l상점")
                        .getItemStack();
                content[1][5] = new AdvItemCreator(Material.DIRT_PATH).setDisplayName("§e§l땅")
                        .getItemStack();
                content[1][7] = new AdvItemCreator(Material.BLAZE_POWDER).setDisplayName("§d§l스텟")
                        .getItemStack();
                content[3][1] = new AdvItemCreator(Material.PAPER).setDisplayName("§f§l주식")
                        .getItemStack();
                content[3][3] = new AdvItemCreator(Material.WRITABLE_BOOK).setDisplayName("§7§l개인 설정")
                        .getItemStack();
                content[3][5] = new AdvItemCreator(Material.GOLD_INGOT).setDisplayName("§e§l유저 상점")
                        .getItemStack();

                return getPageType().flatten(content);
            }

            @Override
            public PageType getPageType() {
                return PageType.CHEST_PLUS_PLUS;
            }

            @Override
            public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
                if(itemStack == null) return;

                switch (itemStack.getType()){
                    case PLAYER_HEAD:
                        if(slot != 0) break;
                        view.getPlayer().closeInventory();
                        Bukkit.dispatchCommand(view.getPlayer(), "discord");
                        break;

                    case ENDER_PEARL:
                        view.getPlayer().playSound(view.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 100f, 1f);
                        new WarpMenu().open(view.getPlayer(), 0);
                        break;

                    case EMERALD:
                        view.getPlayer().playSound(view.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_YES, 100f, 2f);
                        view.getPlayer().closeInventory();
                        Bukkit.dispatchCommand(view.getPlayer(), "상점");
                        break;

                    case DIRT_PATH:
                        view.getPlayer().playSound(view.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100f, 2f);
                        new GroundMenu().open(view.getPlayer(), 0);
                        break;

                    case BLAZE_POWDER:
                        view.getPlayer().playSound(view.getPlayer().getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 100f, 2f);
                        new StatsMenu(view.getPlayer()).open(view.getPlayer(), 0);
                        break;

                    case PAPER:
                        view.getPlayer().playSound(view.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100f, 1.5f);
                        new StockMenu().open(view.getPlayer(), 0);
                        break;

                    case WRITABLE_BOOK:
                        view.getPlayer().playSound(view.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100f, 1.5f);
                        new PrivateSettingMenu(view.getPlayer()).open(view.getPlayer(), 0);
                        break;

                    case GOLD_INGOT:
                        view.getPlayer().playSound(view.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100f, 1.5f);
                        new UserShopMenu().open(view.getPlayer(), 0);
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
