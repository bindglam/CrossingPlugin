package io.github.bindglam.core.npc;

import io.github.bindglam.core.menu.shops.OreShopMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class MinerNPC extends InteractNPC.TalkNPC {
    public MinerNPC() {
        super("§b광부", new HashMap<>(){{
            put("나의 피땀눈물로 채굴한 광물을 사러왔는가?", DialogueAction.TALK);
        }}, 2.0f);
    }

    @Override
    public void onTalk(Player talker, int index) {
        talker.playSound(talker.getLocation(), Sound.ENTITY_VILLAGER_YES, 100f, 0.7f);
    }

    @Override
    public void onAfterTalk(Player talker) {
        new OreShopMenu().open(talker, 0);
    }
}
