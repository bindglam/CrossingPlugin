package io.github.bindglam.core.managers;

import io.github.bindglam.core.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankManager {
    public static final List<UUID> hasBankPlayers = new ArrayList<>();

    public static void init(){
        Core.INSTANCE.getConfig().getStringList("hasBank").forEach((uuidStr) -> hasBankPlayers.add(UUID.fromString(uuidStr)));
    }

    public static void save(){
        List<String> data = new ArrayList<>();
        for(UUID uuid : hasBankPlayers){
            data.add(uuid.toString());
        }
        Core.INSTANCE.getConfig().set("hasBank", data);
    }
}
