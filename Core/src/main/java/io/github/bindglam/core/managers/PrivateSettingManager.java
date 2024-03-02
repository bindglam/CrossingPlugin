package io.github.bindglam.core.managers;

import io.github.bindglam.core.Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class PrivateSettingManager {
    public static final HashMap<UUID, PrivateSetting> privateSettings = new HashMap<>();

    public static PrivateSetting loadPlayer(UUID uuid){
        if(privateSettings.containsKey(uuid)){
            return privateSettings.get(uuid);
        } else {
            PrivateSetting setting = new PrivateSetting();
            for(String key : setting.settings.keySet()){
                if(Core.INSTANCE.getConfig().get("PrivateSettings." + uuid.toString() + "." + key) != null)
                    setting.settings.put(key, Core.INSTANCE.getConfig().get("PrivateSettings." + uuid.toString() + "." + key));
            }
            privateSettings.put(uuid, setting);
            return setting;
        }
    }

    public static void save(){
        Bukkit.broadcast(Component.text("개인 설정 저장중...").color(NamedTextColor.WHITE));
        for(UUID uuid : privateSettings.keySet()){
            PrivateSetting setting = privateSettings.get(uuid);
            for(String key : setting.settings.keySet()){
                Core.INSTANCE.getConfig().set("PrivateSettings." + uuid.toString() + "." + key, setting.settings.get(key));
            }
        }
    }

    public static class PrivateSetting{
        public final HashMap<String, Object> settings = new HashMap<>(){{
            put("PvP", true);
            put("Steal", true);
            put("GroundEnterMessage", true);
            put("Announce", true);
        }};
    }
}
