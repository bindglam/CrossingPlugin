package io.github.bindglam.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class FloorCleanerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("floorcleaner") || args.length < 1) return false;

        World world = Bukkit.getWorld(args[0]);
        if(world != null){
            for(Item item : world.getEntitiesByClass(Item.class)){
                item.remove();
                new Thread(() -> Bukkit.getOnlinePlayers().forEach((player) -> player.sendMessage("§f[ §b§l바닥§f§l청소 §f] §7바닥에 떨어진 모든 아이템을 삭제했습니다!"))).start();
            }
        }
        return true;
    }
}
