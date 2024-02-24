package io.github.bindglam.core.listeners;

import io.github.bindglam.core.managers.EventCoinManager;
import io.github.bindglam.core.managers.PrivateSettingManager;
import io.github.bindglam.core.managers.StatsManager;
import io.github.bindglam.core.menu.core.CoreMenu;
import io.github.bindglam.ground.GroundManager;
import io.github.bindglam.ground.events.GroundEnterEvent;
import io.github.bindglam.ground.events.GroundExitEvent;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
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
import org.purpurmc.purpur.event.ExecuteCommandEvent;

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

        List<Object> data = GroundManager.isInGround(player);
        if(data != null) {
            if(ServerTickListener.pvpTimes.containsKey(player.getUniqueId()) && !ServerTickListener.pvpInGrounds.contains(player.getUniqueId())){
                event.setCancelled(true);
                player.sendActionBar(Component.text("PvP중에는 땅에 들어갈 수 없습니다!").color(TextColor.color(255, 0, 0)));
            }
        }
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

    @EventHandler
    public void onGroundExit(GroundExitEvent event) {
        Player player = event.getPlayer();

        ServerTickListener.pvpInGrounds.remove(player.getUniqueId());
    }

    @EventHandler
    public void onChat(AsyncChatEvent event){
        Player player = event.getPlayer();
        String message = PlainTextComponentSerializer.plainText().serialize(event.message()).replace("[", "").replace("]", "");

        if(!ServerTickListener.eventQuestionData.isEmpty()){
            switch ((ServerTickListener.EventQuestionType) ServerTickListener.eventQuestionData.get(0)){
                case MATH:
                    int amount;
                    try {
                        amount = Integer.parseInt(message);
                    } catch (NumberFormatException ignored){
                        break;
                    }

                    if(amount == ((int) ServerTickListener.eventQuestionData.get(1)) + ((int) ServerTickListener.eventQuestionData.get(2))){
                        ServerTickListener.eventQuestionData.clear();
                        for(Player otherPlayer : Bukkit.getOnlinePlayers()){
                            otherPlayer.sendMessage(Component.text(player.getName() + "님께서 이벤트 문제를 맞추셨습니다!").color(NamedTextColor.GREEN));
                        }
                        player.sendMessage(Component.text("정답입니다!").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD));
                        if(!EventCoinManager.eventCoins.containsKey(player.getUniqueId()))
                            EventCoinManager.eventCoins.put(player.getUniqueId(), 0);
                        EventCoinManager.eventCoins.put(player.getUniqueId(), EventCoinManager.eventCoins.get(player.getUniqueId())+1);
                    }
                    break;
            }
        }
    }

    /*
    @EventHandler
    public void onCommandExecute(ExecuteCommandEvent event){
        if(!(event.getSender() instanceof Player player)) return;
        if(event.getCommand().getLabel().equalsIgnoreCase("verify") || event.getCommand().getLabel().equalsIgnoreCase("인증") && event.getArgs().length == 1){
            player.setAllowFlight(true);
        }
    }
     */
}
