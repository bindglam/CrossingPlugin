package io.github.bindglam.battle.commands;

import io.github.bindglam.battle.MapManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BattleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("battle") || args.length == 0) {
            sendHelpMessage(sender);
            return false;
        }

        try {
            if (args[0].equalsIgnoreCase("createmap")) {
                MapManager.maps.put(player.getWorld().getName(), new MapManager.Map(
                        player.getWorld().getName(),
                        false,
                        new ArrayList<>(),
                        new LinkedList<>(),
                        2
                ));
                player.sendMessage(Component.text("성공적으로 맵을 생성했습니다!"));
            } else if (args[0].equalsIgnoreCase("listmap")) {
                MapManager.maps.keySet().forEach((name) -> player.sendMessage(Component.text(name)));
            } else if (args[0].equalsIgnoreCase("set")) {
                String mapName = args[3];
                if (!MapManager.maps.containsKey(mapName)) {
                    player.sendMessage(Component.text("알맞은 맵 이름이 아닙니다!"));
                    return false;
                }
                MapManager.Map map = MapManager.maps.get(mapName);

                if (args[1].equalsIgnoreCase("spawnlocations")) {
                    int index;
                    try{
                        index = Integer.parseInt(args[2]);
                    } catch (NumberFormatException ignored){
                        player.sendMessage(Component.text("숫자가 아닙니다!"));
                        return false;
                    }
                    map.spawnLocations.add(index, new Location(Bukkit.getWorld(mapName), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(),
                            player.getLocation().getYaw(), player.getLocation().getPitch()));
                    player.sendMessage(Component.text("성공적으로 스폰 위치를 설정했습니다! (" + index + ")"));
                } else if (args[1].equalsIgnoreCase("limitplayercount")) {
                    int count;
                    try{
                        count = Integer.parseInt(args[2]);
                    } catch (NumberFormatException ignored){
                        player.sendMessage(Component.text("숫자가 아닙니다!"));
                        return false;
                    }
                    map.limitPlayerCount = count;
                    player.sendMessage(Component.text("성공적으로 플레이어 수를 설정했습니다!"));
                } else {
                    sendHelpMessage(player);
                    return false;
                }
            } else if(args[0].equalsIgnoreCase("join")){
                MapManager.joinPlayer(player, args[1]);
            } else if(args[0].equalsIgnoreCase("leave")){
                MapManager.leavePlayer(player);
            } else if(args[0].equalsIgnoreCase("removemap")){
                String mapName = args[1];
                if (!MapManager.maps.containsKey(mapName)) {
                    player.sendMessage(Component.text("알맞은 맵 이름이 아닙니다!"));
                    return false;
                }
                MapManager.maps.remove(mapName);
                player.sendMessage(Component.text("성공적으로 맵을 삭제했습니다!"));
            }
        } catch (IndexOutOfBoundsException ignored){
            sendHelpMessage(player);
            return false;
        }
        return false;
    }

    private void sendHelpMessage(CommandSender player){
        player.sendMessage(Component.text("/battle createmap <- 현재 월드를 맵으로 추가").appendNewline()
                .append(Component.text("/battle removemap <맵 이름> <- 맵 삭제")).appendNewline()
                .append(Component.text("/battle listmap <- 맵 목록및 이름 확인")).appendNewline()
                .append(Component.text("/battle set spawnlocations <인덱스> <맵 이름> <- 현재 위치를 스폰 위치 <인덱스>로 설정")).appendNewline()
                .append(Component.text("/battle set limitplayercount <숫자> <맵 이름> <- 맵의 최대 플레이어 접속 수 설정")).appendNewline()
                .append(Component.text("/battle join <맵 이름> <- 맵에 접속")).appendNewline()
                .append(Component.text("/battle leave <- 맵에서 나가기")));
    }
}
