package io.github.bindglam.core.managers;

import io.github.bindglam.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class HomeManager {
    public static final HashMap<UUID, Location> homes = new HashMap<>();

    public static void load() {
        for(String data : Core.INSTANCE.getConfig().getStringList("Homes")){
            UUID uuid = UUID.fromString(data.split(":")[0]);
            String locStr = data.split(":")[1];
            Location loc = new Location(null, 0, 0, 0);

            for(String locData : locStr.split(",")){
                String locKey = locData.split("=")[0];
                String locValue = locData.split("=")[1];
                if(Objects.equals(locKey, "world")){
                    loc.setWorld(Bukkit.getWorld(locValue));
                } else if(Objects.equals(locKey, "x")){
                    loc.setX(Double.parseDouble(locValue));
                } else if(Objects.equals(locKey, "y")){
                    loc.setY(Double.parseDouble(locValue));
                } else if(Objects.equals(locKey, "z")){
                    loc.setZ(Double.parseDouble(locValue));
                } else if(Objects.equals(locKey, "yaw")){
                    loc.setYaw(Float.parseFloat(locValue));
                } else if(Objects.equals(locKey, "pitch")){
                    loc.setPitch(Float.parseFloat(locValue));
                }
            }
            homes.put(uuid, loc);
        }
    }

    public static void save(){
        ArrayList<String> data = new ArrayList<>();
        for(UUID uuid : homes.keySet()){
            Location loc = homes.get(uuid);
            data.add(uuid.toString() + ":world=" + loc.getWorld().getName() + ",x=" + loc.getX() + ",y=" + loc.getY() + ",z=" + loc.getZ() + ",yaw=" + loc.getYaw() + ",pitch=" + loc.getPitch());
        }
        Core.INSTANCE.getConfig().set("Homes", data);
    }
}
