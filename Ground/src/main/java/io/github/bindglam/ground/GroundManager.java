package io.github.bindglam.ground;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.Array;
import java.util.*;

public class GroundManager implements Listener {
    public static final int MAX_MAKE_GROUND = 10;

    public static final LinkedHashMap<Location, UUID> grounds = new LinkedHashMap<>();
    public static final LinkedHashMap<Location, ArrayList<UUID>> grounders = new LinkedHashMap<>();
    public static final LinkedHashMap<UUID, Integer> canMakeGround = new LinkedHashMap<>();

    public static void load(){
        for(String data : GroundPlugin.INSTANCE.getConfig().getStringList("Grounds")){
            String posStr = data.split(":")[0];
            String plrName = data.split(":")[1];

            Location loc = new Location(null, 0, 0, 0);
            for(String locData : posStr.split(",")){
                if(locData.contains("x=")){
                    loc.setX(Double.parseDouble(locData.replace("x=", "")));
                } else if(locData.contains("y=")){
                    loc.setY(Double.parseDouble(locData.replace("y=", "")));
                } else if(locData.contains("z=")){
                    loc.setZ(Double.parseDouble(locData.replace("z=", "")));
                } else if(locData.contains("world=")){
                    loc.setWorld(Bukkit.getWorld(locData.replace("world=", "")));
                }
            }
            grounds.put(loc, UUID.fromString(plrName));
        }

        for(String data : GroundPlugin.INSTANCE.getConfig().getStringList("CanMakeGround")){
            String plrName = data.split(":")[0];
            Integer count = Integer.parseInt(data.split(":")[1]);

            canMakeGround.put(UUID.fromString(plrName), count);
        }

        if(GroundPlugin.INSTANCE.getConfig().get("GroundersLoc") != null) {
            int i = 0;
            for (String locationStr : (List<String>) GroundPlugin.INSTANCE.getConfig().get("GroundersLoc")) {
                Location loc = new Location(null, 0, 0, 0);
                for(String locData : locationStr.split(",")){
                    if(locData.contains("x=")){
                        loc.setX(Double.parseDouble(locData.replace("x=", "")));
                    } else if(locData.contains("y=")){
                        loc.setY(Double.parseDouble(locData.replace("y=", "")));
                    } else if(locData.contains("z=")){
                        loc.setZ(Double.parseDouble(locData.replace("z=", "")));
                    } else if(locData.contains("world=")){
                        loc.setWorld(Bukkit.getWorld(locData.replace("world=", "")));
                    }
                }
                List<ArrayList<UUID>> groundersData = new ArrayList<>();
                for(List<String> uuidStr : (List<List<String>>) GroundPlugin.INSTANCE.getConfig().get("Grounders")){
                    List<UUID> uuids = new ArrayList<>();
                    uuidStr.forEach((str) -> uuids.add(UUID.fromString(str)));
                    groundersData.add(new ArrayList<>(uuids));
                }
                grounders.put(loc, groundersData.get(i));
                i++;
            }
        }

        //초당 청크로딩
        Bukkit.getScheduler().scheduleSyncRepeatingTask(GroundPlugin.INSTANCE, () -> {
            grounds.keySet().forEach((loc) -> loc.getWorld().loadChunk(loc.getChunk()));
        }, 0L, 20L);
    }

    public static void save(){
        List<String> data = new ArrayList<>();
        for(Location loc : grounds.keySet()){
            UUID plrName = grounds.get(loc);

            data.add("x=" + loc.getX() + ",y=" + loc.getY() + ",z=" + loc.getZ() + ",world=" + loc.getWorld().getName()
                    + ":" + plrName.toString());
        }
        GroundPlugin.INSTANCE.getConfig().set("Grounds", data);

        List<String> data2 = new ArrayList<>();
        for(UUID plr : canMakeGround.keySet()){
            Integer count = canMakeGround.get(plr);

            data2.add(plr.toString() + ":" + count);
        }
        GroundPlugin.INSTANCE.getConfig().set("CanMakeGround", data2);

        List<String> data3 = new ArrayList<>();
        for(Location loc : grounders.keySet()){
            data3.add("x=" + loc.getX() + ",y=" + loc.getY() + ",z=" + loc.getZ() + ",world=" + loc.getWorld().getName());
        }
        GroundPlugin.INSTANCE.getConfig().set("GroundersLoc", data3);

        List<ArrayList<String>> data4 = new ArrayList<>();
        for(ArrayList<UUID> uuids : grounders.values().stream().toList()){
            List<String> localData = new ArrayList<>();
            uuids.forEach((uuid) -> localData.add(uuid.toString()));
            data4.add(new ArrayList<>(localData));
        }
        GroundPlugin.INSTANCE.getConfig().set("Grounders", data4);
        GroundPlugin.INSTANCE.saveConfig();
    }

    public static List<Object> isInGround(Player player){
        return isInGround(player.getLocation());
    }

    public static List<Object> isInGround(Location location){
        for(Location loc : grounds.keySet()) {
            if((location.getX() >= loc.getX() - 9 && location.getX() <= loc.getX() + 8)
                    && (location.getZ() >= loc.getZ() - 9 && location.getZ() <= loc.getZ() + 8) && loc.getWorld().getName().equals(location.getWorld().getName()))
                return List.of(true, loc);
        }
        return null;
    }

    public static Location isOverlappingGround(Location location){
        for(int x = -9; x < 8; x++){
            for(int z = -9; z < 9; z++){
                Location loc = new Location(location.getWorld(), location.getX()+x, location.getY(), location.getZ()+z);
                if(isInGround(loc) != null){
                    return (Location) Objects.requireNonNull(isInGround(loc)).get(1);
                }
            }
        }
        return null;
    }

    public static int getGroundCount(UUID name){
        int count = 0;
        for (Map.Entry<Location, UUID> entry : GroundManager.grounds.entrySet()) {
            Location key = entry.getKey();
            UUID value = entry.getValue();

            if (Objects.equals(value, name))
                count++;
        }
        return count;
    }

    public static Location getGroundLoc(Player player, int num){
        Location loc;
        try {
            if(GroundManager.grounds.keySet().stream().toList().size()-1 < num){
                player.sendMessage("§c§l가진 땅의 번호가 아닙니다.");
                return null;
            }
            loc = GroundManager.grounds.keySet().stream().toList().get(num);
        } catch (NumberFormatException e){
            player.sendMessage("§c§l숫자가 아닙니다.");
            return null;
        }
        if(!Objects.equals(GroundManager.grounds.get(loc), player.getUniqueId())){
            player.sendMessage("§c§l땅 주인이 아닙니다.");
            return null;
        }
        return loc;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(!canMakeGround.containsKey(player.getUniqueId()))
            canMakeGround.put(player.getUniqueId(), MAX_MAKE_GROUND);
    }
}
