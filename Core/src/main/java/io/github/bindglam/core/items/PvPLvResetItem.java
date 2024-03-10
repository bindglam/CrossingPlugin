package io.github.bindglam.core.items;

import io.github.bindglam.core.managers.StatsManager;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.core.utils.InvUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PvPLvResetItem extends PluginItem {
    public PvPLvResetItem() {
        super(new AdvItemCreator(Material.PAPER).addEnchantment(Enchantment.MENDING, 10).setDisplayName("§c§lPvP 레벨 초기화권").getItemStack());
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        if(event.getPlayer().isSneaking()) return;
        event.setCancelled(true);
        Player player = event.getPlayer();
        StatsManager.Stats stats = StatsManager.getStats(player.getUniqueId());

        stats.pvpLv = 0;
        player.sendMessage(Component.text("PvP 레벨을 초기화했습니다! 앞으로는 조심해주세요~").color(TextColor.color(255, 255, 0)));
        player.playSound(player.getLocation(), Sound.ENTITY_PIG_HURT, 100f, 1.2f);
        InvUtils.consumeItem(player, 1, player.getInventory().getItemInMainHand());
    }
}
