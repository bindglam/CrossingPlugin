package io.github.bindglam.core.managers;

import com.outstandingboy.donationalert.platform.Toonation;
import io.github.bindglam.core.Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Objects;

public class DonateManager {
    public static Toonation toonation;

    public static void init() {
        try {
            // Toonation Alertbox URL의 마지막 https://toon.at/widget/alertbox/<YOUR_TOONATION_KEY> 부분을 입력하세요.
            toonation = new Toonation("88968eed1f45973a30056845d7b9d654");
        } catch (IOException e){
            Core.INSTANCE.getLogger().severe("어라? 투네이션을 감지할 수 없습니다!");
            return;
        }

        toonation.subscribeMessage(s -> Core.INSTANCE.getLogger().info(s));

        toonation.subscribeDonation(donation -> {
            Core.INSTANCE.getLogger().info("[Toonation] " + donation.getNickName() + "님이 " + donation.getAmount() + "원을 후원했습니다!");
            Core.INSTANCE.getLogger().info("[Toonation] 후원 내용 : " + donation.getComment());

            OfflinePlayer patronOffline = Bukkit.getOfflinePlayer(donation.getNickName());
            if(Objects.equals(donation.getNickName(), "로얄감자"))
                patronOffline = Bukkit.getOfflinePlayer("Octtato");
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(Component.text(donation.getNickName()).decorate(TextDecoration.BOLD).color(NamedTextColor.WHITE)
                        .append(Component.text("님께서 후원을 해주셨습니다! ( 금액 : " + donation.getAmount() + "캐시 )").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD)).appendNewline()
                        .append(Component.text("후원 내용 ").color(NamedTextColor.YELLOW).append(Component.text(": ").color(NamedTextColor.GRAY)
                                .append(Component.text(donation.getComment()).color(NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)))));
            }

            if(!DonatePointManager.donatePoints.containsKey(patronOffline.getUniqueId()))
                DonatePointManager.donatePoints.put(patronOffline.getUniqueId(), 0);
            DonatePointManager.donatePoints.put(patronOffline.getUniqueId(), (int) (DonatePointManager.donatePoints.get(patronOffline.getUniqueId())+donation.getAmount()));
        });
    }
}
