package io.github.bindglam.core.menu.blocks;

import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import dev.lone.itemsadder.api.FontImages.TexturedInventoryWrapper;
import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.advancements.EnhanceAdvancement;
import io.github.bindglam.core.items.PluginItemManager;
import io.github.bindglam.core.utils.AdvancementUtil;
import io.github.bindglam.core.utils.InvUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnhanceMenu extends Menu {
    public static final List<String> additionalEnhanceAbleArmors = List.of("§b§l고대 격투가의 머리");
    public static final List<String> additionalEnhanceAbleWeapons = List.of("§c§l히드라의 정수");
    private final Player player;

    public EnhanceMenu(Player player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return "강화대";
    }

    @Override
    public Page[] getPages() {
        Page[] pages = new Page[getPageCount()];

        pages[0] = new Page() {
            @Override
            public String getName() {
                return "§f" + new FontImageWrapper("minecraftcross:enhance").applyPixelsOffset(-8);
            }

            @Override
            public ItemStack[] getContent() {
                ItemStack[][] content = getPageType().getBlank2DArray();

                /*
                for(int i = 0; i < 9; i++){
                    for(int j = 0; j < 3; j++){
                        content[j][i] = new AdvItemCreator(Material.BARRIER).getItemStack();
                    }
                }
                 */
                content[1][4] = player.getInventory().getItemInMainHand();

                return getPageType().flatten(content);
            }

            @Override
            public PageType getPageType() {
                return PageType.CHEST;
            }

            @Override
            @Deprecated
            public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
                if(itemStack == null) return;

                if (slot == 9 + 4) {
                    Inventory gui = view.getInventoryView().getTopInventory();
                    if (gui.getItem(9 + 4) == null) {
                        player.closeInventory();
                        return;
                    }
                    ItemStack item = Objects.requireNonNull(gui.getItem(9 + 4)).clone();
                    ItemMeta meta = item.getItemMeta();

                    // 강화
                    List<String> lore;
                    if (meta.hasLore()) {
                        lore = new ArrayList<>(Objects.requireNonNull(meta.getLore()));
                    } else {
                        lore = new ArrayList<>();
                    }

                    if(player.getInventory().contains(Material.DIAMOND)){
                        if(lore.size() > 1){
                            int count = countChar(lore.get(1), '★');
                            if(count == 10){
                                return;
                            }
                        }
                        for(ItemStack rubyItem : player.getInventory()){
                            if(rubyItem == null) continue;
                            if(Objects.equals(CustomStack.getInstance("minecraftcross:ruby").getDisplayName(), rubyItem.getDisplayName())){
                                InvUtils.consumeItem(player, 1, rubyItem);
                                break;
                            }
                        }
                    } else
                        return;

                    if (!lore.isEmpty() && lore.size() >= 2) {
                        if (!lore.get(1).contains("★") && !lore.get(1).contains("☆")) {
                            doFirstEnhance(lore, item);
                        } else {
                            if (lore.size() - 4 > 0) {
                                for(String str : lore) {
                                    if(str.contains("성공 확률")) {
                                        lore.remove(lore.size() - 1);
                                        lore.remove(lore.size() - 1);
                                        lore.remove(lore.size() - 1);
                                        lore.remove(lore.size() - 1);
                                        break;
                                    }
                                }
                            }
                            item = doEnhance(lore, item, clickType == ClickType.RIGHT && view.getPlayer().isOp());
                        }
                    } else {
                        doFirstEnhance(lore, item);
                    }

                    view.getPlayer().getInventory().setItemInMainHand(item);
                    if (item != null){
                        int count = countChar(lore.get(1), '★') + 1;
                        if(count <= 10) {
                            lore.add("");
                            lore.add("§a§l - 성공 확률 : " + (int) (((double) (10 - count) / 10 + 0.1) * 100) + "%");
                            lore.add("§c§l - 실패 확률 : " + ((int) ((100 - ((double) (10 - count) / 10 + 0.1) * 100 - 10)) + "%"));
                            lore.add("§4§l - 파괴 확률 : 10%");
                        }
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                    }
                    gui.setItem(9 + 4, item);
                }
            }
        };
        return pages;
    }

    @Override
    public int getPageCount() {
        return 1;
    }

    @Deprecated
    private void doFirstEnhance(List<String> lore, ItemStack item){
        ItemMeta meta = item.getItemMeta();
        lore.add(0, "§e§l★☆☆☆☆☆☆☆☆☆");
        lore.add(0, "");
        lore.add(2, "");
        updateLore(1, lore, item);
        lore.add(2, "");
        /*

        ★☆☆☆☆☆☆☆☆☆

        피해량 +10%

         */
        meta.setLore(lore);
        item.setItemMeta(meta);

        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 100f, 2.0f);
        AdvancementUtil.awardAdvancement(player, new NamespacedKey(Core.INSTANCE, EnhanceAdvancement.ID), "complete");
    }

    @Deprecated
    private ItemStack doEnhance(List<String> lore, ItemStack item, boolean isPerfect){
        ItemMeta meta = item.getItemMeta();

        int count;
        try{
            count = countChar(lore.get(1), '★')+1;
        } catch(IndexOutOfBoundsException e){
            count = countChar(Objects.requireNonNull(meta.getLore()).get(1), '★')+1;
        }
        if(count > 10) return item;
        AdvancementUtil.awardAdvancement(player, new NamespacedKey(Core.INSTANCE, EnhanceAdvancement.ID), "complete");

        if(Math.random() <= (double) (10 - count) / 10 + 0.1 || isPerfect) {
            String l = "§e§l" + "★".repeat(Math.max(0, count)) +
                    "☆".repeat(Math.max(0, 10 - count));
            lore.remove(1);
            lore.add(1, l);
            updateLore(count, lore, item);

            meta.setLore(lore);
            item.setItemMeta(meta);

            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 100f, 2.0f);

            if(count == 10){
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100f, 1.5f);
                player.playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 100f, 1.0f);
                if (!item.getType().isArmor() && !additionalEnhanceAbleArmors.contains(item.getDisplayName()) && !item.getType().name().contains("PICKAXE") && !item.getType().name().contains("SHOVEL") &&
                        !item.getType().name().contains("AXE")) {
                    this.player.sendMessage(Component.text("전설의 검이 탄생했습니다!").color(TextColor.color(255, 255, 0)).decorate(TextDecoration.BOLD));
                } else if (item.getType().isArmor() || (additionalEnhanceAbleArmors.contains(item.getDisplayName()) && !item.getType().name().contains("PICKAXE") && !item.getType().name().contains("SHOVEL") &&
                        !item.getType().name().contains("AXE"))) {
                    this.player.sendMessage(Component.text("전설의 갑옷이 탄생했습니다!").color(TextColor.color(255, 255, 0)).decorate(TextDecoration.BOLD));
                } else if (item.getType().name().contains("PICKAXE") || item.getType().name().contains("SHOVEL") || item.getType().name().contains("AXE")) {
                    this.player.sendMessage(Component.text("전설의 도구가 탄생했습니다!").color(TextColor.color(255, 255, 0)).decorate(TextDecoration.BOLD));
                }

                AdvancementUtil.awardAdvancement(player, new NamespacedKey(Core.INSTANCE, EnhanceAdvancement.ID), "complete");

                meta.setLore(lore);
                item.setItemMeta(meta);
            }
        } else {
            if(Math.random() <= (double) 1 / 10){
                item = null;
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 100f, 2.0f);
            } else {
                count -= 2;
                String l = "§e§l" + "★".repeat(Math.max(0, count)) +
                        "☆".repeat(Math.max(0, 10 - count));
                lore.remove(1);
                lore.add(1, l);
                updateLore(count, lore, item);

                meta.setLore(lore);
                item.setItemMeta(meta);
                player.playSound(player.getLocation(), Sound.ENTITY_PIG_DEATH, 100f, 2.0f);
            }
        }
        return item;
    }

    private void updateLore(int count, List<String> lore, ItemStack item){
        if (item.hasDisplayName()) {
            if (!item.getType().isArmor() && !additionalEnhanceAbleArmors.contains(item.getDisplayName()) && !item.getType().name().contains("PICKAXE") && !item.getType().name().contains("SHOVEL") &&
                    !item.getType().name().contains("AXE")) {
                lore.set(3, "§b§l피해량 +" + count * 10 + "%");
            } else if (item.getType().isArmor() || (additionalEnhanceAbleArmors.contains(item.getDisplayName()) && !item.getType().name().contains("PICKAXE") && !item.getType().name().contains("SHOVEL") &&
                    !item.getType().name().contains("AXE"))) {
                lore.set(3, "§6§l피해량 감소 +" + count * 5 + "%");
            } else if ((!item.getType().isArmor() && !additionalEnhanceAbleArmors.contains(item.getDisplayName()) && item.getType().name().contains("PICKAXE")) || item.getType().name().contains("SHOVEL")) {
                lore.set(3, "§a§l채굴 속도 +" + count + "%");
            }
        } else if (!item.getType().isArmor() && !item.getType().name().contains("PICKAXE") && !item.getType().name().contains("SHOVEL") &&
                !item.getType().name().contains("AXE")) {
            lore.set(3, "§b§l피해량 +" + count * 10 + "%");
        } else if (item.getType().isArmor() && !item.getType().name().contains("PICKAXE") && !item.getType().name().contains("SHOVEL") &&
                !item.getType().name().contains("AXE")) {
            lore.set(3, "§6§l피해량 감소 +" + count * 5 + "%");
        } else if (item.getType().name().contains("PICKAXE") || item.getType().name().contains("SHOVEL") || item.getType().name().contains("AXE")) {
            lore.set(3, "§a§l채굴 속도 +" + count + "%");
        }
    }

    private static int countChar(String str, char ch) {        int count = 0;         for (int i = 0; i < str.length(); i++) {            if (str.charAt(i) == ch) {                count++;            }        }         return count;    }
}
