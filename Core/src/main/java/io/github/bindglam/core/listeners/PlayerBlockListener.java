package io.github.bindglam.core.listeners;

import dev.lone.itemsadder.api.CustomBlock;
import io.github.bindglam.core.Core;
import io.github.bindglam.core.advancements.GetRubyAdvancement;
import io.github.bindglam.core.advancements.SpawnBossAdvancement;
import io.github.bindglam.core.managers.StatsManager;
import io.github.bindglam.core.menu.blocks.EnhanceMenu;
import io.github.bindglam.core.utils.AdvancementUtil;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class PlayerBlockListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();

        if (event.getItemInHand().hasDisplayName() && (
                EnhanceMenu.additionalEnhanceAbleArmors.contains(event.getItemInHand().getDisplayName()) || EnhanceMenu.additionalEnhanceAbleWeapons
                        .contains(event.getItemInHand().getDisplayName())))
            event.setCancelled(true);

        spawnHirobin(event);
        spawnHydra(event);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        StatsManager.Stats stats = StatsManager.getStats(player.getUniqueId());
        Block block = event.getBlock();

        if(block.getType().name().toLowerCase().contains("ore"))
            stats.defenseXp++;
        StatsManager.update(stats, player);

        if(CustomBlock.byAlreadyPlaced(event.getBlock()) != null){
            if(Objects.equals(CustomBlock.byAlreadyPlaced(event.getBlock()).getNamespacedID(), "minecraftcross:ruby_ore")){
                AdvancementUtil.awardAdvancement(player, new NamespacedKey(Core.INSTANCE, GetRubyAdvancement.ID), "complete");
            }
        }
    }

    private void spawnHirobin(BlockPlaceEvent event) {
        /*  68 */     if (event.getBlock().getType() == Material.DRAGON_HEAD &&
                /*  69 */       event.getBlock().getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() == Material.SOUL_SOIL && event.getBlock().getLocation().subtract(0.0D, 2.0D, 0.0D).getBlock().getType() == Material.SOUL_SOIL) {
            /*  70 */       event.getBlock().setType(Material.AIR);
            /*  71 */       event.getBlock().getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().setType(Material.AIR);
            /*  72 */       event.getBlock().getLocation().subtract(0.0D, 2.0D, 0.0D).getBlock().setType(Material.AIR);
            /*     */
            /*  74 */       AdvancementUtil.awardAdvancement(event.getPlayer(), new NamespacedKey((Plugin)Core.INSTANCE, "spawn_boss"), "complete");
            /*     */
            /*  76 */       MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob("AncientFighter").orElse(null);
            /*  77 */       Location spawnLocation = event.getBlock().getLocation().add(0.0D, 2.0D, 0.0D);
            /*  78 */       if (mob != null) {
                /*  79 */         ActiveMob fighter = mob.spawn(BukkitAdapter.adapt(spawnLocation), 1.0D);
                /*  80 */         Husk entity = (Husk)fighter.getEntity().getBukkitEntity();
                /*  81 */         entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255, false, false));
                /*  82 */         entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 255, false, false));
                /*  83 */         for (Player nearByPlayer : entity.getLocation().getNearbyPlayers(50.0D)) {
                    /*  84 */           nearByPlayer.playSound(nearByPlayer.getLocation(), "entity.wither.spawn", 100.0F, 0.8F);
                    /*     */         }
                /*  86 */         entity.getWorld().spawnParticle(Particle.CLOUD, entity.getEyeLocation(), 200, 0.0D, 0.0D, 0.0D, 0.10000000149011612D);
                /*  87 */         Bukkit.getScheduler().runTaskLater((Plugin)Core.INSTANCE, () -> entity.getWorld().createExplosion(entity.getLocation(), 10.0F), 100L);
                /*     */       }
            /*     */     }
        /*     */   }
    /*     */
    /*     */
    /*     */   private void spawnHydra(BlockPlaceEvent event) {
        /*  94 */     if (event.getBlock().getType() == Material.WITHER_ROSE &&
                /*  95 */       isPerfectPlaceForHydra(event.getBlock().getLocation())) {
            /*  96 */       AdvancementUtil.awardAdvancement(event.getPlayer(), new NamespacedKey((Plugin)Core.INSTANCE, "spawn_boss"), "complete");
            /*     */
            /*  98 */       MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob("Hydra").orElse(null);
            /*  99 */       Location spawnLocation = event.getBlock().getLocation();
            /* 100 */       if (mob != null) {
                /* 101 */         event.getBlock().getLocation().createExplosion(10.0F);
                /* 102 */         ActiveMob hydra = mob.spawn(BukkitAdapter.adapt(spawnLocation), 1.0D);
                /* 103 */         Husk entity = (Husk)hydra.getEntity().getBukkitEntity();
                /* 104 */         entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 255, false, false));
                /* 105 */         entity.getWorld().spawnParticle(Particle.CLOUD, entity.getEyeLocation(), 200, 0.0D, 0.0D, 0.0D, 0.10000000149011612D);
                /* 106 */         event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 255, false, false));
                /* 107 */         for (Player nearByPlayer : entity.getLocation().getNearbyPlayers(50.0D)) {
                    /* 108 */           nearByPlayer.playSound(nearByPlayer.getLocation(), "entity.wither.spawn", 100.0F, 0.8F);
                    /*     */         }
                /*     */       }
            /*     */     }
        /*     */   }
    /*     */
    /*     */
    /*     */   private boolean isPerfectPlaceForHydra(Location loc) {
        /* 116 */     if (loc.getBlock().getType() != Material.WITHER_ROSE) return false;
        /* 117 */     return ((loc.add(1.0D, 0.0D, 0.0D).getBlock().getType() == Material.CRYING_OBSIDIAN && loc.add(0.0D, 0.0D, 1.0D).getBlock().getType() == Material.CRYING_OBSIDIAN) || (loc
/* 118 */       .add(-1.0D, 0.0D, 0.0D).getBlock().getType() == Material.CRYING_OBSIDIAN && loc.add(0.0D, 0.0D, 1.0D).getBlock().getType() == Material.CRYING_OBSIDIAN) || (loc
/* 119 */       .add(1.0D, 0.0D, 0.0D).getBlock().getType() == Material.CRYING_OBSIDIAN && loc.add(0.0D, 0.0D, -1.0D).getBlock().getType() == Material.CRYING_OBSIDIAN) || (loc
/* 120 */       .add(-1.0D, 0.0D, 0.0D).getBlock().getType() == Material.CRYING_OBSIDIAN && loc.add(0.0D, 0.0D, -1.0D).getBlock().getType() == Material.CRYING_OBSIDIAN && (loc
/* 121 */       .add(1.0D, 0.0D, 0.0D).getBlock().getType() == Material.WITHER_ROSE || loc.add(-1.0D, 0.0D, 0.0D).getBlock().getType() == Material.WITHER_ROSE || loc
/* 122 */       .add(1.0D, 0.0D, 1.0D).getBlock().getType() == Material.WITHER_ROSE || loc.add(-1.0D, 0.0D, 1.0D).getBlock().getType() == Material.WITHER_ROSE || loc
/* 123 */       .add(1.0D, 0.0D, -1.0D).getBlock().getType() == Material.WITHER_ROSE)));
        /*     */   }
    /*     */ }

