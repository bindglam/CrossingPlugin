package io.github.bindglam.core.commands;

import io.github.bindglam.core.menu.shops.ShopMenu;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class HatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("hat")) return false;
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if(itemStack.getType() == Material.AIR) return false;

        if(player.getInventory().getHelmet() != null)
            player.getInventory().addItem(Objects.requireNonNull(player.getInventory().getHelmet()));

        player.getInventory().setHelmet(itemStack);
        itemStack.setAmount(0);
        itemStack.setType(Material.AIR);
        return true;
    }
}
