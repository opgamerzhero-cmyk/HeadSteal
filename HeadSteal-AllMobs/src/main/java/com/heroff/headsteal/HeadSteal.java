package com.heroff.headsteal;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HeadSteal extends JavaPlugin {
    private static HeadSteal instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new MobKillListener(this), this);
        this.getCommand("headsteal").setExecutor(new HeadCommand(this));
        getLogger().info("HeadSteal plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("HeadSteal plugin disabled!");
    }

    public static HeadSteal getInstance() {
        return instance;
    }
}
