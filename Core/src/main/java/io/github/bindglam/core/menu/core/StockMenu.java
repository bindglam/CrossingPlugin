package io.github.bindglam.core.menu.core;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.advancements.SellStockAdvancement;
import io.github.bindglam.core.advancements.StockAdvancement;
import io.github.bindglam.core.managers.StockManager;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.core.utils.AdvancementUtil;
import io.github.bindglam.core.utils.InvUtils;
import io.github.bindglam.economy.EconomyManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class StockMenu extends Menu {
    @Override
    public String getName() {
        return "주식";
    }

    @Override
    public Page[] getPages() {
        Page[] pages = new Page[getPageCount()];

        pages[0] = new Page() {
            @Override
            public String getName() {
                return "주식 ( 페이지 1 )";
            }

            @Override
            public ItemStack[] getContent() {
                ItemStack[] content = getPageType().getBlankArray();

                //content[Y*9+X]

                for(int i = 0; i < StockManager.stocks.keySet().size(); i++){
                    if(i >= 5*9+9) continue;
                    String name = StockManager.stocks.keySet().stream().toList().get(i);
                    List<Object> data = StockManager.stocks.get(name);
                    content[i] = new AdvItemCreator(Material.PAPER).setDisplayName(name).setLore(List.of(
                            "",
                            "§6§l잔여주식: " + data.get(6) + "개",
                            "§6§l상장가: " + data.get(0) + "원",
                            "§6§l매매가: " + data.get(1) + "원",
                            "§6§l상한가: " + data.get(2) + "원",
                            "§6§l하한가: " + data.get(3) + "원",
                            "",
                            "§b§l변동기록: " + data.get(7) + "원"
                    )).getItemStack();
                }

                content[5 * 9] = new AdvItemCreator(Material.ARROW).setDisplayName("§c뒤로가기").getItemStack();
                content[5*9+1] = new AdvItemCreator(Material.GRAY_STAINED_GLASS_PANE).setDisplayName("§a다음 변동까지 남은 시간: ").setLore(List.of("§b" + StockManager.stockChangeTimer + "초")).getItemStack();
                content[5*9+8] = new AdvItemCreator(Material.ARROW).setDisplayName("§a다음으로").getItemStack();

                return content;
            }

            @Override
            public PageType getPageType() {
                return PageType.DOUBLE_CHEST;
            }

            @Override
            public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
                new StockMenu().open(view.getPlayer(), 0);
                if(itemStack == null) return;

                switch (slot) {
                    case 5 * 9:
                        view.previousPage();
                        break;

                    case 5 * 9 + 8:
                        view.nextPage();
                        break;
                }

                if (itemStack.getType() == Material.PAPER) {
                    String name = PlainTextComponentSerializer.plainText().serialize(itemStack.displayName()).replace("[", "").replace("]", "");
                    List<Object> data = StockManager.stocks.get(name);

                    if(clickType == ClickType.RIGHT && (int) data.get(6) >= 1){
                        if(EconomyManager.getAmount(view.getPlayer().getUniqueId()) < (int) data.get(1)){
                            view.getPlayer().sendMessage(Component.text("돈이 부족합니다!").color(TextColor.color(255, 0, 0)).decorate(TextDecoration.BOLD));
                            return;
                        }
                        data.set(6, (int) data.get(6) - 1);
                        EconomyManager.withdraw(view.getPlayer().getUniqueId(), (int) data.get(1));
                        view.getPlayer().getInventory().addItem(new AdvItemCreator(Material.PAPER).setDisplayName(name).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).getItemStack());
                        view.getPlayer().sendMessage(Component.text("성공적으로 주식을 구매했습니다!").color(TextColor.color(0, 255, 0)));
                        view.getPlayer().playSound(view.getPlayer().getLocation(), "entity.player.levelup", 100f, 1.5f);
                        AdvancementUtil.awardAdvancement(view.getPlayer(), new NamespacedKey(Core.INSTANCE, StockAdvancement.ID), "complete");
                    } else if(clickType == ClickType.SHIFT_RIGHT && (int) data.get(6) >= 10){
                        if(EconomyManager.getAmount(view.getPlayer().getUniqueId()) < (int) data.get(1)*10){
                            view.getPlayer().sendMessage(Component.text("돈이 부족합니다!").color(TextColor.color(255, 0, 0)).decorate(TextDecoration.BOLD));
                            return;
                        }
                        data.set(6, (int) data.get(6) - 10);
                        EconomyManager.withdraw(view.getPlayer().getUniqueId(), (int) data.get(1)*10);
                        for(int i = 0; i < 10; i++)
                            view.getPlayer().getInventory().addItem(new AdvItemCreator(Material.PAPER).setDisplayName(name).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).getItemStack());
                        view.getPlayer().sendMessage(Component.text("성공적으로 주식을 구매했습니다! x10").color(TextColor.color(0, 255, 0)));
                        view.getPlayer().playSound(view.getPlayer().getLocation(), "entity.player.levelup", 100f, 1.5f);
                        AdvancementUtil.awardAdvancement(view.getPlayer(), new NamespacedKey(Core.INSTANCE, StockAdvancement.ID), "complete");
                    } else if(clickType == ClickType.LEFT && view.getPlayer().getInventory().contains(Material.PAPER)){
                        for(ItemStack item : view.getPlayer().getInventory()){
                            if(item == null) continue;
                            if(item.getType() != Material.PAPER) continue;
                            if(!item.hasDisplayName()) continue;
                            if(PlainTextComponentSerializer.plainText().serialize(item.displayName()).replace("[", "").replace("]", "").equals(name)){
                                data.set(6, (int) data.get(6) + 1);

                                EconomyManager.deposit(view.getPlayer().getUniqueId(), (int) data.get(1));
                                InvUtils.consumeItem(view.getPlayer(), 1, item);
                                //view.getPlayer().getInventory().remove(new AdvItemCreator(Material.PAPER).setDisplayName(name).setLore(null).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 10).getItemStack());

                                view.getPlayer().sendMessage(Component.text("성공적으로 주식을 판매했습니다!").color(TextColor.color(0, 255, 0)));
                                view.getPlayer().playSound(view.getPlayer().getLocation(), "entity.player.levelup", 100f, 1.5f);
                                AdvancementUtil.awardAdvancement(view.getPlayer(), new NamespacedKey(Core.INSTANCE, SellStockAdvancement.ID), "complete");
                                break;
                            }
                        }
                    } else if(clickType == ClickType.SHIFT_LEFT && view.getPlayer().getInventory().contains(Material.PAPER, 10)){
                        for(ItemStack item : view.getPlayer().getInventory()) {
                            if(item == null) continue;
                            if (item.getType() != Material.PAPER) continue;
                            if (!item.hasDisplayName()) continue;
                            if (PlainTextComponentSerializer.plainText().serialize(item.displayName()).replace("[", "").replace("]", "").equals(name)) {
                                data.set(6, (int) data.get(6) + 10);

                                EconomyManager.deposit(view.getPlayer().getUniqueId(), (int) data.get(1)*10);
                                InvUtils.consumeItem(view.getPlayer(), 10, item);
                                //view.getPlayer().getInventory().remove(new AdvItemCreator(Material.PAPER).setDisplayName(name).setLore(null).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 10).getItemStack());

                                view.getPlayer().sendMessage(Component.text("성공적으로 주식을 판매했습니다! x10").color(TextColor.color(0, 255, 0)));
                                view.getPlayer().playSound(view.getPlayer().getLocation(), "entity.player.levelup", 100f, 1.5f);
                                AdvancementUtil.awardAdvancement(view.getPlayer(), new NamespacedKey(Core.INSTANCE, SellStockAdvancement.ID), "complete");
                            }
                        }
                    }
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
