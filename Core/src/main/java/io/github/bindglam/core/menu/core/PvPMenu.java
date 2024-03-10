package io.github.bindglam.core.menu.core;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import fr.dwightstudio.dsmapi.utils.ItemCreator;
import io.github.bindglam.battle.MapManager;
import io.github.bindglam.core.managers.PvPManager;
import io.github.bindglam.core.managers.StatsManager;
import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class PvPMenu extends Menu {
    @Override
    public String getName() {
        return "PvP장";
    }

    @Override
    public Page[] getPages() {
        Page[] pages = new Page[getPageCount()];

        pages[0] = new Page() {
            @Override
            public String getName() {
                return "PvP장";
            }

            @Override
            public ItemStack[] getContent() {
                PvPManager.sortRank();

                ItemStack[][] content = getPageType().getBlank2DArray();;

                for(int y = 0; y < PageType.CHEST.getRow(); y++){
                    for(int x = 0; x < 9; x++){
                        content[y][x] = new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName("").getItem();
                    }
                }

                content[1][1] = new AdvItemCreator(Material.GREEN_STAINED_GLASS_PANE).setDisplayName("§a§l맵 입장하기").getItemStack();
                if(!PvPManager.pvpRanks.isEmpty()) {
                    content[1][4] = new AdvItemCreator(Material.PLAYER_HEAD).makeHead(Bukkit.getOfflinePlayer(PvPManager.pvpRanks.keySet().stream().toList().get(0)).getName())
                            .setDisplayName("§e랭킹").getItemStack();
                } else {
                    content[1][4] = new AdvItemCreator(Material.PLAYER_HEAD)
                            .setDisplayName("§e랭킹").getItemStack();
                }
                content[1][7] = new AdvItemCreator(Material.RED_STAINED_GLASS_PANE).setDisplayName("§c§l맵 퇴장하기").getItemStack();

                return getPageType().flatten(content);
            }

            @Override
            public PageType getPageType() {
                return PageType.CHEST;
            }

            @Override
            public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
                if(itemStack == null) return;

                switch (itemStack.getType()){
                    case GREEN_STAINED_GLASS_PANE:
                        view.setPage(1);
                        break;

                    case RED_STAINED_GLASS_PANE:
                        view.close();
                        MapManager.leavePlayer(view.getPlayer());
                        break;

                    case PLAYER_HEAD:
                        view.setPage(2);
                        break;
                }
            }
        };

        pages[1] = new Page() {
            @Override
            public String getName() {
                return "PvP장 ( 맵 선택 )";
            }

            @Override
            public ItemStack[] getContent() {
                ItemStack[][] content = getPageType().getBlank2DArray();;

                for(int y = 0; y < PageType.DOUBLE_CHEST.getRow(); y++){
                    for(int x = 0; x < 9; x++){
                        content[y][x] = new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName("").getItem();
                    }
                }

                content[1][1] = new AdvItemCreator(Material.BEDROCK).setDisplayName("§f§l배드락")
                        .setLore(List.of("플레이어 수 : " + MapManager.maps.get("pvp-bedrock").players.size() + " / " + MapManager.maps.get("pvp-bedrock").limitPlayerCount)).getItemStack();

                return getPageType().flatten(content);
            }

            @Override
            public PageType getPageType() {
                return PageType.DOUBLE_CHEST;
            }

            @Override
            public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
                if(itemStack == null) return;

                view.setPage(1);
                switch (itemStack.getType()){
                    case BEDROCK:
                        view.close();
                        MapManager.joinPlayer(view.getPlayer(), "pvp-bedrock");
                        break;
                }
            }
        };

        pages[2] = new Page() {
            @Override
            public String getName() {
                return "PvP장 ( 랭킹 )";
            }

            @Override
            public ItemStack[] getContent() {
                PvPManager.sortRank();

                ItemStack[][] content = getPageType().getBlank2DArray();;

                for(int y = 0; y < PageType.CHEST.getRow(); y++){
                    for(int x = 0; x < 9; x++){
                        content[y][x] = new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName("").getItem();
                    }
                }

                for(int i = 0; i < 7; i++){
                    if(PvPManager.pvpRanks.size()-1 < i){
                        content[1][1+i] = new AdvItemCreator(Material.PLAYER_HEAD)
                                .setDisplayName("-").getItemStack();
                    } else {
                        content[1][1 + i] = new AdvItemCreator(Material.PLAYER_HEAD).makeHead(Bukkit.getOfflinePlayer(PvPManager.pvpRanks.keySet().stream().toList().get(i)).getName())
                                .setDisplayName(Bukkit.getOfflinePlayer(PvPManager.pvpRanks.keySet().stream().toList().get(i)).getName())
                                .setLore(List.of("승리 횟수 : " + PvPManager.pvpRanks.values().stream().toList().get(i) + "회")).getItemStack();
                    }
                }

                return getPageType().flatten(content);
            }

            @Override
            public PageType getPageType() {
                return PageType.CHEST;
            }

            @Override
            public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
                if(itemStack == null) return;

                view.setPage(2);
            }
        };
        return pages;
    }

    @Override
    public int getPageCount() {
        return 3;
    }
}
