package io.github.bindglam.core.npc;

import io.github.bindglam.core.menu.shops.MiscShopMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class LibrarianNPC extends InteractNPC.TalkNPC {
    public LibrarianNPC() {
        super("§d사서", List.of("학생! 이 책 읽어볼래?", "꼭 책이 아니여도 다른 거 많아!"), 2.0f);
    }

    @Override
    public void onTalk(Player talker, int index) {
        talker.playSound(talker.getLocation(), Sound.ENTITY_VILLAGER_YES, 100f, 1f);
    }

    @Override
    public void onAfterTalk(Player talker) {
        new MiscShopMenu().open(talker, 0);
    }
}
