package io.github.bindglam.core.npc;

import io.github.bindglam.core.menu.shops.MiscShopMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class SodaSellerNPC extends InteractNPC.TalkNPC {
    public SodaSellerNPC() {
        super("§d음료수 상인", new HashMap<>(){{
            put("학생! 피곤해 보이는구만?", DialogueAction.TALK);
            put("음료수들 구경하고 가~", DialogueAction.TALK);
        }}, 2.0f);
    }

    @Override
    public void onTalk(Player talker, int index) {
        talker.playSound(talker.getLocation(), Sound.ENTITY_VILLAGER_YES, 100f, 1.3f);
    }

    @Override
    public void onAfterTalk(Player talker) {
        new MiscShopMenu().open(talker, 0);
    }
}
