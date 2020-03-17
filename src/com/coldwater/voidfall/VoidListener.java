package com.coldwater.voidfall;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class VoidListener implements Listener {

    List<String> worlds = new ArrayList<String>();

    public VoidListener() {
        worlds.add("world");
        worlds.add("flatBuild");
    }

    @EventHandler
    public void onVoidDamage(EntityDamageEvent e)
    {
        if (e.getEntityType() == EntityType.PLAYER && e.getCause() == EntityDamageEvent.DamageCause.VOID)
        {
            Location loc = e.getEntity().getLocation();

            String worldName = worlds.get(0);

            if (worlds.indexOf(loc.getWorld().toString()) != -1) // if the world is found in the list...
            {
                if (worlds.indexOf(loc.getWorld().toString()) != worlds.size() - 1) // ...and the world isn't last in the list...
                {
                    worldName = worlds.get(worlds.indexOf(loc.getWorld().toString()) + 1); // ...fall into the next world.
                }
                else
                {
                    worldName = worlds.get(0); // If the world was last, loop back around and fall into the world at the top of the list :P
                }
            }
//            String worldName = worlds.indexOf(loc.getWorld().toString()) != -1  ? worlds.get(worlds.indexOf(loc.getWorld().toString()) + 1) : worlds.get(0);

            teleportPlayerToWorldAndCoordinates((Player) e.getEntity(), worldName, loc.getBlockX(), loc.getBlockZ());
        }
    }

    public void teleportPlayerToWorldAndCoordinates(Player player, String world, int x, int z)
    {
        Location loc = new Location(Bukkit.getWorld(world), x, 250, z);
        player.teleport(loc);
    }
}
