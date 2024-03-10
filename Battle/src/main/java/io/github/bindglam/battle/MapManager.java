package io.github.bindglam.battle;

import io.github.bindglam.battle.events.PvPMapJoinEvent;
import io.github.bindglam.battle.events.PvPMapLeaveEvent;
import io.github.bindglam.battle.events.PvPMapStartEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class MapManager {
    private static final FileConfiguration config = BattlePlugin.INSTANCE.getConfig();

    public static final HashMap<String, Map> maps = new HashMap<>();

    public static void init(){
        for(String mapName : config.getStringList("MapNames")){
            LinkedList<Location> spawnLocations = new LinkedList<>();
            for(String locData : config.getStringList("Maps." + mapName + ".spawnLocations")){
                spawnLocations.add(parseLocation(locData));
            }

            maps.put(mapName, new Map(
                    mapName,
                    false,
                    new ArrayList<>(),
                    spawnLocations,
                    config.getInt("Maps." + mapName + ".limitPlayerCount")
            ));
        }
    }

    public static void save(){
        config.set("MapNames", maps.keySet().stream().toList());
        for(String name : maps.keySet()){
            Map map = maps.get(name);

            config.set("Maps." + name + ".limitPlayerCount", map.limitPlayerCount);
            config.set("Maps." + name + ".spawnLocations", convertLocationsToStrings(map.spawnLocations));
        }
    }

    private static Location parseLocation(String data){
        Location location = new Location(null, 0.0, 0.0, 0.0);
        for(String locData : data.split(",")){
            String key = locData.split("=")[0];
            String value = locData.split("=")[1];

            switch (key){
                case "world":
                    location.setWorld(Bukkit.getWorld(value));
                    break;

                case "x":
                    location.setX(Double.parseDouble(value));
                    break;

                case "y":
                    location.setY(Double.parseDouble(value));
                    break;

                case "z":
                    location.setZ(Double.parseDouble(value));
                    break;

                case "yaw":
                    location.setYaw(Float.parseFloat(value));
                    break;

                case "pitch":
                    location.setPitch(Float.parseFloat(value));
                    break;
            }
        }
        return location;
    }

    private static String convertLocationToString(Location location){
        return "world=" + location.getWorld().getName() + ",x=" + location.getX() + ",y=" + location.getY() + ",z=" + location.getZ() + ",yaw=" + location.getYaw() + ",pitch=" + location.getPitch();
    }

    private static List<String> convertLocationsToStrings(List<Location> locations){
        List<String> data = new ArrayList<>();
        locations.forEach((loc) -> data.add(convertLocationToString(loc)));
        return data;
    }

    public static void joinPlayer(Player player, String mapName){
        if (!MapManager.maps.containsKey(mapName)) {
            player.sendMessage(Component.text("알맞은 맵 이름이 아닙니다!"));
            return;
        }
        MapManager.Map map = MapManager.maps.get(mapName);
        if(map.players.size() >= map.limitPlayerCount){
            player.sendMessage(Component.text("이미 맵의 플레이어 수가 찼습니다!"));
            return;
        }
        if(map.isPlaying){
            player.sendMessage(Component.text("이미 맵은 플레이중입니다!"));
            return;
        }
        for(Map otherMap : maps.values()){
            for(Player joiner : otherMap.players){
                if(joiner.getUniqueId() == player.getUniqueId()){
                    player.sendMessage(Component.text("이미 다른 맵에 접속해있습니다!"));
                    return;
                }
            }
        }

        int index = map.players.size();
        map.players.add(player);
        player.teleportAsync(map.spawnLocations.get(index));
        player.sendMessage(Component.text("성공적으로 " + mapName + " 맵에 입장했습니다! ( 현재 플레이어 수 : " + map.players.size() + "명, 필요한 플레이어 수 : " + map.limitPlayerCount + "명 )"));

        new PvPMapJoinEvent(player, mapName).callEvent();

        if(map.players.size() >= map.limitPlayerCount){
            map.isPlaying = true;
            for(Player joiner : map.players) {
                joiner.sendMessage(Component.text("이제 PvP를 시작합니다!"));
            }
            new PvPMapStartEvent(mapName).callEvent();
        }
    }

    public static void leavePlayer(Player player){
        for(Map map : maps.values()){
            for (int i = map.players.size() - 1; i >= 0; i--) {
                Player joiner = map.players.get(i);
                if(joiner.getUniqueId() == player.getUniqueId()){
                    if(map.isPlaying){
                        player.sendMessage(Component.text("이미 플레이중입니다!"));
                        return;
                    }
                    map.players.remove(player);
                    player.teleportAsync(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
                    player.sendMessage(Component.text("성공적으로 맵에서 퇴장했습니다!"));

                    new PvPMapLeaveEvent(player).callEvent();

                    return;
                }
            }
        }
        player.sendMessage(Component.text("입장해있는 맵이 없습니다!"));
    }

    public static Map isInAnyMaps(Player player){
        for(Map map : maps.values()) {
            for (int i = map.players.size() - 1; i >= 0; i--) {
                Player joiner = map.players.get(i);
                if(joiner.getUniqueId() == player.getUniqueId())
                    return map;
            }
        }
        return null;
    }

    public static class Map {
        public String name;
        public boolean isPlaying;
        public List<Player> players;
        public LinkedList<Location> spawnLocations;
        public int limitPlayerCount;

        public Map(String name, boolean isPlaying, List<Player> players, LinkedList<Location> spawnLocations, int limitPlayerCount) {
            this.name = name;
            this.isPlaying = isPlaying;
            this.players = players;
            this.spawnLocations = spawnLocations;
            this.limitPlayerCount = limitPlayerCount;
        }
    }
}
