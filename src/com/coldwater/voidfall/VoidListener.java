package com.coldwater.voidfall;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class VoidListener implements Listener {
    List<String> worlds = new ArrayList();

    public VoidListener() {
        this.worlds.add("Tale_1");
        this.worlds.add("Tale_1_Null");
    }

    @EventHandler
    public void onVoidDamage(EntityDamageEvent e) {
        if (e.getEntityType() == EntityType.PLAYER && e.getCause() == EntityDamageEvent.DamageCause.VOID) {
            Location loc = e.getEntity().getLocation();
            String worldName = (String)this.worlds.get(0);
            Bukkit.getLogger().info("current world is " + loc.getWorld().getName());
            if (this.worlds.contains(loc.getWorld().getName())) {
                if (this.worlds.indexOf(loc.getWorld().getName()) != this.worlds.size() - 1) {
                    worldName = (String)this.worlds.get(this.worlds.indexOf(loc.getWorld().getName()) + 1);
                    Bukkit.getLogger().info("whee! Falling into " + worldName);
                } else {
                    worldName = (String)this.worlds.get(0);
                    Bukkit.getLogger().info("Out of worlds, looping back to the top of the list!");
                }

                Player p = (Player)e.getEntity();
                this.teleportPlayerToWorldAndCoordinates(p, worldName, loc.getBlockX(), loc.getBlockZ());
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 400, 1, false, false, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 6, false, false, false));
                p.sendMessage("You feel space bend as the void swallows you...");
            } else {
                Bukkit.getLogger().info("Something went wrong...");
            }
        }

    }

    public void teleportPlayerToWorldAndCoordinates(Player player, String world, int x, int z) {
        Location loc = new Location(Bukkit.getWorld(world), (double)x, 250.0D, (double)z);
        player.teleport(loc);
        Bukkit.getLogger().info("world should be " + world + " after TP");
    }
}
