package io.github.bindglam.core.managers;

import io.github.bindglam.core.Core;

import java.util.*;

public class PvPManager {
    public static LinkedHashMap<UUID, Integer> pvpRanks = new LinkedHashMap<>();

    public static void init(){
        for(String data : Core.INSTANCE.getConfig().getStringList("PvPRanks")){
            pvpRanks.put(UUID.fromString(data.split(":")[0]), Integer.parseInt(data.split(":")[1]));
        }
        sortRank();
    }

    public static void save(){
        sortRank();
        List<String> data = new ArrayList<>();
        for(UUID uuid : pvpRanks.keySet()){
            data.add(uuid.toString() + ":" + pvpRanks.get(uuid));
        }
        Core.INSTANCE.getConfig().set("PvPRanks", data);
    }

    public static void sortRank(){
        LinkedHashMap<UUID, Integer> newMap = new LinkedHashMap<>();

        List<UUID> keySet = new ArrayList<>(pvpRanks.keySet());
        keySet.sort((o1, o2) -> pvpRanks.get(o2).compareTo(pvpRanks.get(o1)));

        for (UUID key : keySet) {
            newMap.put(key, pvpRanks.get(key));
        }
        pvpRanks = newMap;
    }
}
