package io.github.bindglam.core.npc;

import io.github.bindglam.core.Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class InteractNPC {
    public String name;
    public Component componentName;

    public InteractNPC(String name) {
        this.name = name;
    }
    public InteractNPC(Component name) {
        this.componentName = name;
    }

    public abstract void onInteract(PlayerInteractEntityEvent event);

    public static class TalkNPC extends InteractNPC {
        public static final HashMap<UUID, TalkNPC> talkingPlayers = new HashMap<>();
        public static final List<UUID> yesNoPlayers = new ArrayList<>();
        public static final HashMap<UUID, Integer> index = new HashMap<>();
        public static final HashMap<UUID, Integer> tasks = new HashMap<>();
        public HashMap<String, DialogueAction> dialogue;
        public float delay;

        public TalkNPC(String name, HashMap<String, DialogueAction> dialogue, float delay) {
            super(name);
            this.dialogue = dialogue;
            this.delay = delay;
        }

        public void onTalk(Player talker, int index) {}
        public void onAfterTalk(Player talker) {}
        public void onAnswerReceive(Player talker, String answer) {}

        protected void sendYesNo(Player player){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Core.INSTANCE, () -> {
                if(yesNoPlayers.contains(player.getUniqueId())) return;
                TextComponent yesMessage = new TextComponent("[ 예 ] ");
                yesMessage.setColor(ChatColor.GREEN);
                yesMessage.setBold(true);
                yesMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/yescmd"));
                TextComponent noMessage = new TextComponent("[ 아니요 ]");
                noMessage.setColor(ChatColor.RED);
                noMessage.setBold(true);
                noMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nocmd"));
                yesMessage.addExtra(noMessage);
                player.spigot().sendMessage(yesMessage);

                talkingPlayers.put(player.getUniqueId(), this);
                yesNoPlayers.add(player.getUniqueId());
            }, 0L);
        }

        protected void talk(Player player, String message, boolean onlyTalk){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Core.INSTANCE, () -> {
                player.sendMessage(name + " §7: §f§l" + message);
                if (!onlyTalk) {
                    onTalk(player, index.get(player.getUniqueId()));
                }
            }, 0L);
        }

        protected void talk(Player player, String message, long delay, boolean onlyTalk){
            Bukkit.getScheduler().runTaskLater(Core.INSTANCE, () -> talk(player, message, onlyTalk), delay);
        }

        @Override
        public void onInteract(PlayerInteractEntityEvent event) {
            Player player = event.getPlayer();
            if(talkingPlayers.containsKey(player.getUniqueId()) || yesNoPlayers.contains(player.getUniqueId())) return;
            if(tasks.containsKey(player.getUniqueId())){
                Bukkit.getScheduler().cancelTask(tasks.get(player.getUniqueId()));
                tasks.remove(player.getUniqueId());
                return;
            }

            talkingPlayers.put(player.getUniqueId(), this);
            index.put(player.getUniqueId(), 0);

            int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.INSTANCE, () -> {
                if(!yesNoPlayers.contains(player.getUniqueId()) && index.containsKey(player.getUniqueId())) {
                    if (index.get(player.getUniqueId()) >= dialogue.size()) {
                        talkingPlayers.remove(player.getUniqueId());
                        index.remove(player.getUniqueId());
                        onAfterTalk(player);
                        return;
                    }
                    talk(player, dialogue.keySet().stream().toList().get(index.get(player.getUniqueId())), false);
                    if (dialogue.values().stream().toList().get(index.get(player.getUniqueId())) == DialogueAction.YES_NO) {
                        sendYesNo(player);
                    }
                    index.put(player.getUniqueId(), index.get(player.getUniqueId()) + 1);
                }
            }, 0L, (long) ((int) delay*20f));
            tasks.put(player.getUniqueId(), taskId);
        }

        public enum DialogueAction {
            TALK,
            YES_NO,
        }
    }
}
