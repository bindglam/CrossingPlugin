package io.github.bindglam.battle;

import io.github.bindglam.battle.commands.BattleCommand;
import io.github.bindglam.battle.listeners.PlayerListener;
import io.github.bindglam.battle.listeners.ServerTickListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class BattlePlugin extends JavaPlugin {
    public static BattlePlugin INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        Objects.requireNonNull(getCommand("battle")).setExecutor(new BattleCommand());

        getServer().getPluginManager().registerEvents(new ServerTickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        MapManager.init();
    }

    @Override
    public void onDisable() {
        MapManager.save();
        saveConfig();
    }
}
