package io.github.bindglam.core.npc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.List;
import java.util.Objects;

public class InteractNPCManager implements Listener {
    public static final List<InteractNPC> NPCs = List.of(new BankerNPC(), new InnKeeperNPC(), new MinerNPC(), new FarmerNPC(), new SodaSellerNPC(), new FlowerSellerNPC(), new FoodSellerNPC(), new LibrarianNPC(),
            new TutorialEndNPC());

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event){
        if(!(event.getRightClicked() instanceof Player npcE)) return;

        for(InteractNPC npc : NPCs) {
            if (Objects.equals(npcE.getCustomName(), npc.name)){
                npc.onInteract(event);
            }
        }
    }
}
