package io.github.bindglam.core.items;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.core.utils.InvUtils;
import io.github.bindglam.economy.EconomyManager;
import net.minecraft.SharedConstants;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GoldenHeadItem extends PluginItem {
    public GoldenHeadItem(String name) {
        super(new AdvItemCreator(Material.PLAYER_HEAD).makeHead(name).setDisplayName("§6§l황금머리").getItemStack());
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if(event.getPlayer().isSneaking()) return;
        event.setCancelled(true);
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();

        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 30*20, 5, false, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 2*20, 3, false, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5*20, 1, false, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5*20, 3, false, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10*20, 3, false, true));
        player.sendMessage("§6§l황금머리§a를 먹으셨습니다!");
        InvUtils.consumeItem(player, 1, itemStack);
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 100f, 1.2f);
    }
}
