package io.github.bindglam.ground.utils;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ParticleUtil {
    @Deprecated
    public static void spawnParticles(EnumParticle particle, float x, float y, float z, float offsetX, float offsetY, float offsetZ,
                                      float speed, int count){
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                particle,
                true,
                x,
                y,
                z,
                offsetX,
                offsetY,
                offsetZ,
                speed,
                count,
                null
        );

        for(Player player : Bukkit.getOnlinePlayers()){
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    @Deprecated
    public static void spawnParticles(EnumParticle particle, float x, float y, float z, float offsetX, float offsetY, float offsetZ,
                                      float speed, int count, Player player){
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                particle,
                true,
                x,
                y,
                z,
                offsetX,
                offsetY,
                offsetZ,
                speed,
                count,
                null
        );

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
