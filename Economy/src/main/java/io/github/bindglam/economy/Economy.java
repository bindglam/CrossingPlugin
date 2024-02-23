package io.github.bindglam.economy;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Economy extends JavaPlugin {
    public static Economy INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        try {
            EconomyManager.init();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        try {
            EconomyManager.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
