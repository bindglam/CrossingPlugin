package io.github.bindglam.core.listeners;

import io.github.bindglam.core.Core;
import io.github.bindglam.core.managers.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitLoginListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        event.setJoinMessage("§f[ §a§l+ §f] §7§l" + player.getName());

        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage("§d환영합니다! §6" + player.getName() + " §b님");
        if(player.hasPlayedBefore()) {
            player.sendMessage("§a오늘도 §d§l플레이§a해주셔서 §e§l감사합니다!");
        } else {
            player.sendMessage("§9§l저희서버에 처음 방문하셨군요?");
            player.sendMessage("§b§l방문에 주셔서 감사합니다!");
            player.sendMessage("");
            player.sendMessage("§e§l/메뉴 를 입력하여 메뉴를 여실 수 있습니다.");
            player.sendMessage("");
            player.sendMessage("§d§l튜토리얼로 이동합니다!");

            player.teleportAsync(Core.INSTANCE.worldManager.getMVWorld("tutorial").getSpawnLocation());
        }
        player.sendMessage("");
        player.sendMessage("§b저희 서버는 §9§l디스코드 §b가입이 권장됩니다!");
        player.sendMessage(Component.text("https://discord.gg/g6cdUEZw").decorate(TextDecoration.UNDERLINED).color(TextColor.color(0, 200, 255))
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/g6cdUEZw")));
        player.sendMessage("");
        player.sendMessage("");

        PrivateSettingManager.loadPlayer(player.getUniqueId());

        if(!StatsManager.statsMap.containsKey(player.getUniqueId()))
            StatsManager.statsMap.put(player.getUniqueId(), new StatsManager.Stats(0, 0, 0, 0, 0, 0, 0, 100, 100, 100));

        if(!DivingPointManager.divingPoints.containsKey(player.getUniqueId()))
            DivingPointManager.divingPoints.put(player.getUniqueId(), 0);
        if(!EventCoinManager.eventCoins.containsKey(player.getUniqueId()))
            EventCoinManager.eventCoins.put(player.getUniqueId(), 0);
        if(!DonatePointManager.donatePoints.containsKey(player.getUniqueId()))
            DonatePointManager.donatePoints.put(player.getUniqueId(), 0);

        player.setHealthScale(MaxHealthManager.loadMaxHealth(player.getUniqueId()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        event.setQuitMessage("§f[ §c§l- §f] §7§l" + player.getName());

        MaxHealthManager.lastMaxHealth.put(player.getUniqueId(), player.getHealthScale());
    }
}
