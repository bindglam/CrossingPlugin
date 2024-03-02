package io.github.bindglam.core.items;

import io.github.bindglam.core.Core;
import io.github.bindglam.core.utils.AdvItemCreator;
import io.github.bindglam.economy.EconomyManager;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Objects;

public class CosmeticGetItem extends PluginItem {
    public CosmeticGetItem(String cosmeticKey, String cosmeticName) {
        super(new AdvItemCreator(Material.PAPER).setDisplayName("§b§l치장품 §f§l획득권").setLore(List.of("§b치장품: " + cosmeticName))
                .addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5).addPersistentData("cosmetic-key", cosmeticKey).getItemStack());
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        ItemMeta meta = Objects.requireNonNull(itemStack).getItemMeta();

        String cosmeticKey = meta.getPersistentDataContainer().get(new NamespacedKey(Core.INSTANCE, "cosmetic-key"), PersistentDataType.STRING);
        User user = Core.INSTANCE.luckPerms.getUserManager().getUser(player.getUniqueId());
        Objects.requireNonNull(user).data().add(Node.builder("cosmeticscore.user.cosmetics.wear." + cosmeticKey).build());
        Core.INSTANCE.luckPerms.getUserManager().saveUser(user);

        player.sendMessage("§b§l치장품 §f§l획득권§a을 사용하였습니다!");
        itemStack.setAmount(0);
        player.getInventory().setItemInMainHand(null);
        player.playSound(player.getLocation(), "entity.item.pickup", 100f, 2f);
    }
}
