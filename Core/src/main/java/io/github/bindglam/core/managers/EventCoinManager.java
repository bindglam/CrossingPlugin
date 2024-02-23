package io.github.bindglam.core.managers;

import io.github.bindglam.core.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EventCoinManager {
    public static final HashMap<UUID, Integer> eventCoins = new HashMap<>();

    public static void init() {
        Core.INSTANCE.getConfig().getStringList("eventCoins").forEach(str -> {
            try {
                UUID uuid = UUID.fromString(str.split(":")[0]);
                Integer point = Integer.parseInt(str.split(":")[1]);
                eventCoins.put(uuid, point);
            } catch (NumberFormatException ignored){
            }
        });
    }

    public static void save() {
        List<String> data = new ArrayList<>();
        for (UUID uuid : eventCoins.keySet())
            data.add(uuid.toString() + ":" + uuid);
        Core.INSTANCE.getConfig().set("eventCoins", data);
    }
}
