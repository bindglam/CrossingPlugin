package io.github.bindglam.core.menu.npc;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import fr.dwightstudio.dsmapi.utils.ItemCreator;
import io.github.bindglam.core.managers.BankManager;
import io.github.bindglam.economy.EconomyManager;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class BankerMenu extends Menu implements Listener {
    private static final HashMap<String, Integer> signingPlayers = new HashMap<>();
    private Player player;

    public BankerMenu(Player viewer){
        if(viewer != null) {
            player = viewer;
        }
    }

    @Override
    public String getName() {
        return "은행원";
    }

    @Override
    public Page[] getPages() {
        Page[] pages = new Page[getPageCount()];

        pages[0] = new Page() {
            @Override
            public String getName() {
                return "은행원";
            }

            @Override
            @Deprecated
            public ItemStack[] getContent() {
                ItemStack[][] content = getPageType().getBlank2DArray();

                content[1][1] = new ItemCreator(Material.BOOK).setName("§e§l통장 §a개설").getItem();
                content[1][3] = new ItemCreator(Material.GOLD_INGOT).setName("§e§l통장§7으로 §6입금").getItem();
                ItemMeta meta = content[1][3].getItemMeta();
                meta.setLore(List.of("현재 통장 소지금 : " + String.format("%.1f", EconomyManager.getBankAmount(player.getUniqueId())) + "원"));
                content[1][3].setItemMeta(meta);
                content[1][5] = new ItemCreator(Material.FEATHER).setName("§e§l통장§7에서 §6출금").getItem();
                meta.setDisplayName(content[1][5].getItemMeta().getDisplayName());
                content[1][5].setItemMeta(meta);
                content[1][7] = new ItemCreator(Material.ENDER_CHEST).setName("§b§l개인 공간").getItem();

                return getPageType().flatten(content);
            }

            @Override
            public PageType getPageType() {
                return PageType.CHEST;
            }

            @Override
            public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
                if(itemStack == null) return;
                if(player == null) return;
                signingPlayers.remove(player.getName());

                switch (itemStack.getType()){
                    case ENDER_CHEST:
                        view.getPlayer().openInventory(view.getPlayer().getEnderChest());
                        view.getPlayer().playSound(view.getPlayer().getLocation(), "block.chest.open", 100f, 1.2f);
                        break;

                    case BOOK:
                        view.getPlayer().closeInventory();
                        if(BankManager.hasBankPlayers.contains(view.getPlayer().getUniqueId())){
                            view.getPlayer().sendMessage("§c§l현재 통장을 가지고 있습니다.");
                            break;
                        }

                        if(EconomyManager.getAmount(view.getPlayer().getUniqueId()) >= 1000){
                            EconomyManager.withdraw(view.getPlayer().getUniqueId(), 1000);
                            BankManager.hasBankPlayers.add(view.getPlayer().getUniqueId());
                            view.getPlayer().sendMessage("§a§l통장을 개설하였습니다!");

                            Firework fw = (Firework) view.getPlayer().getWorld().spawnEntity(view.getPlayer().getLocation(), EntityType.FIREWORK);
                            FireworkMeta fwm = fw.getFireworkMeta();

                            fwm.setPower(2);
                            fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());

                            fw.setFireworkMeta(fwm);
                        } else {
                            view.getPlayer().sendMessage("§c§l돈이 부족합니다.");
                        }
                        break;

                    case GOLD_INGOT:
                        view.getPlayer().closeInventory();
                        if(!BankManager.hasBankPlayers.contains(view.getPlayer().getUniqueId())){
                            view.getPlayer().sendMessage("§c§l현재 통장을 가지고 있지 않습니다.");
                            break;
                        }

                        view.getPlayer().sendMessage(Component.text("채팅에 입금할 액수를 입력해주세요!").color(TextColor.color(0, 255, 0)));
                        signingPlayers.put(view.getPlayer().getName(), 1);
                        break;

                    case FEATHER:
                        view.getPlayer().closeInventory();
                        if(!BankManager.hasBankPlayers.contains(view.getPlayer().getUniqueId())){
                            view.getPlayer().sendMessage("§c§l현재 통장을 가지고 있지 않습니다.");
                            break;
                        }

                        view.getPlayer().sendMessage(Component.text("채팅에 출금할 액수를 입력해주세요!").color(TextColor.color(0, 255, 0)));
                        signingPlayers.put(view.getPlayer().getName(), 2);
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

    @EventHandler
    public void onChat(AsyncChatEvent event){
        if(!signingPlayers.containsKey(event.getPlayer().getName())) return;
        try{
            event.setCancelled(true);
            int amount = Integer.parseInt(PlainTextComponentSerializer.plainText().serialize(event.message()));
            if(signingPlayers.get(event.getPlayer().getName()) == 2) {
                if (EconomyManager.getBankAmount(event.getPlayer().getUniqueId()) < amount) {
                    event.getPlayer().sendMessage("§c§l통장 금액보다 입력한 액수가 더 많습니다.");
                    signingPlayers.remove(event.getPlayer().getName());
                    return;
                }

                EconomyManager.withdrawBank(event.getPlayer().getUniqueId(), amount);
                EconomyManager.deposit(event.getPlayer().getUniqueId(), amount);
                event.getPlayer().sendMessage("§a성공적으로 은행계좌에서 출금하였습니다! ( 현재 은행계좌 금액 : " + EconomyManager.getBankAmount(event.getPlayer().getUniqueId()) + "원 )");
            } else {
                if (EconomyManager.getAmount(event.getPlayer().getUniqueId()) < amount) {
                    player.sendMessage("§c§l소지금보다 입력한 액수가 더 많습니다.");
                    signingPlayers.remove(event.getPlayer().getName());
                    return;
                }

                EconomyManager.withdraw(event.getPlayer().getUniqueId(), amount);
                EconomyManager.depositBank(event.getPlayer().getUniqueId(), amount);
                event.getPlayer().sendMessage("§a성공적으로 은행계좌에서 입금하였습니다! ( 현재 은행계좌 금액 : " + EconomyManager.getBankAmount(event.getPlayer().getUniqueId()) + "원 )");
            }
            signingPlayers.remove(event.getPlayer().getName());
        } catch (NumberFormatException e){
            event.getPlayer().sendMessage("§4숫자가 아닙니다!");
        }
    }
}
