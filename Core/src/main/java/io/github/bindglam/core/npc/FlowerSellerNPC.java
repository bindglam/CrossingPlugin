package io.github.bindglam.core.npc;

import io.github.bindglam.core.menu.shops.CropShopMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class FlowerSellerNPC extends InteractNPC.TalkNPC {
    public FlowerSellerNPC() {
        super("§d장식 상인", List.of("총각! 예쁜 꽃들 많지?", "꽃들 구경하고 가~"), 2.0f);
    }

    @Override
    public void onTalk(Player talker, int index) {
        talker.playSound(talker.getLocation(), Sound.ENTITY_VILLAGER_YES, 100f, 1.3f);
    }

    @Override
    public void onAfterTalk(Player talker) {
        new CropShopMenu().open(talker, 0);
    }
}
