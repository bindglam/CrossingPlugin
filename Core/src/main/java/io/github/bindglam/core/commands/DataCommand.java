package io.github.bindglam.core.commands;

import io.github.bindglam.core.Core;
import io.github.bindglam.core.menu.core.StatsMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DataCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, @NotNull String[] args) {
        if(!command.getLabel().equalsIgnoreCase("core-data") || args.length == 0 || !sender.isOp()) return false;

        if(args[0].equalsIgnoreCase("load")){
            Core.loadData();
            Bukkit.broadcast(Component.text("서버 데이터가 로드되었습니다!").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD));
        } else if(args[0].equalsIgnoreCase("save")) {
            Core.saveData(false);
            Bukkit.broadcast(Component.text("서버 데이터가 저장되었습니다!").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD));
        }
        return true;
    }
}
