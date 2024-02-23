package io.github.bindglam.core.npc;

import io.github.bindglam.core.utils.TeleportUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class TutorialEndNPC extends InteractNPC.TalkNPC {
    public TutorialEndNPC() {
        super("§b§l튜토리얼 §f종료!", List.of("즐거운 게임 바랍니다!"), 2.0f);
    }

    @Override
    public void onTalk(Player talker, int index) {
        talker.playSound(talker.getLocation(), Sound.ENTITY_VILLAGER_YES, 100f, 1.2f);
        talker.playSound(talker.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100f, 2f);
    }

    @Override
    public void onAfterTalk(Player talker) {
        talker.teleportAsync(TeleportUtil.getRandomLoc());
    }
}
