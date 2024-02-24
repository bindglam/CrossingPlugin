package io.github.bindglam.economy;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Economy extends JavaPlugin {
    public static Economy INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        try {
            EconomyManager.init();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        try {
            EconomyManager.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("economy") && !command.getLabel().equalsIgnoreCase("bank") || args.length < 3) return false;

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);

        double amount;
        try {
            amount = Double.parseDouble(args[2]);
        } catch (NumberFormatException ignored){
            sender.sendMessage("숫자가 아닙니다!");
            return false;
        }

        if(command.getLabel().equalsIgnoreCase("economy")) {
            if (args[0].equalsIgnoreCase("deposit")) {
                EconomyManager.deposit(offlinePlayer.getUniqueId(), amount);
                sender.sendMessage("성공적으로 입금했습니다!");
            } else if (args[0].equalsIgnoreCase("withdraw")) {
                EconomyManager.withdraw(offlinePlayer.getUniqueId(), amount);
                sender.sendMessage("성공적으로 출금했습니다!");
            }
        } else {
            if (args[0].equalsIgnoreCase("deposit")) {
                EconomyManager.depositBank(offlinePlayer.getUniqueId(), amount);
                sender.sendMessage("성공적으로 입금했습니다! ( 은행 )");
            } else if (args[0].equalsIgnoreCase("withdraw")) {
                EconomyManager.withdrawBank(offlinePlayer.getUniqueId(), amount);
                sender.sendMessage("성공적으로 출금했습니다! ( 은행 )");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("economy") && !command.getLabel().equalsIgnoreCase("bank")) return null;
        if(args.length == 1){
            return List.of("deposit", "withdraw");
        } else if(args.length == 2){
            List<String> players = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach((player) -> players.add(player.getName()));
            return players;
        } else if(args.length == 3){
            return List.of("< 숫자 >");
        }
        return null;
    }
}
