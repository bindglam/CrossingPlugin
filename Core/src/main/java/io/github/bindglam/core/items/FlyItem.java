package io.github.bindglam.core.items;

import io.github.bindglam.core.managers.FlyManager;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.economy.EconomyManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class FlyItem extends PluginItem {
    public FlyItem(int time) {
        super(new AdvItemCreator(Material.PAPER).setDisplayName("§f§l플라이 §b§l이용권").setLore(List.of("§b시간: " + time + "초")).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5).getItemStack());
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        ItemMeta meta = Objects.requireNonNull(itemStack).getItemMeta();

        int time = Integer.parseInt(Objects.requireNonNull(meta.getLore()).get(0).split("§b시간: ")[1].replace("초", "")) * itemStack.getAmount();
        FlyManager.flyTimes.put(player.getUniqueId(), FlyManager.flyTimes.get(player.getUniqueId())+time*20);
        player.sendMessage("§a§l플라이 이용권을 사용하였습니다!");
        itemStack.setAmount(0);
        player.getInventory().setItemInMainHand(null);
        player.playSound(player.getLocation(), "entity.item.pickup", 100f, 2f);
    }
}
