package io.github.bindglam.core.commands;

import io.github.bindglam.core.Core;
import io.github.bindglam.core.advancements.SendMoneyAdvancement;
import io.github.bindglam.core.advancements.SendMoneyKingAdvancement;
import io.github.bindglam.core.managers.EventCoinManager;
import io.github.bindglam.core.utils.AdvancementUtil;
import io.github.bindglam.economy.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Objects;

public class SendMoneyCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("sendmoney") || args.length < 2) return false;

        int cost;
        try {
            cost = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            sender.sendMessage("§c§l숫자가 아닙니다!");
            return false;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);

        if (EconomyManager.getAmount(player.getUniqueId()) < cost) {
            sender.sendMessage("§c§l입력한 액수가 소지금보다 많습니다!");
            return false;
        }

        if (cost <= 0) {
            sender.sendMessage("§c§l금액이 0보다 작으면 안됩니다!");
            return false;
        }

        if (player.getUniqueId() == offlinePlayer.getUniqueId()) {
            sender.sendMessage("§c§l자기자신에게는 보낼 수 없습니다!");
            return false;
        }

        sender.sendMessage("§a성공적으로 송금하였습니다! ( 금액 : " + cost + " 원 )");
        if(Bukkit.getPlayer(offlinePlayer.getUniqueId()) != null){
            Objects.requireNonNull(Bukkit.getPlayer(offlinePlayer.getUniqueId())).sendMessage("§a§l성공적으로 송금받으셨습니다! ( 금액 : " + cost + " 원 )");
        }
        EconomyManager.deposit(offlinePlayer.getUniqueId(), cost);
        EconomyManager.withdraw(player.getUniqueId(), cost);
        AdvancementUtil.awardAdvancement(player, new NamespacedKey(Core.INSTANCE, SendMoneyAdvancement.ID), "complete");
        if(cost >= 5000000)
            AdvancementUtil.awardAdvancement(player, new NamespacedKey(Core.INSTANCE, SendMoneyKingAdvancement.ID), "complete");
        return true;
    }
}
