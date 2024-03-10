package io.github.bindglam.ground.listeners;

import io.github.bindglam.ground.GroundManager;
import io.github.bindglam.ground.events.GroundEnterEvent;
import io.github.bindglam.ground.events.GroundExitEvent;
import io.github.bindglam.ground.utils.ParticleUtil;
import io.papermc.paper.event.entity.EntityMoveEvent;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PlayerListener implements Listener {
    private static final HashMap<String, Location> inGrounds = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        List<Object> data = GroundManager.isInGround(player);
        if(data != null){
            for(int x = -8; x < 8; x++) {
                player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, ((Location) data.get(1)).getX() + x, player.getLocation().getY(), (float) ((Location) data.get(1)).getZ() + 8, 1, 0f, 0f, 0f, 0);
                player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, ((Location) data.get(1)).getX() + x, player.getLocation().getY(), (float) ((Location) data.get(1)).getZ() - 8, 1, 0f, 0f, 0f, 0);
            }
            for(int z = -8; z < 8; z++) {
                player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, ((Location) data.get(1)).getX() + 8, player.getLocation().getY(), (float) ((Location) data.get(1)).getZ() + z, 1, 0f, 0f, 0f, 0);
                player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, ((Location) data.get(1)).getX() - 8, player.getLocation().getY(), (float) ((Location) data.get(1)).getZ() + z, 1, 0f, 0f, 0f, 0);
            }

            GroundEnterEvent enterEvent;
            if(inGrounds.containsKey(player.getName())){
                if(inGrounds.get(player.getName()).equals(data.get(1))) return;
            }
            enterEvent = new GroundEnterEvent(player, (Location) data.get(1), data);
            enterEvent.callEvent();
            inGrounds.put(player.getName(), (Location) data.get(1));

            if(enterEvent.isCancelled()){
                inGrounds.remove(player.getName());
                event.getPlayer().teleport(event.getFrom());
            }
        } else {
            inGrounds.remove(player.getName());
            new GroundExitEvent(player).callEvent();
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getClickedBlock() == null) return;
        Player player = event.getPlayer();

        List<Object> data = GroundManager.isInGround(event.getClickedBlock().getLocation());
        if(data != null){
            UUID owner = GroundManager.grounds.get((Location) data.get(1));
            if(Objects.equals(owner, player.getUniqueId())) return;
            if(GroundManager.grounders.containsKey((Location) data.get(1))) if(GroundManager.grounders.get((Location) data.get(1)).contains(player.getUniqueId())) return;

            if(event.getClickedBlock().getType() != Material.LECTERN && !event.getClickedBlock().getType().name().contains("PRESSURE_PLATE") && !event.getClickedBlock().getType().name().contains("DOOR")) {
                event.setCancelled(true);
                player.sendMessage("§c§l이곳은 " + Bukkit.getOfflinePlayer(owner).getName() + "님의 땅입니다.");
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();

        List<Object> data = GroundManager.isInGround(event.getBlock().getLocation());
        if(data != null){
            UUID owner = GroundManager.grounds.get((Location) data.get(1));
            if(Objects.equals(owner, player.getUniqueId())) return;
            if(GroundManager.grounders.containsKey((Location) data.get(1))) if(GroundManager.grounders.get((Location) data.get(1)).contains(player.getUniqueId())) return;

            event.setCancelled(true);
            player.sendMessage("§c§l이곳은 " + Bukkit.getOfflinePlayer(owner).getName() + "님의 땅입니다.");
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();

        List<Object> data = GroundManager.isInGround(event.getBlock().getLocation());
        if(data != null){
            UUID owner = GroundManager.grounds.get((Location) data.get(1));
            if(Objects.equals(owner, player.getUniqueId())) return;
            if(GroundManager.grounders.containsKey((Location) data.get(1))) if(GroundManager.grounders.get((Location) data.get(1)).contains(player.getUniqueId())) return;

            event.setCancelled(true);
            player.sendMessage("§c§l이곳은 " + Bukkit.getOfflinePlayer(owner).getName() + "님의 땅입니다.");
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        Entity entity = event.getEntity();

        List<Object> data = GroundManager.isInGround(event.getEntity().getLocation());
        if(data != null) {
            UUID owner = GroundManager.grounds.get((Location) data.get(1));
            if (Objects.equals(owner, entity.getUniqueId())) {
                event.setCancelled(true);
                return;
            }
            if (GroundManager.grounders.containsKey((Location) data.get(1)) && !Objects.equals(owner, entity.getUniqueId())) {
                if (GroundManager.grounders.get((Location) data.get(1)).contains(entity.getUniqueId())) {
                    event.setCancelled(true);
                }
            }
            //entity.sendMessage("§c§l이곳은 " + owner + "님의 땅입니다.");
        }
    }

    @EventHandler
    public void onDamageByPlayer(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        List<Object> data = GroundManager.isInGround(event.getEntity().getLocation());
        if(data != null) {
            if(!(entity instanceof Player) && damager instanceof Player) {
                event.setCancelled(true);
                UUID owner = GroundManager.grounds.get((Location) data.get(1));
                if (Objects.equals(owner, damager.getUniqueId())) {
                    event.setCancelled(false);
                    return;
                }
                if (GroundManager.grounders.containsKey((Location) data.get(1)) && !Objects.equals(owner, damager.getUniqueId())) {
                    if (GroundManager.grounders.get((Location) data.get(1)).contains(damager.getUniqueId())) {
                        event.setCancelled(false);
                    }
                }
            }
            //entity.sendMessage("§c§l이곳은 " + owner + "님의 땅입니다.");
        }
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event){
        Entity entity = event.getEntity();

        List<Object> data = GroundManager.isInGround(event.getEntity().getLocation());
        if(data != null){
            UUID owner = GroundManager.grounds.get((Location) data.get(1));
            if(Objects.equals(owner, entity.getUniqueId())) return;
            if(GroundManager.grounders.containsKey((Location) data.get(1))) if(GroundManager.grounders.get((Location) data.get(1)).contains(entity.getUniqueId())) return;

            event.setCancelled(true);
            //entity.sendMessage("§c§l이곳은 " + owner + "님의 땅입니다.");
        }

        for (int i = event.blockList().size() - 1; i >= 0; i--) {
            Block block = event.blockList().get(i);
            List<Object> block_data = GroundManager.isInGround(block.getLocation());
            if(block_data != null){
                event.blockList().remove(block);
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractAtEntityEvent event){
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();

        List<Object> data = GroundManager.isInGround(entity.getLocation());
        if(data != null) {
            event.setCancelled(true);
            UUID owner = GroundManager.grounds.get((Location) data.get(1));
            if (Objects.equals(owner, player.getUniqueId())) {
                event.setCancelled(false);
                return;
            }
            if (GroundManager.grounders.containsKey((Location) data.get(1)) && !Objects.equals(owner, player.getUniqueId())) {
                if (GroundManager.grounders.get((Location) data.get(1)).contains(player.getUniqueId())) {
                    event.setCancelled(false);
                    return;
                }
            }
            player.sendMessage("§c§l이곳은 " + Bukkit.getOfflinePlayer(owner).getName() + "님의 땅입니다.");
        }
    }
}
