package io.github.bindglam.core.npc;

import io.github.bindglam.core.Core;
import io.github.bindglam.core.advancements.RecoveryHealthAdvancement;
import io.github.bindglam.core.utils.AdvancementUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class InnKeeperNPC extends InteractNPC.TalkNPC {
    public InnKeeperNPC() {
        super("§b§l여관 §f관리원", new HashMap<>(){{
            put("피로를 날리려 오셨군요.", DialogueAction.TALK);
            put("가격은 다이아 16개입니다.", DialogueAction.TALK);
            put("잠시 쉬시겠습니까? (손에 다이아를 들어주세요.)", DialogueAction.YES_NO);
        }}, 2.0f);
    }

    @Override
    public void onTalk(Player talker, int index) {
        talker.playSound(talker.getLocation(), Sound.ENTITY_VILLAGER_YES, 100f, 1.2f);

        if(index == 3){
            sendYesNo(talker);
        }
    }

    @Override
    public void onAfterTalk(Player talker) {
    }

    @Override
    public void onAnswerReceive(Player talker, String answer) {
        if (Objects.equals(answer, "yes")) {
            if(talker.getHealthScale() >= 40.0) {
                talk(talker, "근데 당신은 이미 피로가 회복된 것 같군요...", true);
                talkingPlayers.remove(talker.getUniqueId());
                return;
            }

            ItemStack item = talker.getInventory().getItemInMainHand();
            if(item.getType() != Material.AIR) {
                if (item.getAmount() >= 16 && item.getType() == Material.DIAMOND) {
                    talk(talker, "감사합니다!", true);
                    AdvancementUtil.awardAdvancement(talker, new NamespacedKey(Core.INSTANCE, RecoveryHealthAdvancement.ID), "complete");
                    talker.setHealthScale(talker.getHealthScale() + 2.0);
                    talker.playSound(talker.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100f, 2.5f);
                    talker.playSound(talker.getLocation(), Sound.ENTITY_GENERIC_EAT, 100f, 1.5f);
                    talkingPlayers.remove(talker.getUniqueId());
                    if(talker.getInventory().getItemInMainHand().getAmount()-16 > 0) talker.getInventory().getItemInMainHand().setAmount(talker.getInventory().getItemInMainHand().getAmount()-16);
                    else talker.getInventory().setItemInMainHand(null);
                    return;
                }
            }

            talk(talker, "돈이 부족하시군요.", true);
            talk(talker, "다음에 오십시오.", 2*20, true);
        } else {
            talk(talker, "다음에 오십시오.", true);
        }
        Bukkit.getScheduler().runTaskLater(Core.INSTANCE, () -> talkingPlayers.remove(talker.getUniqueId()), 3*20);
    }
}
