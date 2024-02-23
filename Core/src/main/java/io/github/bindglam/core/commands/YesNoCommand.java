package io.github.bindglam.core.commands;

import io.github.bindglam.core.npc.InteractNPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class YesNoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("yescmd") && !command.getLabel().equalsIgnoreCase("nocmd") || !(sender instanceof Player player)) return false;
        if(!InteractNPC.TalkNPC.talkingPlayers.containsKey(player.getName()) || !InteractNPC.TalkNPC.yesNoPlayers.contains(player.getName())) return false;

        if(command.getLabel().equalsIgnoreCase("yescmd"))
            InteractNPC.TalkNPC.talkingPlayers.get(player.getName()).onAnswerReceive(player, "yes");
        else if(command.getLabel().equalsIgnoreCase("nocmd"))
            InteractNPC.TalkNPC.talkingPlayers.get(player.getName()).onAnswerReceive(player, "no");

        InteractNPC.TalkNPC.yesNoPlayers.remove(player.getName());
        return true;
    }
}
