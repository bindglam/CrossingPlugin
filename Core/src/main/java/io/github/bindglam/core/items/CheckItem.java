package io.github.bindglam.core.items;

import dev.lone.itemsadder.api.CustomStack;
import io.github.bindglam.core.managers.StatsManager;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.core.utils.InvUtils;
import io.github.bindglam.economy.EconomyManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class CheckItem extends PluginItem {
    public CheckItem(double amount) {
        super(new AdvItemCreator(Material.PAPER).setDisplayName("§f§l수표").setLore(List.of("§b금액: " + String.format("%.1f", amount) + "원")).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5).getItemStack());
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        ItemMeta meta = Objects.requireNonNull(itemStack).getItemMeta();

        double amount = Double.parseDouble(Objects.requireNonNull(meta.getLore()).get(0).split("§b금액: ")[1].replace("원", "")) * itemStack.getAmount();
        EconomyManager.deposit(player.getUniqueId(), amount * itemStack.getAmount());
        player.sendMessage("§a§l수표를 사용하였습니다!");
        itemStack.setAmount(0);
        player.getInventory().setItemInMainHand(null);
        player.playSound(player.getLocation(), "entity.item.pickup", 100f, 2f);
    }
}
