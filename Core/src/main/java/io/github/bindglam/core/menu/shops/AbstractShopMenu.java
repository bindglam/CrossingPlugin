package io.github.bindglam.core.menu.shops;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.managers.DivingPointManager;
import io.github.bindglam.core.managers.DonatePointManager;
import io.github.bindglam.core.managers.EventCoinManager;
import io.github.bindglam.core.menu.blocks.EnhanceMenu;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.core.utils.InvUtils;
import io.github.bindglam.economy.EconomyManager;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public abstract class AbstractShopMenu extends Menu {
    private static final List<Material> SELL_BLACKLIST = List.of(Material.ENCHANTED_BOOK, Material.ANVIL, Material.GRINDSTONE, Material.ENCHANTING_TABLE, Material.WITHER_ROSE, Material.SNOWBALL,
            Material.SCAFFOLDING);
    private static final List<String> SELL_BLACKLIST_NAME = List.of("SMITHING_TEMPLATE");
    private final String name;

    public AbstractShopMenu(String name){
        this.name = name;
    }

    public LinkedHashMap<Material, Double> getItems(){
        return null;
    }

    public abstract LinkedHashMap<ItemStack, Double> getItemStacks();

    private LinkedHashMap<ItemStack, Double> getAllItemStacks(){
        LinkedHashMap<ItemStack, Double> itemStacks = new LinkedHashMap<>();
        for(Material material : getItems().keySet()){
            itemStacks.put(new ItemStack(material), getItems().get(material));
        }
        itemStacks.putAll(getItemStacks());
        return itemStacks;
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

                    for (int k = finalI * 45; k < finalI * 45 + getAllItemStacks().keySet().size() &&
                            k < (finalI + 1) * 45 && getAllItemStacks().keySet().stream().toList().size() > k; k++) {
                        ItemStack itemStack = getAllItemStacks().keySet().stream().toList().get(k);
                        double cost = getAllItemStacks().get(itemStack);
                        if(getEconomyUnit() == EconomyUnit.WON && !SELL_BLACKLIST.contains(itemStack.getType()) && !containsInBlacklist(itemStack.getDisplayName())
                                && !containsInBlacklist(itemStack.getType().name())) {
                            content[k - finalI * 45] = (new AdvItemCreator(itemStack)).addLore(List.of("", "§b§l가격 : " + String.format("%.1f", cost) + AbstractShopMenu.this.getEconomyUnit().getName(), "§b§l판매 가격 : " + String.format("%.1f", cost / 2) + AbstractShopMenu.this.getEconomyUnit().getName(), "",
                                            "§e§l클릭으로 구매", "§e§l웅크리기 + 클릭으로 64개씩 구매", "§a§l우클릭으로 모두 판매"))
                                    .addPersistentData("cost", cost)
                                    .addPersistentData("is-itemstack", true)
                                    .getItemStack();
                        } else {
                            content[k - finalI * 45] = (new AdvItemCreator(itemStack)).addLore(List.of("", "§b§l가격 : " + String.format("%.1f", cost) + AbstractShopMenu.this.getEconomyUnit().getName(), "",
                                            "§e§l클릭으로 구매", "§e§l웅크리기 + 클릭으로 64개씩 구매"))
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
                    double cost = Objects.requireNonNull(container.get(new NamespacedKey(Core.INSTANCE, "cost"), PersistentDataType.DOUBLE));
                    if (clickType == ClickType.LEFT) {
                        if (!InvUtils.hasInventorySpace(view.getPlayer().getInventory())) {
                            view.getPlayer().sendMessage((Component.text("인벤토리에 공간이 없습니다!").color(TextColor.color(255, 0, 0))));
                            return;
                        }
                        if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.WON) {
                            if (cost > EconomyManager.getAmount(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage((Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0))));
                                return;
                            }
                            EconomyManager.withdraw(view.getPlayer().getUniqueId(), cost);
                        } else if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.POINT) {
                            if (cost > DivingPointManager.divingPoints.get(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage(Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0)));
                                return;
                            }
                            DivingPointManager.divingPoints.put(view.getPlayer().getUniqueId(), (int) (DivingPointManager.divingPoints.get(view.getPlayer().getUniqueId()) - cost));
                        } else if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.COIN) {
                            if (cost > EventCoinManager.eventCoins.get(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage(Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0)));
                                return;
                            }
                            EventCoinManager.eventCoins.put(view.getPlayer().getUniqueId(), (int) (EventCoinManager.eventCoins.get(view.getPlayer().getUniqueId()) - cost));
                        } else if (AbstractShopMenu.this.getEconomyUnit() == EconomyUnit.MILEAGE) {
                            if (cost > DonatePointManager.donatePoints.get(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage(Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0)));
                                return;
                            }
                            DonatePointManager.donatePoints.put(view.getPlayer().getUniqueId(), (int) (DonatePointManager.donatePoints.get(view.getPlayer().getUniqueId()) - cost));
                        }
                        view.getPlayer().sendMessage(Component.text("성공적으로 구매했습니다!").color(TextColor.color(0, 255, 0)));
                                AbstractShopMenu.this.giveItem(itemStack, meta, view.getPlayer());
                    } else if (clickType == ClickType.SHIFT_LEFT) {
                        if (!InvUtils.hasInventorySpace(view.getPlayer().getInventory())) {
                            view.getPlayer().sendMessage(Component.text("인벤토리에 공간이 없습니다!").color(TextColor.color(255, 0, 0)));
                            return;
                        }
                        if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.WON) {
                            if (cost * 64 > EconomyManager.getAmount(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage(Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0)));
                                return;
                            }
                            EconomyManager.withdraw(view.getPlayer().getUniqueId(), cost * 64);
                        } else if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.POINT) {
                            if (cost * 64 > DivingPointManager.divingPoints.get(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage(Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0)));
                                return;
                            }
                            DivingPointManager.divingPoints.put(view.getPlayer().getUniqueId(), (int) (DivingPointManager.divingPoints.get(view.getPlayer().getUniqueId()) - cost * 64));
                        } else if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.COIN) {
                            if (cost * 64 > EventCoinManager.eventCoins.get(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage(Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0)));
                                return;
                            }
                            EventCoinManager.eventCoins.put(view.getPlayer().getUniqueId(), (int) (EventCoinManager.eventCoins.get(view.getPlayer().getUniqueId()) - cost * 64));
                        } else if (AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.MILEAGE) {
                            if (cost * 64 > DonatePointManager.donatePoints.get(view.getPlayer().getUniqueId())) {
                                view.getPlayer().sendMessage(Component.text("가격이 소지금보다 높습니다!").color(TextColor.color(255, 0, 0)));
                                return;
                            }
                            DonatePointManager.donatePoints.put(view.getPlayer().getUniqueId(), (int) (DonatePointManager.donatePoints.get(view.getPlayer().getUniqueId()) - cost * 64));
                        }
                        view.getPlayer().sendMessage(Component.text("성공적으로 구매했습니다!").color(TextColor.color(0, 255, 0)));
                        for (int i = 0; i < 64; i++)
                            AbstractShopMenu.this.giveItem(itemStack, meta, view.getPlayer());
                    } else if (clickType == ClickType.RIGHT && AbstractShopMenu.this.getEconomyUnit() == AbstractShopMenu.EconomyUnit.WON && !SELL_BLACKLIST.contains(itemStack.getType()) && !containsInBlacklist(itemStack.getType().name())) {
                        for (int i = 0; i < view.getPlayer().getInventory().getSize(); i++) {
                            ItemStack invItem = view.getPlayer().getInventory().getItem(i);
                            if (invItem == null)
                                continue;
                            if (invItem.hasDisplayName()) {
                                if (EnhanceMenu.additionalEnhanceAbleWeapons.contains(invItem.getDisplayName()) || EnhanceMenu.additionalEnhanceAbleArmors.contains(invItem.getDisplayName()))
                                    continue;
                            }
                            if(invItem.hasDisplayName() && container.has(new NamespacedKey(Core.INSTANCE, "is-itemstack"))) {
                                if (itemStack.getType() == invItem.getType() && itemStack.getDisplayName().equals(invItem.getDisplayName())) {
                                    EconomyManager.deposit(view.getPlayer().getUniqueId(), cost / 2 * invItem.getAmount());
                                    view.getPlayer().getInventory().setItem(i, new ItemStack(Material.AIR));
                                    view.getPlayer().sendMessage(Component.text("성공적으로 판매하였습니다!").color(NamedTextColor.GREEN));
                                }
                            } else {
                                if (itemStack.getType() == invItem.getType()) {
                                    EconomyManager.deposit(view.getPlayer().getUniqueId(), cost / 2 * invItem.getAmount());
                                    view.getPlayer().getInventory().setItem(i, new ItemStack(Material.AIR));
                                    view.getPlayer().sendMessage(Component.text("성공적으로 판매하였습니다!").color(NamedTextColor.GREEN));
                                }
                            }
                        }
                    }
                }
            };
        }
        return pages;
    }

    @Override
    public int getPageCount() {
        return getAllItemStacks().size() / ((PageType.DOUBLE_CHEST.getRow() - 1) * 9) + 1;
    }

    private void giveItem(ItemStack itemStack, ItemMeta meta, Player player) {
        ItemStack clone = new ItemStack(itemStack);
        ItemMeta cloneMeta = clone.getItemMeta();
        List<String> lore = new ArrayList<>(Objects.requireNonNull(meta.getLore()));
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        if (getEconomyUnit() == EconomyUnit.WON && !SELL_BLACKLIST.contains(itemStack.getType()) && !containsInBlacklist(itemStack.getType().name())) {
            if(!lore.isEmpty()) {
                lore.remove(lore.size() - 1);
                lore.remove(lore.size() - 1);
            }
        }
        cloneMeta.setLore(lore);
        clone.setItemMeta(cloneMeta);
        player.getInventory().addItem(clone);
    }

    private boolean containsInBlacklist(String name) {
        for (String blacklist : SELL_BLACKLIST_NAME) {
            if (name.contains(blacklist)) {
                return true;
            }
        }
        return false;
    }

    @Getter
    public enum EconomyUnit {
        WON("원"),
        POINT("포인트"),
        COIN("코인"),
        MILEAGE("마일리지");

        final String name;

        EconomyUnit(String name) {
            this.name = name;
        }
    }
}
