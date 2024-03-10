package io.github.bindglam.core.items.blocks;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import dev.lone.itemsadder.api.CustomBlock;
import io.github.bindglam.core.Core;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MegaGhastBlock extends PluginBlock {
    private static final List<Location> placedSpawners = new ArrayList<>();

    public MegaGhastBlock() {
        super(CustomBlock.getInstance("minecraftcross:mega_ghast_spawner"));
    }

    @Override
    public void onUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = Objects.requireNonNull(event.getClickedBlock());
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        ItemStack itemStack = event.getItem();

        if(itemStack == null && block.hasMetadata("is-charged")){
            player.playSound(player.getLocation(), Sound.BLOCK_END_GATEWAY_SPAWN, 100f, 0.7f);
            player.playSound(player.getLocation(), Sound.ENTITY_TNT_PRIMED, 100f, 1.0f);

            MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob("MegaGhast").orElse(null);
            if(mob != null){
                ActiveMob megaGhast = mob.spawn(BukkitAdapter.adapt(block.getLocation()), 1);
                LivingEntity entity = (LivingEntity) megaGhast.getEntity().getBukkitEntity();
                entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 255, false, false));

                for(Player nearByPlayer : block.getLocation().getNearbyEntitiesByType(Player.class, 50)){
                    nearByPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1, false, false));
                }
            }

            block.removeMetadata("is-charged", Core.INSTANCE);
            customBlock.playBreakEffect();
            customBlock.remove();
            placedSpawners.remove(block.getLocation());
        } else if(itemStack != null && itemStack.getType() == Material.EMERALD && !block.hasMetadata("is-charged")){
            player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 100f, 1f);
            block.setMetadata("is-charged", new FixedMetadataValue(Core.INSTANCE, true));
            block.getWorld().spawnParticle(Particle.SOUL, block.getLocation(), 10);
            itemStack.setAmount(itemStack.getAmount()-1);
            placedSpawners.add(block.getLocation());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        if(customBlock == null) return;
        if(!Objects.equals(customBlock.getNamespacedID(), "minecraftcross:mega_ghast_spawner")) return;

        placedSpawners.remove(block.getLocation());
    }

    @EventHandler
    public void onTick(ServerTickStartEvent event){
        for(Location location : placedSpawners){
            location.getWorld().spawnParticle(Particle.SOUL, location, 5);
        }
    }
}
