package io.github.bindglam.ground;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.google.common.collect.Lists;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import io.github.bindglam.economy.EconomyManager;
import io.github.bindglam.ground.listeners.PlayerListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class GroundPlugin extends JavaPlugin {
    public static GroundPlugin INSTANCE;

    public ProtocolManager protocolManager;
    public MultiverseCore multiverseCore;
    public MVWorldManager worldManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        protocolManager = ProtocolLibrary.getProtocolManager();
        multiverseCore = (MultiverseCore) getServer().getPluginManager().getPlugin("Multiverse-Core");
        assert multiverseCore != null;
        worldManager = multiverseCore.getMVWorldManager();

        getServer().getPluginManager().registerEvents(new GroundManager(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getConfig().options().copyDefaults(true);
        saveConfig();

        GroundManager.load();
    }

    @Override
    public void onDisable() {
        GroundManager.save();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("ground") || args.length < 1) return false;

        if(args[0].equalsIgnoreCase("생성")){
            if(!player.getWorld().getName().contains("world")){
                player.sendMessage("§c§l이 월드에서는 땅을 만들 수 없습니다.");
                return false;
            }
            if(GroundManager.canMakeGround.get(player.getUniqueId()) <= 0){
                player.sendMessage("§c§l땅을 생성할 수 없습니다.");
                return false;
            }

            if(GroundManager.isOverlappingGround(player.getLocation()) != null){
                player.sendMessage("§c§l다른 땅과 겹칩니다.");
                return false;
            }

            if(GroundManager.canMakeGround.get(player.getUniqueId()) != GroundManager.MAX_MAKE_GROUND){
                if(EconomyManager.getAmount(player.getUniqueId()) < GroundManager.getGroundCount(player.getUniqueId())*50000){
                    player.sendMessage(Component.text("소지금이 부족합니다!").color(TextColor.color(255, 0, 0)));
                    return false;
                }
                EconomyManager.withdraw(player.getUniqueId(), GroundManager.getGroundCount(player.getUniqueId())*50000);
            }

            Location loc = player.getLocation(); loc.setYaw(0f); loc.setPitch(0f);
            GroundManager.grounds.put(loc, player.getUniqueId());
            GroundManager.canMakeGround.put(player.getUniqueId(), GroundManager.canMakeGround.get(player.getUniqueId()) - 1);
            GroundManager.grounders.put(loc, Lists.newArrayList());
            player.sendMessage("땅을 생성하였습니다!");
            Bukkit.dispatchCommand(player, "땅 정보");
        } else if(args[0].equalsIgnoreCase("땅원") && args.length >= 4){
            if(args[1].equalsIgnoreCase("추가")){
                Location loc = getGroundLoc(player, Integer.parseInt(args[2]));
                if(loc == null) return false;

                OfflinePlayer grounderOffline = Bukkit.getOfflinePlayer(args[3]);
                if(GroundManager.grounders.get(loc).contains(grounderOffline.getUniqueId())){
                    player.sendMessage("§c§l이미 그 플레이어는 땅원입니다.");
                    return false;
                }
                Player grounder = Bukkit.getPlayer(args[3]);
                if(grounder != null) grounder.sendMessage("당신은 " + player.getName() + "님의 땅의 땅원으로 초대받았습니다!");

                GroundManager.grounders.get(loc).add(grounderOffline.getUniqueId());
                player.sendMessage("§a§l성공적으로 땅원을 초대했습니다!");
            } else if(args[1].equalsIgnoreCase("삭제")){
                Location loc = getGroundLoc(player, Integer.parseInt(args[2]));
                if(loc == null) return false;

                OfflinePlayer grounderOffline = Bukkit.getOfflinePlayer(args[3]);

                if(!GroundManager.grounders.get(loc).contains(grounderOffline.getUniqueId())){
                    player.sendMessage("§c§l그 플레이어는 땅원이 아닙니다.");
                    return false;
                }

                GroundManager.grounders.get(loc).remove(grounderOffline.getUniqueId());
                player.sendMessage("§a§l성공적으로 땅원을 삭제했습니다!");
            }
        } else if(args[0].equalsIgnoreCase("목록")){
            player.sendMessage("§f§l -- 땅 목록 -- ");
            for(int i = 0; i < GroundManager.grounds.keySet().size(); i++){
                Location loc = GroundManager.grounds.keySet().stream().toList().get(i);
                if(!Objects.equals(GroundManager.grounds.get(loc), player.getUniqueId())) continue;

                player.sendMessage( i + "번 : X: " + loc.getBlockX() + ", Y: " + loc.getBlockY() + ", Z: " + loc.getBlockZ() + ", 세계: " + loc.getWorld().getName());
            }
        } else if(args[0].equalsIgnoreCase("삭제") && args.length >= 2){
            Location loc = getGroundLoc(player, Integer.parseInt(args[1]));
            if(loc == null) return false;

            GroundManager.grounds.remove(loc);
            GroundManager.grounders.remove(loc);
            GroundManager.canMakeGround.put(player.getUniqueId(), GroundManager.canMakeGround.get(player.getUniqueId()) + 1);
            player.sendMessage("§a§l성공적으로 땅을 삭제했습니다.");
        } else if(args[0].equalsIgnoreCase("생성가능횟수")){
            player.sendMessage("§e" + GroundManager.canMakeGround.get(player.getUniqueId()) + "번 생성 가능합니다!");
            if(args.length >= 4 && player.isOp()){
                if(args[1].equalsIgnoreCase("추가")){
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[2]);
                    GroundManager.canMakeGround.put(offlinePlayer.getUniqueId(), GroundManager.canMakeGround.get(offlinePlayer.getUniqueId())+Integer.parseInt(args[3]));
                }
            }
        } else if(args[0].equalsIgnoreCase("정보")) {
            player.sendMessage(Component.text("땅 갯수 : " + GroundManager.getGroundCount(player.getUniqueId())).color(TextColor.color(255, 255, 0)));
            player.sendMessage(Component.text("다음 땅 생성시, 비용 : " + GroundManager.getGroundCount(player.getUniqueId())*50000).color(TextColor.color(255, 255, 0)));
        } else {
            player.sendMessage(Component.text(" - 사용법 - ").color(TextColor.color(200, 150, 0)).decorate(TextDecoration.BOLD));
            player.sendMessage(Component.text("/땅 생성").color(TextColor.color(255, 255, 255)));
            player.sendMessage(Component.text("/땅 삭제 <땅 번호>").color(TextColor.color(255, 255, 255)));
            player.sendMessage(Component.text("/땅 목록").color(TextColor.color(255, 255, 255)));
            player.sendMessage(Component.text("/땅 생성가능횟수").color(TextColor.color(255, 255, 255)));
            player.sendMessage(Component.text("/땅 정보").color(TextColor.color(255, 255, 255)));
            player.sendMessage(Component.text("/땅 땅원 추가 <땅 번호> <플레이어 이름>").color(TextColor.color(255, 255, 255)));
            player.sendMessage(Component.text("/땅 땅원 삭제 <땅 번호> <플레이어 이름>").color(TextColor.color(255, 255, 255)));
        }
        return true;
    }

    private Location getGroundLoc(Player player, int num){
        Location loc;
        try {
            if(GroundManager.grounds.keySet().stream().toList().size()-1 < num){
                player.sendMessage("§c§l가진 땅의 번호가 아닙니다.");
                return null;
            }
            loc = GroundManager.grounds.keySet().stream().toList().get(num);
        } catch (NumberFormatException e){
            player.sendMessage("§c§l숫자가 아닙니다.");
            return null;
        }
        if(!Objects.equals(GroundManager.grounds.get(loc), player.getUniqueId())){
            player.sendMessage("§c§l땅 주인이 아닙니다.");
            return null;
        }
        return loc;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(!(sender instanceof Player)) return null;
        if(args.length == 1){
            return List.of("생성", "삭제", "땅원", "목록", "정보", "생성가능횟수");
        }
        return null;
    }
}
