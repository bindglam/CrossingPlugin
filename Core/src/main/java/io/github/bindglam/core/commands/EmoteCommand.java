package io.github.bindglam.core.commands;

import dev.lone.itemsadder.api.CustomPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class EmoteCommand implements CommandExecutor {
    public static final HashMap<String, String> emotes = new HashMap<>(){{
        put("박수", "clap");
        put("구르기", "roll");
        put("그래", "yes");
        put("싫어", "no");
        put("춤1", "fortinayt");
        put("머리마술", "magic_trick_head");
        put("안녕!", "wave");
        put("앉기", "sit");
    }};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("crossemote")) return false;

        if(args.length > 0){
            if(emotes.containsKey(args[0])){
                CustomPlayer.playEmote(player, emotes.get(args[0]));
                player.sendMessage(Component.text("감정표현 ").color(TextColor.color(190, 255, 177))
                        .append(Component.text(args[0]).color(TextColor.color(0, 255, 0)).decorate(TextDecoration.BOLD))
                        .append(Component.text("을(를) 사용했습니다!").color(TextColor.color(190, 255, 177))));
                player.sendMessage(Component.keybind("key.sneak").color(TextColor.color(255, 255, 177)).decorate(TextDecoration.BOLD)
                        .append(Component.text("로 감정표현을 멈출 수 있습니다!").color(TextColor.color(0, 200, 255))));
            } else {
                player.sendMessage(Component.text("입력한 이름의 감정표현은 없습니다.").color(TextColor.color(255, 0, 0)));
            }
        }
        return true;
    }
}
