package io.github.bindglam.core.commands;

import io.github.bindglam.core.menu.core.StatsMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DonateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, @NotNull String[] args) {
        if(!command.getLabel().equalsIgnoreCase("donate")) return false;

        sender.sendMessage("§b§l후원§f은 §9§l투네이션§f으로 진행됩니다.");
        sender.sendMessage(Component.text("링크 : ").color(NamedTextColor.AQUA)
                .append(Component.text("https://toon.at/donate/637537175649089005").color(NamedTextColor.WHITE).decorate(TextDecoration.BOLD).clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, "https://toon.at/donate/637537175649089005")))
                .appendNewline()
                .appendNewline()
                .append(Component.text("1 캐시 = 1 마일리지").color(NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)));
        return true;
    }
}
