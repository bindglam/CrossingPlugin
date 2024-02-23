package io.github.bindglam.core.npc;

import io.github.bindglam.core.Core;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public abstract class InteractNPC {
    public String name;

    public InteractNPC(String name) {
        this.name = name;
    }

    public abstract void onInteract(PlayerInteractEntityEvent event);

    public static class TalkNPC extends InteractNPC {
        public static final HashMap<String, TalkNPC> talkingPlayers = new HashMap<>();
        public static final List<String> yesNoPlayers = new ArrayList<>();
        private final List<String> dialogue;
        private final float delay;
        private static final ConcurrentHashMap<String, Integer> index = new ConcurrentHashMap<>();
        private boolean canAnswer = false;

        public TalkNPC(String name, List<String> dialogue, float delay) {
            super(name);
            this.dialogue = dialogue;
            this.delay = delay;
        }

        public TalkNPC(String name, List<String> dialogue, float delay, boolean canAnswer) {
            super(name);
            this.dialogue = dialogue;
            this.delay = delay;
            this.canAnswer = canAnswer;
        }

        public void onTalk(Player talker, int index) {}
        public void onAfterTalk(Player talker) {}
        public void onAnswerReceive(Player talker, String answer) {}

        protected void sendYesNo(Player player){
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

            talkingPlayers.put(player.getName(), this);
            yesNoPlayers.add(player.getName());
        }

        protected void talk(Player player, String message){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Core.INSTANCE, () -> {
                player.sendMessage(name + " §7: §f§l" + message);
                onTalk(player, index.get(player.getName()));
            }, 0L);
        }

        protected void talk(Player player, String message, long delay){
            Bukkit.getScheduler().runTaskLater(Core.INSTANCE, () -> talk(player, message), delay);
        }

        @Override
        public void onInteract(PlayerInteractEntityEvent event) {
            Player player = event.getPlayer();
            if(talkingPlayers.containsKey(player.getName()) || yesNoPlayers.contains(player.getName())) return;
            talkingPlayers.put(player.getName(), this);
            index.put(player.getName(), 0);

            new Thread(() -> {
                while(true){
                    if(index.get(player.getName()) >= dialogue.size()){
                        if(!canAnswer) talkingPlayers.remove(player.getName());
                        index.put(player.getName(), 0);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.INSTANCE, () -> onAfterTalk(player), 0L);
                        break;
                    }

                    talk(player, dialogue.get(index.get(player.getName())));
                    index.put(player.getName(), index.get(player.getName())+1);
                    try {
                        Thread.sleep((long) (delay*1000));
                    } catch (InterruptedException ignored) {
                    }
                }
            }).start();
        }
    }
}
