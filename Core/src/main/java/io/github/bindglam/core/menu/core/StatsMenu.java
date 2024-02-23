package io.github.bindglam.core.menu.core;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import fr.dwightstudio.dsmapi.utils.ItemCreator;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.managers.StatsManager;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.core.utils.TeleportUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class StatsMenu extends Menu {
    Player player;

    public StatsMenu(Player player){
        this.player = player;
    }

    @Override
    public String getName() {
        return "스텟";
    }

    @Override
    public Page[] getPages() {
        Page[] pages = new Page[getPageCount()];

        pages[0] = new Page() {
            @Override
            public String getName() {
                return "스텟";
            }

            @Override
            public ItemStack[] getContent() {
                ItemStack[][] content = getPageType().getBlank2DArray();
                StatsManager.Stats stats = StatsManager.getStats(player.getUniqueId());

                for(int y = 0; y < PageType.CHEST.getRow(); y++){
                    for(int x = 0; x < 9; x++){
                        content[y][x] = new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName("").getItem();
                    }
                }

                content[1][1] = new AdvItemCreator(Material.DIAMOND_AXE).setDisplayName("§4§l힘 §7: §f§lLv." + stats.strongLv).setLore(List.of("§bXp : " + stats.strongXp)).getItemStack();
                content[1][3] = new AdvItemCreator(Material.SHIELD).setDisplayName("§f§l방어력 §7: §f§lLv." + stats.defenseLv).setLore(List.of("§bXp : " + stats.defenseXp)).getItemStack();
                content[1][5] = new AdvItemCreator(Material.IRON_SWORD).setDisplayName("§b§l스피드 §7: §f§lLv." + stats.speedLv).setLore(List.of("§bXp : " + stats.speedXp)).getItemStack();
                content[1][7] = new AdvItemCreator(Material.NETHERITE_AXE).setDisplayName("§4§lPvP §7: §f§lLv." + stats.pvpLv).setLore(List.of("§7§l25 이상이 올라가면 당신은 인벤세이브의 보호를 받을 수 없습니다!")).getItemStack();

                return getPageType().flatten(content);
            }

            @Override
            public PageType getPageType() {
                return PageType.CHEST;
            }

            @Override
            public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
            }
        };
        return pages;
    }

    @Override
    public int getPageCount() {
        return 1;
    }
}
