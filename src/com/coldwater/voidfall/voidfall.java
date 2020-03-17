package com.coldwater.voidfall;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class voidfall extends JavaPlugin {

    @Override
    public void onEnable(){
        registerListeners();
        getLogger().info("Fillory awaits...");
    }

    @Override
    public void onDisable() {
        getLogger().info("Let's fly.");
    }

    public void registerListeners()
    {
        getServer().getPluginManager().registerEvents(new VoidListener(), this);
    }

}
