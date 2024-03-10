package io.github.bindglam.core.commands;

import io.github.bindglam.core.managers.StockManager;
import io.github.bindglam.core.menu.core.StockMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StockCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player) || !command.getLabel().equalsIgnoreCase("stock") || args.length < 1) return false;

        ///주식 추가 [주식이름] [주식갯수] [주식가격]
        if(args[0].equalsIgnoreCase("추가") && args.length >= 4 && player.isOp()){
            StockManager.stocks.put(args[1], new ArrayList<>(List.of(
                    Integer.parseInt(args[3]), //상장가
                    Integer.parseInt(args[3]), //매매가
                    Integer.parseInt(args[3]), //상한가
                    Integer.parseInt(args[3]), //하한가
                    Integer.parseInt(args[3]), //최대 변동가
                    Integer.parseInt(args[2]), //최대 갯수
                    Integer.parseInt(args[2]), //현재 갯수
                    "0"                      //기록
            )));
            player.sendMessage(Component.text("주식을 생성하였습니다!").color(TextColor.color(0, 255, 0)));
        } else if(args[0].equalsIgnoreCase("삭제") && args.length >= 2 && player.isOp()){
            if(StockManager.stocks.containsKey(args[1])) {
                StockManager.stocks.remove(args[1]);
                player.sendMessage(Component.text("주식을 삭제하였습니다!").color(TextColor.color(255, 255, 0)));
            } else {
                player.sendMessage(Component.text("입력한 주식은 존재하지 않습니다!").color(TextColor.color(255, 0, 0)));
            }
        } else if(args[0].equalsIgnoreCase("열기")) {
            new StockMenu().open(player, 0);
            player.sendMessage(Component.text("주식을 판매하실때 아이템을 나누어서 인벤토리에 저장해주세요!").color(TextColor.color(255, 255, 0)));
            player.playSound(player.getLocation(), "block.ender_chest.open", 100f, 1.4f);
        }
        return true;
    }
}
