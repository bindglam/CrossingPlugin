package io.github.bindglam.core.commands;

import io.github.bindglam.core.Core;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.economy.EconomyManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BasicItemsCommand implements CommandExecutor {
    private static final List<String> gotBasicItemsPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("basicitems")) return false;
        if(args.length < 1) {
            if (gotBasicItemsPlayers.contains(player.getName())) {
                player.sendMessage("§c§l이미 기본템을 지급 받았습니다.");
                return false;
            }

            player.getInventory().addItem(new AdvItemCreator(Material.IRON_PICKAXE).setDisplayName("§e기본 곡괭이")
                    .addEnchantment(Enchantment.DIG_SPEED, 3).addEnchantment(Enchantment.DURABILITY, 2)
                    .getItemStack());
            player.getInventory().addItem(new AdvItemCreator(Material.IRON_SWORD).setDisplayName("§e기본 검")
                    .addEnchantment(Enchantment.DAMAGE_ALL, 1).addEnchantment(Enchantment.DURABILITY, 2)
                    .getItemStack());
            player.getInventory().addItem(new AdvItemCreator(Material.IRON_AXE).setDisplayName("§e기본 도끼")
                    .addEnchantment(Enchantment.DIG_SPEED, 3).addEnchantment(Enchantment.DURABILITY, 2)
                    .getItemStack());
            player.getInventory().addItem(new AdvItemCreator(Material.IRON_SHOVEL).setDisplayName("§e기본 삽")
                    .addEnchantment(Enchantment.DIG_SPEED, 4).addEnchantment(Enchantment.DURABILITY, 3)
                    .getItemStack());
            player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));
            player.sendMessage("§a기본템을 지급 받았습니다!");
            gotBasicItemsPlayers.add(player.getName());
        } else if(args[0].equalsIgnoreCase("clear") && player.isOp()) {
            gotBasicItemsPlayers.remove(player.getName());
        }
        return true;
    }

    public static void init(){
        Bukkit.broadcast(Component.text("기본템 정보 로드중...").color(NamedTextColor.WHITE));
        gotBasicItemsPlayers.addAll(Core.INSTANCE.getConfig().getStringList("gotBasicItemsPlayers"));
    }

    public static void save(){
        Bukkit.broadcast(Component.text("기본템 저장중...").color(NamedTextColor.WHITE));
        Core.INSTANCE.getConfig().set("gotBasicItemsPlayers", gotBasicItemsPlayers);
    }
}
