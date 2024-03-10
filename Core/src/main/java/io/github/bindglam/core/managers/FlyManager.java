package io.github.bindglam.core.managers;

import io.github.bindglam.core.Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.*;

public class FlyManager {
    public static final HashMap<UUID, Integer> flyTimes = new HashMap<>();

    public static void init(){
        Bukkit.broadcast(Component.text("플라이 시간 로드중...").color(NamedTextColor.WHITE));
        for(String data : Core.INSTANCE.getConfig().getStringList("FlyTimes")){
            UUID uuid = UUID.fromString(data.split(":")[0]);
            Integer count = Integer.parseInt(data.split(":")[1]);
            flyTimes.put(uuid, count);
        }
    }

    public static void save(){
        Bukkit.broadcast(Component.text("플라이 시간 저장중...").color(NamedTextColor.WHITE));
        List<String> data = new ArrayList<>();
        for(UUID uuid : flyTimes.keySet()){
            data.add(uuid.toString() + ":" + flyTimes.get(uuid));
        }
        Core.INSTANCE.getConfig().set("FlyTimes", data);
    }
}
