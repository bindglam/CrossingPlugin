package io.github.bindglam.core.managers;

import io.github.bindglam.core.Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

public class BanManager {
    public static final HashMap<UUID, Integer> warnCounts = new HashMap<>();

    public static void init(){
        Bukkit.broadcast(Component.text("경고 횟수 로드중...").color(NamedTextColor.WHITE));
        for(String data : Core.INSTANCE.getConfig().getStringList("WarnCounts")){
            UUID uuid = UUID.fromString(data.split(":")[0]);
            Integer count = Integer.parseInt(data.split(":")[1]);
            warnCounts.put(uuid, count);
        }
    }

    public static void save(){
        Bukkit.broadcast(Component.text("경고 횟수 저장중...").color(NamedTextColor.WHITE));
        List<String> data = new ArrayList<>();
        for(UUID uuid : warnCounts.keySet()){
            data.add(uuid.toString() + ":" + warnCounts.get(uuid));
        }
        Core.INSTANCE.getConfig().set("WarnCounts", data);
    }

    public static void warnPlayer(OfflinePlayer offlinePlayer, String reason){
        if(!BanManager.warnCounts.containsKey(offlinePlayer.getUniqueId()))
            BanManager.warnCounts.put(offlinePlayer.getUniqueId(), 0);
        BanManager.warnCounts.put(offlinePlayer.getUniqueId(), BanManager.warnCounts.get(offlinePlayer.getUniqueId())+1);
        if(Bukkit.getPlayer(offlinePlayer.getUniqueId()) != null){
            Objects.requireNonNull(Bukkit.getPlayer(offlinePlayer.getUniqueId())).sendMessage(Component.newline()
                    .append(Component.text("당신은 규칙을 어겨 경고 1회를 받았습니다! ( 현재 경고 갯수 : " + BanManager.warnCounts.get(offlinePlayer.getUniqueId()) + " )").color(NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD)).appendNewline()
                    .append(Component.text(" - 사유 : " + reason).color(NamedTextColor.RED)).appendNewline());
        }
        if(BanManager.warnCounts.get(offlinePlayer.getUniqueId()) >= 3){
            banPlayer(offlinePlayer, reason);
            BanManager.warnCounts.put(offlinePlayer.getUniqueId(), 0);
        }
    }

    public static void banPlayer(OfflinePlayer offlinePlayer, String reason){
        offlinePlayer.banPlayer("§4§l당신은 차단되었습니다!\n§c사유: " + reason);
    }
}
