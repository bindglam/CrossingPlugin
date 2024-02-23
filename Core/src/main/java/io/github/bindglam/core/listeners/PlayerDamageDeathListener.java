package io.github.bindglam.core.listeners;

import dev.lone.itemsadder.api.CustomStack;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.advancements.BossAdvancement;
import io.github.bindglam.core.advancements.KillPlayerAdvancement;
import io.github.bindglam.core.advancements.LostHealthAdvancement;
import io.github.bindglam.core.items.GoldenHeadItem;
import io.github.bindglam.core.items.PluginItemManager;
import io.github.bindglam.core.managers.PrivateSettingManager;
import io.github.bindglam.core.managers.StatsManager;
import io.github.bindglam.core.menu.StealMenu;
import io.github.bindglam.core.utils.AdvancementUtil;
import io.github.bindglam.core.utils.InvUtils;
import io.github.bindglam.economy.EconomyManager;
import io.github.bindglam.ground.GroundManager;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.UUID;

public class PlayerDamageDeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        if(event.getPlayer().hasMetadata("NPC")) return;
        event.deathMessage(Component.text("\uD83D\uDC80").color(TextColor.color(153, 0, 0)).decorate(TextDecoration.BOLD)
                .append(Component.text(" | ").color(TextColor.color(255, 255, 255)).append(Objects.requireNonNull(event.deathMessage()).color(TextColor.color(255, 255, 102)))));
        Player player = event.getEntity();
        StatsManager.Stats stats = StatsManager.getStats(player.getUniqueId());

        if (stats != null && stats.pvpLv >= 25) {
            NamespacedKey invsaveKey = new NamespacedKey(Core.INSTANCE, "invsave");
            boolean hasInvSave = false;
            ListIterator<ItemStack> listIterator;
            for (listIterator = player.getInventory().iterator(); listIterator.hasNext(); ) {
                ItemStack itemStack = listIterator.next();
                if (itemStack != null &&
                        itemStack.hasDisplayName()) {
                    PersistentDataContainer container = itemStack.getPersistentDataContainer();
                    if (container.has(invsaveKey)) {
                        hasInvSave = true;
                        InvUtils.consumeItem(player, 1, itemStack);
                    }
                }
            }
            event.setKeepInventory(hasInvSave);
            if (!hasInvSave)
                for (listIterator = player.getInventory().iterator(); listIterator.hasNext(); ) {
                    ItemStack itemStack = listIterator.next();
                    if (itemStack == null)
                        continue;
                    player.getWorld().dropItem(player.getEyeLocation(), itemStack);
                }
            player.sendMessage(Component.text("§c§l당신의 PvP 레벨이 너무 높아 인벤토리를 보호하지 못했습니다... §7( Lv." + stats.pvpLv + " > 25 )"));
        } else {
            event.setKeepInventory(true);
            if (stats != null)
                player.sendMessage(Component.text("§e§l당신의 PvP 레벨이 낮아 인벤토리를 보호했습니다! §7( Lv." + stats.pvpLv + " < 25 )"));
        }

        if(player.getHealthScale() > 1) {
            AdvancementUtil.awardAdvancement(player, new NamespacedKey(Core.INSTANCE, LostHealthAdvancement.ID), "complete");
            player.setHealthScale(player.getHealthScale() - 1);
            player.sendMessage("§c ! 당신은 죽어서 최대 체력 1칸이 깎였습니다! !");
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event){
        if(event.getEntity().hasMetadata("NPC") || event.getDamager().hasMetadata("NPC")) return;
        if (event.getEntity() instanceof LivingEntity victim) {
            victim.setMaximumNoDamageTicks(10);
            if (victim.getEquipment() != null)
                for (ItemStack armor : ((EntityEquipment)Objects.requireNonNull(victim.getEquipment())).getArmorContents()) {
                    if (armor != null &&
                            armor.getType() != Material.AIR) {
                        ItemMeta meta = armor.getItemMeta();
                        if (meta != null &&
                                meta.getLore() != null) {
                            List<String> lore = meta.getLore();
                            if (lore.size() > 3 && lore.get(3).contains("피해량 감소 +")) {
                                double minusDamageAmount = Double.parseDouble(lore.get(3).split("§6§l피해량 감소 +")[1].replace("%", ""));
                                event.setDamage(event.getDamage() - event.getDamage() * minusDamageAmount / 100.0D);
                            }
                        }
                    }
                }
        }

        if(event.getDamager() instanceof Player damager && event.getEntity() instanceof LivingEntity victim){
            StatsManager.Stats stats = StatsManager.getStats(damager.getUniqueId());

            event.setDamage(event.getDamage() + (double) stats.strongLv / 5.0);

            ItemStack itemStack = damager.getInventory().getItemInMainHand();
            if(itemStack.getType() != Material.AIR){
                ItemMeta meta = itemStack.getItemMeta();
                if(meta != null){
                    if(meta.getLore() != null){
                        List<String> lore = meta.getLore();
                        if(lore.size() > 3 && lore.get(3).contains("피해량 +")) {
                            double plusDamageAmount = Double.parseDouble(lore.get(3).split("§b§l피해량 +")[1].replace("%", ""));
                            event.setDamage(event.getDamage() + event.getDamage() * (plusDamageAmount / 100));
                        }
                    }
                }
            }

            if (victim.getHealth() - event.getFinalDamage() <= 0.0) {
                if (victim instanceof Monster)
                    stats.strongXp += 5;
                else if(victim instanceof Animals)
                    stats.strongXp += 1;
                else if(victim instanceof Player)
                    stats.strongXp += 10;
                StatsManager.update(stats, damager);
            }

            ActiveMob mythicMob = MythicBukkit.inst().getMobManager().getActiveMob(victim.getUniqueId()).orElse(null);
            if(mythicMob != null && mythicMob.getMobType().equals("AncientFighter")){
                AdvancementUtil.awardAdvancement(damager, new NamespacedKey(Core.INSTANCE, BossAdvancement.AncientFighter.ID), "complete");
            } else if(mythicMob != null && mythicMob.getMobType().equals("Hydra")){
                AdvancementUtil.awardAdvancement(damager, new NamespacedKey(Core.INSTANCE, BossAdvancement.Hydra.ID), "complete");
            }
        }

        if(event.getEntity() instanceof Player player && event.getDamager() instanceof Player damager) {
            PrivateSettingManager.PrivateSetting setting = PrivateSettingManager.loadPlayer(player.getUniqueId());
            PrivateSettingManager.PrivateSetting damagerSetting = PrivateSettingManager.loadPlayer(damager.getUniqueId());

            StatsManager.Stats damagerStats = StatsManager.getStats(damager.getUniqueId());

            if((boolean) setting.settings.get("PvP")){
                damager.sendMessage(Component.text(player.getName() + "님은 PvP를 싫어하십니다!").color(TextColor.color(255, 0, 0)).decorate(TextDecoration.BOLD));
                event.setCancelled(true);
                return;
            }

            if((boolean) damagerSetting.settings.get("PvP")){
                damager.sendMessage(Component.text("당신의 PvP 설정이 꺼짐으로 되어있습니다!").color(TextColor.color(255, 0, 0)).decorate(TextDecoration.BOLD));
                event.setCancelled(true);
                return;
            }

            List<Object> data = GroundManager.isInGround(event.getEntity().getLocation());
            if(data != null) {
                UUID owner = GroundManager.grounds.get((Location) data.get(1));
                if (Objects.equals(owner, player.getUniqueId())) {
                    event.setCancelled(true);
                    return;
                }
                if (GroundManager.grounders.containsKey((Location) data.get(1)) && !Objects.equals(owner, player.getUniqueId())){
                    if (GroundManager.grounders.get((Location) data.get(1)).contains(player.getUniqueId())) {
                        event.setCancelled(true);
                    }
                }

                //entity.sendMessage("§c§l이곳은 " + owner + "님의 땅입니다.");
            }

            if (player.getHealth() - event.getFinalDamage() <= 0.0 && player.getInventory().getItemInMainHand().getType() != Material.TOTEM_OF_UNDYING && player.getInventory().getItemInOffHand().getType() != Material.TOTEM_OF_UNDYING) {
                damager.getInventory().addItem(CustomStack.getInstance("golden_head").getItemStack());

                EconomyManager.deposit(damager.getUniqueId(), EconomyManager.getAmount(player.getUniqueId()) / 2);
                damager.sendMessage("§3" + player.getName() + "님을 죽여 §e§l황금머리§3와 §6§l" + EconomyManager.getAmount(player.getUniqueId()) / 2 + "원§3을 얻으셨습니다!");

                player.sendMessage("§4§l" + damager.getName() + "님께 살해당해 §6§l" + EconomyManager.getAmount(player.getUniqueId()) / 2 + "원§4§l을 잃으셨습니다.");
                EconomyManager.withdraw(player.getUniqueId(), EconomyManager.getAmount(player.getUniqueId()) / 2);

                damagerStats.pvpLv++;
                damager.sendMessage(Component.text("§4§l하지만 당신의 PvP 레벨이 상승하였습니다... §7( Lv." + damagerStats.pvpLv + " )"));
                damager.sendMessage(Component.text("§cPvP 레벨이 25렙 이상이 된다면 더 이상 인벤토리가 보호되지 않습니다!"));
                if (damagerStats.pvpLv >= 25)
                    AdvancementUtil.awardAdvancement(damager, new NamespacedKey(Core.INSTANCE, "kill_player_25"), "complete");

                AdvancementUtil.awardAdvancement(damager, new NamespacedKey(Core.INSTANCE, KillPlayerAdvancement.ID), "complete");

                if((boolean) damagerSetting.settings.get("Steal")){
                    new StealMenu(player).open(damager, 0);
                } else {
                    damager.sendMessage(Component.text("당신의 훔치기 설정이 꺼짐으로 되어있습니다!").color(TextColor.color(255, 255, 0)));
                }
            }
        }

        if(event.getFinalDamage() < 0.0){
            event.setDamage(0.0);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity().hasMetadata("NPC")) return;
        if(event.getEntity() instanceof Player player) {
            StatsManager.Stats stats = StatsManager.getStats(player.getUniqueId());

            event.setDamage(event.getDamage() - (double) stats.defenseLv / 10.0);

            if (event.getCause() == EntityDamageEvent.DamageCause.FALL && Math.random() < 0.5) {
                stats.speedXp++;
            }
            StatsManager.update(stats, player);
        }

        if(event.getFinalDamage() < 0.0){
            event.setDamage(0.0);
        }
    }
}
