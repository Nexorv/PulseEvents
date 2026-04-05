package com.voidpulse.pulseevents.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class FireFeetEvent implements PulseEvent {

    private final JavaPlugin plugin;
    private BukkitTask task;

    public FireFeetEvent(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Fire Feet";
    }

    @Override
    public void start() {
        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                Location loc = p.getLocation().clone().subtract(0, 1, 0);
                if (loc.getBlock().getType() == Material.AIR) {
                    loc.getBlock().setType(Material.FIRE);
                }
            }
        }, 0L, 20L);
    }

    @Override
    public void stop() {
        if (task != null) task.cancel();
    }

    @Override
    public int getDuration() {
        return 35;
    }
}