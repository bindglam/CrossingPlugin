package io.github.bindglam.core.menu.shops;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.managers.DivingPointManager;
import io.github.bindglam.core.managers.EventCoinManager;
import io.github.bindglam.core.menu.blocks.EnhanceMenu;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.core.utils.InvUtils;
import io.github.bindglam.economy.EconomyManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public abstract class AbstractShopMenu extends Menu {
    private final String name;

    public AbstractShopMenu(String name){
        this.name = name;
    }

    public abstract LinkedHashMap<Material, Integer> getItems();
    public LinkedHashMap<ItemStack, Integer> getItemStacks(){
        return null;
    }

    public EconomyUnit getEconomyUnit() {
        return EconomyUnit.WON;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Page[] getPages() {
        Page[] pages = new Page[getPageCount()];
        for (int i = 0; i < getPageCount(); i++) {
            final int finalI = i;
            pages[i] = new Page() {
                public String getName() {
                    return AbstractShopMenu.this.name + " ( 페이지 " + (finalI+1) + " )";
                }

                public ItemStack[] getContent() {
                    ItemStack[] content = getPageType().getBlankArray();
                    int i;
                    for (i = finalI * 45; i < finalI * 45 + AbstractShopMenu.this.getItems().keySet().size() &&
                            i < (finalI + 1) * 45 && AbstractShopMenu.this.getItems().keySet().stream().toList().size() > i; i++) {
                        Material material = AbstractShopMenu.this.getItems().keySet().stream().toList().get(i);
                        int cost = AbstractShopMenu.this.getItems().get(material);
                        if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.WON && !material.name().contains("SMITHING_TEMPLATE")) {
                            content[i - finalI * 45] = (new AdvItemCreator(material)).setLore(List.of("", "§b§l가격 : " + cost + AbstractShopMenu.this.getEconomyUnit().getName(), "", "§e§l클릭으로 구매", "§e§l웅크리기 + 클릭으로 64개씩 구매", "§a§l우클릭으로 모두 판매"))
                                    .addPersistentData("cost", cost).getItemStack();
                        } else {
                            content[i - finalI * 45] = (new AdvItemCreator(material)).setLore(List.of("", "§b§l가격 : " + cost + AbstractShopMenu.this.getEconomyUnit().getName(), "", "§e§l클릭으로 구매", "§e§l웅크리기 + 클릭으로 64개씩 구매"))
                                    .addPersistentData("cost", cost).getItemStack();
                        }
                    }
                    if (AbstractShopMenu.this.getItemStacks() != null)
                        for (i = AbstractShopMenu.this.getItems().keySet().size() + finalI * 45; i < finalI * 45 + AbstractShopMenu.this.getItems().keySet().size() + AbstractShopMenu.this.getItemStacks().size() &&
                                i < (finalI + 1) * 45 && AbstractShopMenu.this.getItemStacks().keySet().stream().toList().size() > i - AbstractShopMenu.this.getItems().keySet().size(); i++) {
                            ItemStack itemStack = AbstractShopMenu.this.getItemStacks().keySet().stream().toList().get(i - AbstractShopMenu.this.getItems().keySet().size());
                            int cost = ((Integer)AbstractShopMenu.this.getItemStacks().get(itemStack)).intValue();
                            if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.WON && itemStack.getType() != Material.ENCHANTED_BOOK && !itemStack.getType().name().contains("SMITHING_TEMPLATE")) {
                                content[i - finalI * 45] = (new AdvItemCreator(itemStack)).setLore(List.of("", "§b§l가격 : " + cost + AbstractShopMenu.this.getEconomyUnit().getName(), "", "§e§l클릭으로 구매", "§e§l웅크리기 + 클릭으로 64개씩 구매", "§a§l우클릭으로 모두 판매"))
                                        .addPersistentData("cost", cost)
                                        .addPersistentData("is-itemstack", true)
                                        .getItemStack();
                            } else {
                                content[i - finalI * 45] = (new AdvItemCreator(itemStack)).setLore(List.of("", "§b§l가격 : " + cost + AbstractShopMenu.this.getEconomyUnit().getName(), "", "§e§l클릭으로 구매", "§e§l웅크리기 + 클릭으로 64개씩 구매"))
                                        .addPersistentData("cost", cost)
                                        .addPersistentData("is-itemstack", true)
                                        .getItemStack();
                            }
                        }
                    content[45] = (new AdvItemCreator(Material.ARROW)).setDisplayName("§c뒤로가기").getItemStack();
                    content[53] = (new AdvItemCreator(Material.ARROW)).setDisplayName("§a다음으로").getItemStack();
                    return content;
                }

                public PageType getPageType() {
                    return PageType.DOUBLE_CHEST;
                }

                public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
                    if (itemStack == null)
                        return;
                    ItemMeta meta = itemStack.getItemMeta();
                    PersistentDataContainer container = meta.getPersistentDataContainer();
                    switch (slot) {
                        case 45:
                            view.previousPage();
                            return;
                        case 53:
                            view.nextPage();
                            return;
                    }
                    int cost = Objects.requireNonNull(container.get(new NamespacedKey(Core.INSTANCE, "cost"), PersistentDataType.INTEGER));
                    if (clickType == ClickType.LEFT) {
                        if (!InvUtils.hasInventorySpace(view.getPlayer().getInventory())) {
                            view.getPlayer().sendMessage((Component.text("인벤토리에 공간이 없습니다!").color(TextColor.color(255, 0, 0))).decorate(TextDecoration.BOLD));
                            return;
                        }
                        if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.WON) {
                            if (cost > EconomyManager.getAmount(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage((Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0))).decorate(TextDecoration.BOLD));
                                return;
                            }
                            EconomyManager.withdraw(view.getPlayer().getUniqueId(), cost);
                        } else if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.POINT) {
                            if (cost > DivingPointManager.divingPoints.get(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage(Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0)).decorate(TextDecoration.BOLD));
                                return;
                            }
                            DivingPointManager.divingPoints.put(view.getPlayer().getUniqueId(), DivingPointManager.divingPoints.get(view.getPlayer().getUniqueId()) - cost);
                        } else if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.COIN) {
                            if (cost > ((Integer)EventCoinManager.eventCoins.get(view.getPlayer().getUniqueId())).intValue()) {
                                view.getPlayer().sendMessage(Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0)).decorate(TextDecoration.BOLD));
                                return;
                            }
                            EventCoinManager.eventCoins.put(view.getPlayer().getUniqueId(), Integer.valueOf(((Integer)EventCoinManager.eventCoins.get(view.getPlayer().getUniqueId())).intValue() - cost));
                        }
                        view.getPlayer().sendMessage(((TextComponent)Component.text("성공적으로 구매했습니다!").color(TextColor.color(0, 255, 0))).decorate(TextDecoration.BOLD));
                                AbstractShopMenu.this.giveItem(container, itemStack, meta, view.getPlayer());
                    } else if (clickType == ClickType.SHIFT_LEFT) {
                        if (!InvUtils.hasInventorySpace(view.getPlayer().getInventory())) {
                            view.getPlayer().sendMessage(Component.text("인벤토리에 공간이 없습니다!").color(TextColor.color(255, 0, 0)).decorate(TextDecoration.BOLD));
                            return;
                        }
                        if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.WON) {
                            if (cost * 64 > EconomyManager.getAmount(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage(Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0)).decorate(TextDecoration.BOLD));
                                return;
                            }
                            EconomyManager.withdraw(view.getPlayer().getUniqueId(), cost * 64);
                        } else if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.POINT) {
                            if (cost * 64 > DivingPointManager.divingPoints.get(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage(Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0)).decorate(TextDecoration.BOLD));
                                return;
                            }
                            DivingPointManager.divingPoints.put(view.getPlayer().getUniqueId(), DivingPointManager.divingPoints.get(view.getPlayer().getUniqueId()).intValue() - cost * 64);
                        } else if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.COIN) {
                            if (cost * 64 > ((Integer)EventCoinManager.eventCoins.get(view.getPlayer().getUniqueId())).intValue()) {
                                view.getPlayer().sendMessage(((TextComponent)Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0))).decorate(TextDecoration.BOLD));
                                return;
                            }
                            EventCoinManager.eventCoins.put(view.getPlayer().getUniqueId(), Integer.valueOf(((Integer)EventCoinManager.eventCoins.get(view.getPlayer().getUniqueId())).intValue() - cost * 64));
                        }
                        view.getPlayer().sendMessage(Component.text("성공적으로 구매했습니다!").color(TextColor.color(0, 255, 0)).decorate(TextDecoration.BOLD));
                        for (int i = 0; i < 64; i++)
                            AbstractShopMenu.this.giveItem(container, itemStack, meta, view.getPlayer());
                    } else if (clickType == ClickType.RIGHT && AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.WON && itemStack.getType() != Material.ENCHANTED_BOOK && !itemStack.getType().name().contains("SMITHING_TEMPLATE")) {
                        for (int i = 0; i < view.getPlayer().getInventory().getSize(); i++) {
                            ItemStack invItem = view.getPlayer().getInventory().getItem(i);
                            if (invItem == null)
                                continue;
                            if (invItem.hasDisplayName()) {
                                if (EnhanceMenu.additionalEnhanceAbleWeapons.contains(invItem.getDisplayName()) || EnhanceMenu.additionalEnhanceAbleArmors.contains(invItem.getDisplayName()))
                                    continue;
                                if (itemStack.getType() == invItem.getType())
                                    view.getPlayer().getInventory().setItem(i, new ItemStack(Material.AIR));
                            } else if (itemStack.getType() == invItem.getType()) {
                                view.getPlayer().getInventory().setItem(i, new ItemStack(Material.AIR));
                            }
                            if (invItem.getType() == itemStack.getType() && invItem.getType() == Material.ENCHANTED_BOOK) {
                                EnchantmentStorageMeta bookmeta = (EnchantmentStorageMeta)invItem.getItemMeta();
                                if (bookmeta.hasStoredEnchant(((EnchantmentStorageMeta)itemStack.getItemMeta()).getStoredEnchants().keySet().stream().toList().get(0))) {
                                    EconomyManager.deposit(view.getPlayer().getUniqueId(), cost * invItem.getAmount());
                                    view.getPlayer().sendMessage(Component.text("성공적으로 판매했습니다!").color(TextColor.color(0, 255, 0)).decorate(TextDecoration.BOLD));
                                }
                            } else if (invItem.getType() == itemStack.getType() && invItem.getType() != Material.ENCHANTED_BOOK) {
                                EconomyManager.deposit(view.getPlayer().getUniqueId(), cost * invItem.getAmount());
                                view.getPlayer().sendMessage(Component.text("성공적으로 판매했습니다!").color(TextColor.color(0, 255, 0)).decorate(TextDecoration.BOLD));
                            }
                            continue;
                        }
                    }
                }
            };
        }
        return pages;
    }

    @Override
    public int getPageCount() {
        if (getItemStacks() == null)
            return getItems().size() / (PageType.DOUBLE_CHEST.getRow() - 1) * 9 + 1;
        return (getItems().size() + getItemStacks().size()) / (PageType.DOUBLE_CHEST.getRow() - 1) * 9 + 1;
    }

    private void giveItem(PersistentDataContainer container, ItemStack itemStack, ItemMeta meta, Player player) {
        if (container.has(new NamespacedKey(Core.INSTANCE, "is-itemstack"))) {
            ItemStack clone = new ItemStack(itemStack);
            ItemMeta cloneMeta = clone.getItemMeta();
            List<String> lore = new ArrayList<>(Objects.requireNonNull(meta.getLore()));
            lore.remove(lore.size() - 1);
            lore.remove(lore.size() - 1);
            lore.remove(lore.size() - 1);
            lore.remove(lore.size() - 1);
            lore.remove(lore.size() - 1);
            if (getEconomyUnit() == EconomyUnit.WON && itemStack.getType() != Material.ENCHANTED_BOOK && !itemStack.getType().name().contains("SMITHING_TEMPLATE"))
                lore.remove(lore.size() - 1);
            cloneMeta.setLore(lore);
            clone.setItemMeta(cloneMeta);
            player.getInventory().addItem(clone);
        } else {
            player.getInventory().addItem(new ItemStack(itemStack.getType()));
        }
    }

    public enum EconomyUnit {
        WON("원"),
        POINT("포인트"),
        COIN("코인");

        final String name;

        EconomyUnit(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
