package io.github.bindglam.core.npc;

import io.github.bindglam.core.menu.shops.CropShopMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class FarmerNPC extends InteractNPC.TalkNPC {
    public FarmerNPC() {
        super("§6농부", List.of("신선한 농작물 팝니다!", "아이고, 학생!", "나의 농작물들을 사러왔는감?"), 2.0f);
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
