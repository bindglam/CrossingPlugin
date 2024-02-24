package io.github.bindglam.core.managers;

import io.github.bindglam.core.Core;

import java.util.HashMap;
import java.util.UUID;

public class MaxHealthManager {
    public static final HashMap<UUID, Double> lastMaxHealth = new HashMap<>();

    public static Double loadMaxHealth(UUID uuid){
        if(lastMaxHealth.containsKey(uuid))
            return lastMaxHealth.get(uuid);
        if(Core.INSTANCE.getConfig().getDouble("LastMaxHealth." + uuid.toString()) > 0.0)
            return Core.INSTANCE.getConfig().getDouble("LastMaxHealth." + uuid);
        return 20.0;
    }

    public static void save(){
        for(UUID uuid : lastMaxHealth.keySet()){
            Core.INSTANCE.getConfig().set("LastMaxHealth." + uuid.toString(), lastMaxHealth.get(uuid));
        }
    }
}
