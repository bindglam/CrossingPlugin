package io.github.bindglam.core.listeners;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import fr.mrmicky.fastboard.FastBoard;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.managers.*;
import io.github.bindglam.core.menu.blocks.EnhanceMenu;
import io.github.bindglam.economy.EconomyManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServerTickListener implements Listener {
    private static final HashMap<String, Integer> ticks = new HashMap<>(){{ put("floorcleaner", 0); put("Announcement", 0); }};
    private static int announcementI = 0, floorCleanerI = 0;

    public static final List<Object> eventQuestionData = new ArrayList<>();
    public static final ConcurrentHashMap<UUID, Integer> pvpTimes = new ConcurrentHashMap<>();
    public static final List<UUID> pvpInGrounds = new ArrayList<>();

    public static void init(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.INSTANCE, () -> {
            if (ticks.get("Announcement") >= 120 * 20)
                sendAnnouncement();

            if (ticks.get("floorcleaner") >= 5 * 60 * 20 && floorCleanerI == 3){
                cleanFloor();
                floorCleanerI = 0;
            } else if(ticks.get("floorcleaner") >= 4*60*20 + 50*20 && floorCleanerI == 2) {
                Bukkit.getOnlinePlayers().forEach((player) -> player.sendMessage("§f[ §b§l바닥§f§l청소 §f] §710초뒤 바닥에 떨어진 모든 아이템들을 삭제합니다."));
                floorCleanerI++;
            } else if(ticks.get("floorcleaner") >= 4*60*20 && floorCleanerI == 1) {
                Bukkit.getOnlinePlayers().forEach((player) -> player.sendMessage("§f[ §b§l바닥§f§l청소 §f] §71분뒤 바닥에 떨어진 모든 아이템들을 삭제합니다."));
                floorCleanerI++;
            } else if(ticks.get("floorcleaner") >= 2*60*20 && floorCleanerI == 0) {
                Bukkit.getOnlinePlayers().forEach((player) -> player.sendMessage("§f[ §b§l바닥§f§l청소 §f] §73분뒤 바닥에 떨어진 모든 아이템들을 삭제합니다."));
                floorCleanerI++;
            }

            ticks.forEach((key, tick) -> ticks.put(key, tick+1));
        }, 0L, 1L);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.INSTANCE, () -> {
            if(StockManager.stockChangeTimer <= 0){
                StockManager.stockChangeTimer = StockManager.stockChangeTime;
                changeStock();
            } else {
                StockManager.stockChangeTimer--;
            }
        }, 0L, 20L);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.INSTANCE, () -> {
            for(Player player : Bukkit.getOnlinePlayers()){
                sendBoard(player);
            }
        }, 0L, 40L);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.INSTANCE, () -> {
            eventQuestionData.clear();

            Random random = new Random();
            eventQuestionData.add(EventQuestionType.values()[random.nextInt(EventQuestionType.values().length)]);
            if(eventQuestionData.get(0) == EventQuestionType.MATH){
                eventQuestionData.add((int) (Math.random() * 100));
                eventQuestionData.add((int) (Math.random() * 100));
            }

            for(Player player : Bukkit.getOnlinePlayers()){
                switch ((EventQuestionType) eventQuestionData.get(0)){
                    case MATH:
                        player.sendMessage(Component.text("[ 이벤트 ] ").color(NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)
                                .append(Component.text(eventQuestionData.get(1) + " + " + eventQuestionData.get(2) + " = ?").color(NamedTextColor.WHITE)));
                        break;
                }
            }
        }, 0L, 120*20L);
    }

    private static void changeStock(){
        for(String name : StockManager.stocks.keySet()){
            List<Object> data = StockManager.stocks.get(name);

            if(Math.random() <= 0.5){
                int maxChangeValue = (int) (Math.random() * (((int) data.get(4)) - 1 + 1) + 1);
                data.set(1, ((int) data.get(1)) + maxChangeValue);
                if((int) data.get(1) > (int) data.get(2))
                    data.set(2, data.get(1));

                data.set(7, "§a▲" + maxChangeValue);
            } else {
                int maxChangeValue = (int) (Math.random() * (((int) data.get(4)) - 1 + 1) + 1);
                data.set(1, ((int) data.get(1)) - maxChangeValue);
                if((int) data.get(1) < (int) data.get(3))
                    data.set(3, data.get(1));

                if((int) data.get(1) < 0) {
                    data.set(1, 0);
                    data.set(3, 0);
                }

                data.set(7, "§c▼" + maxChangeValue);
            }
        }
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(Component.text("주식가격이 변동되었습니다!").color(TextColor.color(200, 255, 200)));
        }
    }

    private static void sendBoard(Player player){
        FastBoard board = new FastBoard(player);
        board.updateTitle(new FontImageWrapper("mcemojis:mc_bamboo").getString() + "  §a§l마크§b§l의 §9§l숲  " + new FontImageWrapper("mcemojis:mc_bamboo").getString());

        String grade = "";
        if(player.hasPermission("group.admin"))
            grade = "§4§l어§c§l드§4§l민";
        else if(player.hasPermission("group.vip"))
            grade = "§aVIP";
        else if(player.hasPermission("group.mod"))
            grade = "§9M§bO§aD";
        else if(player.hasPermission("group.default"))
            grade = "§8유저";

        board.updateLines(
                new FontImageWrapper("mcemojis:mc_name_tag").getString() + " §2§l닉네임 §7: §8" + player.getName(),
                new FontImageWrapper("mcemojis:mc_dragon_breath").getString() + " §b§l등급 §7: " + grade,
                "",
                new FontImageWrapper("mcemojis:mc_emerald").getString() + " §e§l소지금 §7: §6§l" + String.format("%.1f", EconomyManager.getAmount(player.getUniqueId())) + "원",
                new FontImageWrapper("mcemojis:mc_nether_star").getString() + " §9§l잠수 포인트 §7: §b§l" + DivingPointManager.divingPoints.get(player.getUniqueId()) + "포인트",
                new FontImageWrapper("mcemojis:mc_gold_nugget").getString() + " §e§l이벤트 포인트 §7: §e§l" + EventCoinManager.eventCoins.get(player.getUniqueId()) + "코인",
                new FontImageWrapper("mcemojis:mc_diamond").getString() + " §d§l후원 포인트 §7: §5§l" + DonatePointManager.donatePoints.get(player.getUniqueId()) + "포인트",
                "",
                "",
                new FontImageWrapper("mcemojis:mc_filled_map").getString() + " §e§lMC-CROSS.MCV.KR " + new FontImageWrapper("mcemojis:mc_filled_map").getString()
        );
    }

    private static void sendAnnouncement(){
        ticks.put("Announcement", 0);

        for (Player player : Bukkit.getOnlinePlayers()) {
            PrivateSettingManager.PrivateSetting setting = PrivateSettingManager.loadPlayer(player.getUniqueId());

            if((boolean) setting.settings.get("Announce")) {
                player.sendMessage("");
                player.sendMessage("");
                switch (announcementI) {
                    case 0:
                        player.sendMessage("§f[ §a§lM§b§lC §f] §f§l저희 서버는 특별한 야생 시스템을 가지고 있습니다!");
                        announcementI = 1;
                        break;
                    case 1:
                        player.sendMessage("§f[ §a§lM§b§lC §f] §f§l/땅 커맨드로 자신만의 땅을 가지세요!");
                        announcementI = 2;
                        break;
                    case 2:
                        player.sendMessage("§f[ §a§lM§b§lC §f] §f§l/땅 땅원 커맨드로 자신만의 땅에 관리자를 추가할 수 있습니다!");
                        announcementI = 3;
                        break;
                    case 3:
                        player.sendMessage("§f[ §a§lM§b§lC §f] §f§l자신의 땅에서는 도박장, 결투장등등 다양한 건축물을 지을 수 있습니다!");
                        announcementI = 4;
                        break;
                    case 4:
                        player.sendMessage("§f[ §a§lM§b§lC §f] §f§l사람을 죽여서 얻는 §e§l황금머리§f§l를 전시 할 수 있습니다!");
                        announcementI = 0;
                        break;
                }
                player.sendMessage("");
                player.sendMessage("");
            }
        }
    }

    private static void cleanFloor(){
        ticks.put("floorcleaner", 0);

        for(World world : Bukkit.getWorlds()){
            world.getEntitiesByClass(Item.class).forEach(Entity::remove);
        }
        Bukkit.getOnlinePlayers().forEach((player) -> player.sendMessage("§f[ §b§l바닥§f§l청소 §f] §7바닥에 떨어진 모든 아이템을 삭제했습니다!"));
    }

    @EventHandler
    public void onTick(ServerTickStartEvent event){
        for (Player player : Bukkit.getOnlinePlayers()) {
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if (itemStack.getType() != Material.AIR) {
                ItemMeta meta = itemStack.getItemMeta();
                if (meta != null &&
                        meta.getLore() != null) {
                    List<String> lore = meta.getLore();
                    if (lore.size() > 3 && lore.get(3).contains("채굴 속도 +")) {
                        int plusMineAmount = Integer.parseInt(lore.get(3).split("§a§l채굴 속도 +")[1].replace("%", ""));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 25, plusMineAmount, false, false));
                    }
                }
            }

            ItemStack boots = player.getInventory().getBoots();
            if (boots != null && boots.getType() != Material.AIR) {
                ItemMeta meta = boots.getItemMeta();
                if (meta != null &&
                        meta.getLore() != null) {
                    List<String> lore = meta.getLore();
                    if (lore.size() > 1) {
                        int count = EnhanceMenu.countChar(lore.get(1), '★');
                        if(count == 10) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 25, 2, false, false));
                        }
                    }
                }
            }

            if(pvpTimes.containsKey(player.getUniqueId())) {
                if(pvpTimes.get(player.getUniqueId()) > 0) {
                    pvpTimes.put(player.getUniqueId(), pvpTimes.get(player.getUniqueId()) - 1);
                    player.sendActionBar(Component.text("PvP 풀리기까지 남은 시간 : " + pvpTimes.get(player.getUniqueId()) /20 + "초").color(TextColor.color(255, 255, 0)));
                } else {
                    pvpTimes.remove(player.getUniqueId());
                    player.sendActionBar(Component.text("PvP가 풀렸습니다!").color(TextColor.color(0, 255, 0)));
                }
            }
        }
    }

    public enum EventQuestionType {
        MATH;
    }
}
