package io.github.bindglam.core.menu;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.managers.PrivateSettingManager;
import io.github.bindglam.core.utils.AdvItemCreator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class PrivateSettingMenu extends Menu {
    private final Player player;

    public PrivateSettingMenu(Player player){
        this.player = player;
    }

    @Override
    public String getName() {
        return "개인 설정";
    }

    @Override
    public Page[] getPages() {
        Page[] pages = new Page[getPageCount()];
        pages[0] = new Page() {
            @Override
            public String getName() {
                return "개인 설정 ( 페이즈 1 )";
            }

            @Override
            public ItemStack[] getContent() {
                PrivateSettingManager.PrivateSetting setting = PrivateSettingManager.loadPlayer(player.getUniqueId());
                ItemStack[][] contents = getPageType().getBlank2DArray();

                for(int y = 0; y < getPageType().getRow(); y++){
                    for(int x = 0; x < 9; x++){
                        contents[y][x] = new AdvItemCreator(Material.GRAY_STAINED_GLASS_PANE).getItemStack();
                    }
                }

                contents[1][1] = new AdvItemCreator(Material.IRON_SWORD).setDisplayName("§c§lPVP §7: " + getBoolText((boolean) setting.settings.get("PvP")))
                        .addPersistentData("key", "PvP").addPersistentData("type", "bool").getItemStack();
                contents[1][2] = new AdvItemCreator(Material.FLINT).setDisplayName("§8§l훔치기 모드 §7: " + getBoolText((boolean) setting.settings.get("Steal")))
                        .addPersistentData("key", "Steal").addPersistentData("type", "bool").getItemStack();
                contents[1][3] = new AdvItemCreator(Material.WRITABLE_BOOK).setDisplayName("§e§l땅 입장 메세지 §7: " + getBoolText((boolean) setting.settings.get("GroundEnterMessage")))
                        .addPersistentData("key", "GroundEnterMessage").addPersistentData("type", "bool").getItemStack();
                contents[1][4] = new AdvItemCreator(Material.PAPER).setDisplayName("§f§l공지 §7: " + getBoolText((boolean) setting.settings.get("Announce")))
                        .addPersistentData("key", "Announce").addPersistentData("type", "bool").getItemStack();

                return getPageType().flatten(contents);
            }

            @Override
            public PageType getPageType() {
                return PageType.CHEST_PLUS;
            }

            @Override
            public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
                PrivateSettingManager.PrivateSetting setting = PrivateSettingManager.loadPlayer(view.getPlayer().getUniqueId());
                if(itemStack == null) return;

                view.getPlayer().playSound(view.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100f, 2f);
                if(itemStack.getPersistentDataContainer().has(new NamespacedKey(Core.INSTANCE, "key")) && itemStack.getPersistentDataContainer().has(new NamespacedKey(Core.INSTANCE, "type"))){
                    if(Objects.equals(itemStack.getPersistentDataContainer().get(new NamespacedKey(Core.INSTANCE, "type"), PersistentDataType.STRING), "bool")) {
                        setting.settings.put(itemStack.getPersistentDataContainer().get(new NamespacedKey(Core.INSTANCE, "key"), PersistentDataType.STRING),
                                !((boolean) setting.settings.get(itemStack.getPersistentDataContainer().get(new NamespacedKey(Core.INSTANCE, "key"), PersistentDataType.STRING))));
                    }
                }

                new PrivateSettingMenu(view.getPlayer()).open(view.getPlayer(), 0);
            }
        };
        return pages;
    }

    @Override
    public int getPageCount() {
        return 1;
    }

    private String getBoolText(boolean value){
        if(value){
            return "§a§l켜짐";
        } else {
            return "§c§l꺼짐";
        }
    }
}
