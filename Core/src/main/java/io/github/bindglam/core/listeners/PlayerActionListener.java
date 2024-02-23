package io.github.bindglam.core.listeners;

import io.github.bindglam.core.managers.PrivateSettingManager;
import io.github.bindglam.core.managers.StatsManager;
import io.github.bindglam.core.menu.core.CoreMenu;
import io.github.bindglam.ground.GroundManager;
import io.github.bindglam.ground.events.GroundEnterEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PlayerActionListener implements Listener {
    @EventHandler
    public void onSwapHands(PlayerSwapHandItemsEvent event){
        Player player = event.getPlayer();

        if(player.isSneaking()){
            event.setCancelled(true);
            new CoreMenu(player).open(player, 0);
            player.playSound(player.getLocation(), "block.note_block.pling", 100f, 2f);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        StatsManager.Stats stats = StatsManager.getStats(player.getUniqueId());

        player.setWalkSpeed(0.2f + (float) stats.speedLv / 20.0f);
    }

    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event){
        if(event.message() == null) return;
        event.message(Component.text("\uD83D\uDCD6").color(TextColor.color(255, 206, 0)).decorate(TextDecoration.BOLD)
                .append(Component.text(" | ").color(TextColor.color(255, 255, 255)))
                .append(Objects.requireNonNull(event.message()).color(TextColor.color(255, 207, 248))));
    }

    @EventHandler
    public void onGroundEnter(GroundEnterEvent event){
        Player player = event.getPlayer();
        List<Object> data = event.getData();
        PrivateSettingManager.PrivateSetting setting = PrivateSettingManager.loadPlayer(player.getUniqueId());

        if((boolean) setting.settings.get("GroundEnterMessage")){
            UUID uuid = GroundManager.grounds.get((Location) data.get(1));
            player.showTitle(Title.title(Component.text(Objects.requireNonNull(Bukkit.getOfflinePlayer(uuid).getName())).color(TextColor.color(255, 255, 0)).decorate(TextDecoration.BOLD),
                    Component.text("님의 땅에 들오셨습니다!").color(NamedTextColor.WHITE), Title.Times.times(Duration.ofNanos(100), Duration.ofNanos(1000), Duration.ofNanos(100))));
        }
    }
}
