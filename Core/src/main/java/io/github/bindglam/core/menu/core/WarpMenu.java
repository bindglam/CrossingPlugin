package io.github.bindglam.core.menu.core;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import fr.dwightstudio.dsmapi.utils.ItemCreator;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.core.utils.TeleportUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class WarpMenu extends Menu {
    @Override
    public String getName() {
        return "워프";
    }

    @Override
    public Page[] getPages() {
        Page[] pages = new Page[getPageCount()];

        pages[0] = new Page() {
            @Override
            public String getName() {
                return "워프";
            }

            @Override
            public ItemStack[] getContent() {
                ItemStack[][] content = getPageType().getBlank2DArray();

                for(int y = 0; y < PageType.CHEST.getRow(); y++){
                    for(int x = 0; x < 9; x++){
                        content[y][x] = new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName("").getItem();
                    }
                }

                content[1][1] = new AdvItemCreator(Material.GRASS_BLOCK).setDisplayName("§a§l야생§b으로 이동")
                        .setLore(Component.text("야생으로 이동합니다").color(TextColor.color(100, 100, 100)))
                        .getItemStack();
                content[1][4] = new AdvItemCreator(Material.DIAMOND_BLOCK).setDisplayName("§e§l로비§b으로 이동")
                        .setLore(Component.text("로비으로 이동합니다").color(TextColor.color(100, 100, 100)))
                        .getItemStack();
                content[1][7] = new AdvItemCreator(Material.RED_BED).setDisplayName("§c§l홈§b으로 이동")
                        .setLore(Component.text("홈으로 이동합니다").color(TextColor.color(100, 100, 100)))
                        .setLore(Component.text("우클릭시, 현재 위치를 홈으로 설정합니다").color(TextColor.color(100, 100, 100)))
                        .getItemStack();

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
                    case GRASS_BLOCK:
                        view.getPlayer().closeInventory();
                        TeleportUtil.teleportAfterCooldown(view.getPlayer(), TeleportUtil.getRandomLoc(), 5, false);
                        break;

                    case DIAMOND_BLOCK:
                        view.getPlayer().closeInventory();
                        TeleportUtil.teleportAfterCooldown(view.getPlayer(), Core.INSTANCE.worldManager.getMVWorld("lobby").getSpawnLocation(), 5, true);
                        break;

                    case RED_BED:
                        view.getPlayer().closeInventory();
                        if(clickType == ClickType.LEFT)
                            Bukkit.dispatchCommand(view.getPlayer(), "home");
                        else
                            Bukkit.dispatchCommand(view.getPlayer(), "sethome");
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
