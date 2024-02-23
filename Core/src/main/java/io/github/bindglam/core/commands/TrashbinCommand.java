package io.github.bindglam.core.commands;

import io.github.bindglam.economy.EconomyManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public class TrashbinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("trashbin")) return false;

        player.openInventory(Bukkit.createInventory(null, InventoryType.CHEST, Component.text("쓰레기통")));
        player.playSound(player.getLocation(), "block.chest.open", 50f, 1.5f);
        return true;
    }
}
