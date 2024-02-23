package io.github.bindglam.core.managers;

import io.github.bindglam.core.Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

import java.util.*;

public class StatsManager {
    public static final HashMap<UUID, Stats> statsMap = new HashMap<>();

    public static void init(){
        for(String str : Core.INSTANCE.getConfig().getStringList("Stats")){
            UUID uuid = UUID.fromString(str.split(":")[0]);
            String[] dataList = str.split(":")[1].split(",");
            Stats stats = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            for(String data : dataList){
                String key = data.split("=")[0];
                String value = data.split("=")[1];

                if(Objects.equals(key, "strongLv"))
                    stats.strongLv = Integer.parseInt(value);
                else if(Objects.equals(key, "defenseLv"))
                    stats.defenseLv = Integer.parseInt(value);
                else if(Objects.equals(key, "speedLv"))
                    stats.speedLv = Integer.parseInt(value);
                else if(Objects.equals(key, "pvpLv"))
                    stats.pvpLv = Integer.parseInt(value);

                else if(Objects.equals(key, "strongXp"))
                    stats.strongXp = Integer.parseInt(value);
                else if(Objects.equals(key, "defenseXp"))
                    stats.defenseXp = Integer.parseInt(value);
                else if(Objects.equals(key, "speedXp"))
                    stats.speedXp = Integer.parseInt(value);

                else if(Objects.equals(key, "strongMaxXp"))
                    stats.strongMaxXp = Integer.parseInt(value);
                else if(Objects.equals(key, "defenseMaxXp"))
                    stats.defenseMaxXp = Integer.parseInt(value);
                else if(Objects.equals(key, "speedMaxXp"))
                    stats.speedMaxXp = Integer.parseInt(value);
            }
            statsMap.put(uuid, stats);
        }
    }

    public static void save(){
        List<String> data = new ArrayList<>();
        for(UUID uuid : statsMap.keySet()){
            Stats stats = statsMap.get(uuid);
            data.add(uuid.toString() + ":strongLv=" + stats.strongLv + ",defenseLv=" + stats.defenseLv + ",speedLv=" + stats.speedLv + ",pvpLv=" + stats.pvpLv
                    + ",strongXp=" + stats.strongXp + ",defenseXp=" + stats.defenseXp + ",speedXp=" + stats.speedXp
                    + ",strongMaxXp=" + stats.strongMaxXp + ",defenseMaxXp=" + stats.defenseMaxXp + ",speedMaxXp=" + stats.speedMaxXp);
        }
        Core.INSTANCE.getConfig().set("Stats", data);
    }

    public static Stats getStats(UUID uuid){
        if(!statsMap.containsKey(uuid)){
            statsMap.put(uuid, new Stats(0, 0, 0, 0, 0, 0, 0, 100, 100, 100));
        }
        return statsMap.get(uuid);
    }

    public static void update(Stats stats, Player player){
        if(stats.strongXp >= stats.strongMaxXp){
            stats.strongLv++;
            stats.strongMaxXp*=2;
            stats.strongXp = 0;
            levelUp("힘", stats.strongLv, player);
        }

        if(stats.defenseXp >= stats.defenseMaxXp){
            stats.defenseLv++;
            stats.defenseMaxXp*=2;
            stats.defenseXp = 0;
            levelUp("방어력", stats.defenseLv, player);
        }

        if(stats.speedXp >= stats.speedMaxXp){
            stats.speedLv++;
            stats.speedMaxXp*=2;
            stats.speedXp = 0;
            levelUp("민첩", stats.speedLv, player);
        }
    }

    private static void levelUp(String statsName, int statsLv, Player player){
        player.sendMessage(Component.text(statsName + " 스텟이 ").color(TextColor.color(0, 255, 0))
                .append(Component.text("레벨업").color(TextColor.color(255, 255, 0)).decorate(TextDecoration.BOLD))
                .append(Component.text("하였습니다! ( Lv." + statsLv + " )").color(TextColor.color(0, 255, 0))));
        player.playSound(player.getLocation(), "block.enchantment_table.use", 120f, 1.2f);
        player.playSound(player.getLocation(), "entity.player.levelup", 100f, 1.2f);
    }

    public static class Stats {
        public int strongLv, defenseLv, speedLv, pvpLv;
        public int strongXp, defenseXp, speedXp;
        public int strongMaxXp, defenseMaxXp, speedMaxXp;

        public Stats(int strong, int defense, int speed, int pvp, int strongXp, int defenseXp, int speedXp, int strongMaxXp, int defenseMaxXp, int speedMaxXp) {
            this.strongLv = strong;
            this.defenseLv = defense;
            this.speedLv = speed;
            this.pvpLv = pvp;
            this.strongXp = strongXp;
            this.defenseXp = defenseXp;
            this.speedXp = speedXp;
            this.strongMaxXp = strongMaxXp;
            this.defenseMaxXp = defenseMaxXp;
            this.speedMaxXp = speedMaxXp;
        }
    }
}
