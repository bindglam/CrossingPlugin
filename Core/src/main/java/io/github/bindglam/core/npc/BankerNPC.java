package io.github.bindglam.core.npc;

import io.github.bindglam.core.menu.npc.BankerMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class BankerNPC extends InteractNPC.TalkNPC {
    public BankerNPC() {
        super("§e은행원", List.of("무엇이 필요하신가요?"), 2.0f);
    }

    @Override
    public void onTalk(Player talker, int index) {
        talker.playSound(talker.getLocation(), Sound.ENTITY_VILLAGER_YES, 100f, 1.2f);
    }

    @Override
    public void onAfterTalk(Player talker) {
        new BankerMenu(talker).open(talker, 0);
    }
}
