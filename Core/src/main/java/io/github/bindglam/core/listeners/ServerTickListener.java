package io.github.bindglam.core.listeners;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import fr.mrmicky.fastboard.FastBoard;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.managers.*;
import io.github.bindglam.core.menu.blocks.EnhanceMenu;
import io.github.bindglam.core.utils.CouponGenerator;
import io.github.bindglam.economy.EconomyManager;
import io.github.bindglam.ground.GroundManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServerTickListener implements Listener {
    private static final HashMap<String, Integer> ticks = new HashMap<>(){{ put("floorcleaner", 0); put("Announcement", 0); }};
    private static int announcementI = 0, floorCleanerI = 0;

    public static final List<Object> eventQuestionData = new ArrayList<>();
    public static final ConcurrentHashMap<UUID, Integer> pvpTimes = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<UUID, Integer> divingTimes = new ConcurrentHashMap<>();
    public static final List<UUID> pvpInGrounds = new ArrayList<>();

    public static void init(){
        Bukkit.broadcast(Component.text("틱 활동 초기화중...").color(NamedTextColor.WHITE));
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
            } else if(eventQuestionData.get(0) == EventQuestionType.COUPON){
                eventQuestionData.add(CouponGenerator.generateCouponCode(5));
            }

            for(Player player : Bukkit.getOnlinePlayers()){
                switch ((EventQuestionType) eventQuestionData.get(0)){
                    case MATH:
                        player.sendMessage(Component.text("[ 이벤트 ] ").color(NamedTextColor.YELLOW)
                                .append(Component.text(eventQuestionData.get(1) + " + " + eventQuestionData.get(2) + " = ?").color(NamedTextColor.WHITE))
                                .append(Component.text(" ← 답을 채팅에 입력하세요!").color(NamedTextColor.GOLD)));
                        break;

                    case COUPON:
                        player.sendMessage(Component.text("[ 이벤트 ] ").color(NamedTextColor.YELLOW)
                                .append(Component.text("쿠폰 코드 : " + eventQuestionData.get(1)).color(NamedTextColor.WHITE))
                                .append(Component.text(" ← 이 코드를 채팅에 입력하세요!").color(NamedTextColor.GOLD)));
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
        board.updateTitle(new FontImageWrapper("mcemojis:mc_bamboo").getString() + "  §a마크§b의 §9숲  " + new FontImageWrapper("mcemojis:mc_bamboo").getString());

        String grade = "";
        if(player.hasPermission("group.admin"))
            grade = "§4§l어§c§l드§4§l민";
        else if(player.hasPermission("group.vip"))
            grade = "§aVIP";
        else if(player.hasPermission("group.mod"))
            grade = "§9M§bO§aD";
        else if(player.hasPermission("group.default"))
            grade = "§8유저";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());

        String season = Core.INSTANCE.customCrops.getAPI().getSeason(player.getWorld().getName()).getSeason();
        season = switch (season) {
            case "SPRING" -> "§e봄";
            case "SUMMER" -> "§b여름";
            case "AUTUMN" -> "§c가을";
            case "WINTER" -> "§f겨울";
            default -> season;
        };

        board.updateLines(
                new FontImageWrapper("mcemojis:mc_name_tag").getString() + " §2닉네임 §7: §8" + player.getName(),
                new FontImageWrapper("mcemojis:mc_dragon_breath").getString() + " §b등급 §7: " + grade,
                "",
                new FontImageWrapper("mcemojis:mc_emerald").getString() + " §e소지금 §7: §6" + String.format("%.1f", EconomyManager.getAmount(player.getUniqueId())) + "원",
                new FontImageWrapper("mcemojis:mc_nether_star").getString() + " §9잠수 포인트 §7: §b" + DivingPointManager.divingPoints.get(player.getUniqueId()) + "포인트",
                new FontImageWrapper("mcemojis:mc_gold_nugget").getString() + " §e이벤트 포인트 §7: §e" + EventCoinManager.eventCoins.get(player.getUniqueId()) + "코인",
                new FontImageWrapper("mcemojis:mc_diamond").getString() + " §d마일리지 §7: §5" + DonatePointManager.donatePoints.get(player.getUniqueId()) + "마일리지",
                "",
                new FontImageWrapper("mcemojis:mc_bell").getString() + " §6계절 §7: §f" + season,
                "",
                new FontImageWrapper("mcemojis:mc_clock").getString() + " §e" + date,
                new FontImageWrapper("mcemojis:mc_filled_map").getString() + " §eMC-CROSS.MCV.KR " + new FontImageWrapper("mcemojis:mc_filled_map").getString()
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

            if (!FlyManager.flyTimes.containsKey(player.getUniqueId()))
                FlyManager.flyTimes.put(player.getUniqueId(), 0);
            List<Object> data = GroundManager.isInGround(player);
            boolean isInMyGround = false;
            if(data != null){
                UUID owner = GroundManager.grounds.get((Location) data.get(1));
                if(Objects.equals(owner, player.getUniqueId()))
                    isInMyGround = true;
                if(GroundManager.grounders.containsKey((Location) data.get(1))) if(GroundManager.grounders.get((Location) data.get(1)).contains(player.getUniqueId()))
                    isInMyGround = true;
            }
            if(player.isOp()){
                player.setAllowFlight(true);
            } else {
                player.setAllowFlight(FlyManager.flyTimes.get(player.getUniqueId()) > 0 && !ServerTickListener.pvpTimes.containsKey(player.getUniqueId())
                        && isInMyGround);
            }
            if(player.isFlying()) {
                if (FlyManager.flyTimes.get(player.getUniqueId()) > 0) {
                    player.sendActionBar(Component.text("남은 플라이 시간 : ").color(NamedTextColor.YELLOW)
                            .append(Component.text(FlyManager.flyTimes.get(player.getUniqueId())/20 + "초").color(NamedTextColor.WHITE)));
                    FlyManager.flyTimes.put(player.getUniqueId(), FlyManager.flyTimes.get(player.getUniqueId()) - 1);
                }
            }

            if(!divingTimes.containsKey(player.getUniqueId()))
                divingTimes.put(player.getUniqueId(), 0);
            if(divingTimes.get(player.getUniqueId()) >= 120*20){
                player.showTitle(Title.title(
                        Component.text("< ").color(NamedTextColor.WHITE)
                                .append(Component.text("잠수").color(NamedTextColor.BLUE))
                                .append(Component.text(" >").color(NamedTextColor.WHITE)),
                        Component.text("잠수 포인트 +1").color(NamedTextColor.WHITE),
                        Title.Times.times(Duration.ofNanos(800), Duration.ofNanos(2000), Duration.ofNanos(800))
                ));
                DivingPointManager.divingPoints.put(player.getUniqueId(), DivingPointManager.divingPoints.get(player.getUniqueId())+1);
                divingTimes.put(player.getUniqueId(), 0);
            }
            divingTimes.put(player.getUniqueId(), divingTimes.get(player.getUniqueId())+1);
        }
    }

    public enum EventQuestionType {
        MATH,
        COUPON;
    }
}
