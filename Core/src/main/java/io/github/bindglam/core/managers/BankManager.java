package io.github.bindglam.core.managers;

import io.github.bindglam.core.Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankManager {
    public static final List<UUID> hasBankPlayers = new ArrayList<>();

    public static void init(){
        Bukkit.broadcast(Component.text("계좌 정보 로드중...").color(NamedTextColor.WHITE));
        Core.INSTANCE.getConfig().getStringList("hasBank").forEach((uuidStr) -> hasBankPlayers.add(UUID.fromString(uuidStr)));
    }

    public static void save(){
        Bukkit.broadcast(Component.text("계좌 정보 저장중...").color(NamedTextColor.WHITE));
        List<String> data = new ArrayList<>();
        for(UUID uuid : hasBankPlayers){
            data.add(uuid.toString());
        }
        Core.INSTANCE.getConfig().set("hasBank", data);
    }
}
