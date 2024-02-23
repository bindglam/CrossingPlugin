package io.github.bindglam.core.npc;

import io.github.bindglam.core.menu.shops.FoodShopMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class FoodSellerNPC extends InteractNPC.TalkNPC {
    public FoodSellerNPC() {
        super("§e음식 상인", List.of("라면 먹고갈래?"), 2.0f);
    }

    @Override
    public void onTalk(Player talker, int index) {
        talker.playSound(talker.getLocation(), Sound.ENTITY_VILLAGER_YES, 100f, 0.8f);
    }

    @Override
    public void onAfterTalk(Player talker) {
        new FoodShopMenu().open(talker, 0);
    }
}
