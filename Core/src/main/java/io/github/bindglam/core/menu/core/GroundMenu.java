package io.github.bindglam.core.menu.core;

import fr.dwightstudio.dsmapi.Menu;
import fr.dwightstudio.dsmapi.MenuView;
import fr.dwightstudio.dsmapi.pages.Page;
import fr.dwightstudio.dsmapi.pages.PageType;
import fr.dwightstudio.dsmapi.utils.ItemCreator;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.ground.GroundManager;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GroundMenu extends Menu implements Listener {
    public static final List<UUID> chattingGroundNumber = new ArrayList<>();
    public static final List<UUID> chattingAddGrounders = new ArrayList<>();
    public static final List<UUID> chattingRemoveGround = new ArrayList<>();
    public static final List<UUID> chattingRemoveGrounders = new ArrayList<>();
    public static final HashMap<UUID, Location> inputGroundLoc = new HashMap<>();

    @Override
    public String getName() {
        return "땅";
    }

    @Override
    public Page[] getPages() {
        Page[] pages = new Page[getPageCount()];

        pages[0] = new Page() {
            @Override
            public String getName() {
                return "땅";
            }

            @Override
            public ItemStack[] getContent() {
                ItemStack[][] content = getPageType().getBlank2DArray();

                for(int y = 0; y < PageType.DOUBLE_CHEST.getRow(); y++){
                    for(int x = 0; x < 9; x++){
                        content[y][x] = new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName("").getItem();
                    }
                }

                content[1][1] = new AdvItemCreator(Material.GRASS_BLOCK).setDisplayName("§e§l땅 §a생성")
                        .setLore(Component.text("현재 위치에 땅을 생성합니다").color(TextColor.color(100, 100, 100)))
                        .getItemStack();
                content[2][1] = new AdvItemCreator(Material.PLAYER_HEAD).setDisplayName("§b§l땅원 §a추가")
                        .setLore(Component.text("땅원을 추가합니다").color(TextColor.color(100, 100, 100)))
                        .getItemStack();

                content[2][4] = new AdvItemCreator(Material.BOOK).setDisplayName("§f§l땅 목록")
                        .setLore(Component.text("땅 번호와 땅의 위치를 알 수 있습니다").color(TextColor.color(100, 100, 100)))
                        .getItemStack();
                content[3][4] = new AdvItemCreator(Material.PAPER).setDisplayName("§f§l땅 정보")
                        .setLore(Component.text("땅에 관한 정보를 알 수 있습니다").color(TextColor.color(100, 100, 100)))
                        .getItemStack();

                content[1][7] = new AdvItemCreator(Material.BARRIER).setDisplayName("§e§l땅 §c삭제")
                        .setLore(Component.text("땅을 삭제합니다").color(TextColor.color(100, 100, 100)))
                        .getItemStack();
                content[2][7] = new AdvItemCreator(Material.STRUCTURE_VOID).setDisplayName("§b§l땅원 §c삭제")
                        .setLore(Component.text("땅원을 삭제합니다").color(TextColor.color(100, 100, 100)))
                        .getItemStack();

                return getPageType().flatten(content);
            }

            @Override
            public PageType getPageType() {
                return PageType.DOUBLE_CHEST;
            }

            @Override
            public void onClick(MenuView view, ClickType clickType, int slot, ItemStack itemStack) {
                if(itemStack == null) return;
                if(chattingGroundNumber.contains(view.getPlayer().getUniqueId()) || chattingAddGrounders.contains(view.getPlayer().getUniqueId())) return;

                switch (itemStack.getType()){
                    case GRASS_BLOCK:
                        view.getPlayer().closeInventory();
                        Bukkit.dispatchCommand(view.getPlayer(), "땅 생성");
                        break;

                    case PLAYER_HEAD:
                        view.getPlayer().closeInventory();
                        chattingGroundNumber.add(view.getPlayer().getUniqueId());
                        chattingAddGrounders.add(view.getPlayer().getUniqueId());
                        view.getPlayer().sendMessage(Component.text("채팅에 땅 번호를 입력해주세요!").color(TextColor.color(0, 255, 0)).decorate(TextDecoration.BOLD));
                        break;

                    case BOOK:
                        view.getPlayer().closeInventory();
                        Bukkit.dispatchCommand(view.getPlayer(), "땅 목록");
                        break;

                    case PAPER:
                        view.getPlayer().closeInventory();
                        Bukkit.dispatchCommand(view.getPlayer(), "땅 정보");
                        break;

                    case BARRIER:
                        view.getPlayer().closeInventory();
                        chattingGroundNumber.add(view.getPlayer().getUniqueId());
                        chattingRemoveGround.add(view.getPlayer().getUniqueId());
                        view.getPlayer().sendMessage(Component.text("채팅에 땅 번호를 입력해주세요!").color(TextColor.color(0, 255, 0)).decorate(TextDecoration.BOLD));
                        break;

                    case STRUCTURE_VOID:
                        view.getPlayer().closeInventory();
                        chattingGroundNumber.add(view.getPlayer().getUniqueId());
                        chattingRemoveGrounders.add(view.getPlayer().getUniqueId());
                        view.getPlayer().sendMessage(Component.text("채팅에 땅 번호를 입력해주세요!").color(TextColor.color(0, 255, 0)).decorate(TextDecoration.BOLD));
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
        Player player = event.getPlayer();
        String message = PlainTextComponentSerializer.plainText().serialize(event.message()).replace("[", "").replace("]", "");
        int number;
        Location loc = null;
        if(chattingGroundNumber.contains(player.getUniqueId())) {
            try {
                number = Integer.parseInt(message);
            } catch (NumberFormatException e) {
                player.sendMessage(Component.text("숫자가 아닙니다!").color(TextColor.color(255, 0, 0)).decorate(TextDecoration.BOLD));
                return;
            }

            loc = GroundManager.getGroundLoc(player, number);
            if (loc == null) return;
            inputGroundLoc.put(player.getUniqueId(), loc);
        }

        if((chattingAddGrounders.contains(player.getUniqueId()) || chattingRemoveGrounders.contains(player.getUniqueId())) && chattingGroundNumber.contains(player.getUniqueId())){
            event.setCancelled(true);
            // 땅원 작업시, 땅 번호 확인
            chattingGroundNumber.remove(player.getUniqueId());
            player.sendMessage(Component.text("땅원의 이름을 입력해주세요!").color(TextColor.color(0, 255, 0)).decorate(TextDecoration.BOLD));
        } else if(chattingAddGrounders.contains(player.getUniqueId()) && !chattingGroundNumber.contains(player.getUniqueId())){
            event.setCancelled(true);
            //땅원 추가
            chattingAddGrounders.remove(player.getUniqueId());

            OfflinePlayer grounderOffline = Bukkit.getOfflinePlayer(message);
            if(GroundManager.grounders.get(inputGroundLoc.get(player.getUniqueId())).contains(grounderOffline.getUniqueId())){
                player.sendMessage("§c§l이미 그 플레이어는 땅원입니다.");
                return;
            }
            Player grounder = Bukkit.getPlayer(message);
            if(grounder != null) grounder.sendMessage("당신은 " + player.getName() + "님의 땅의 땅원으로 초대받았습니다!");

            GroundManager.grounders.get(inputGroundLoc.get(player.getUniqueId())).add(grounderOffline.getUniqueId());
            player.sendMessage("§a§l성공적으로 땅원을 초대했습니다!");
        } else if(chattingRemoveGround.contains(player.getUniqueId()) && chattingGroundNumber.contains(player.getUniqueId())){
            event.setCancelled(true);
            //땅 삭제
            chattingRemoveGround.remove(player.getUniqueId());
            chattingGroundNumber.remove(player.getUniqueId());

            GroundManager.grounds.remove(inputGroundLoc.get(player.getUniqueId()));
            GroundManager.grounders.remove(inputGroundLoc.get(player.getUniqueId()));
            GroundManager.canMakeGround.put(player.getUniqueId(), GroundManager.canMakeGround.get(player.getUniqueId()) + 1);
            player.sendMessage("§a§l성공적으로 땅을 삭제했습니다.");
        } else if(chattingRemoveGrounders.contains(player.getUniqueId()) && !chattingGroundNumber.contains(player.getUniqueId())){
            event.setCancelled(true);
            //땅원 삭제
            chattingRemoveGrounders.remove(player.getUniqueId());

            OfflinePlayer grounderOffline = Bukkit.getOfflinePlayer(message);
            if(!GroundManager.grounders.get(inputGroundLoc.get(player.getUniqueId())).contains(grounderOffline.getUniqueId())){
                player.sendMessage("§c§l그 플레이어는 땅원이 아닙니다.");
                return;
            }

            GroundManager.grounders.get(inputGroundLoc.get(player.getUniqueId())).remove(grounderOffline.getUniqueId());
            player.sendMessage("§a§l성공적으로 땅원을 삭제했습니다!");
        }
    }
}
