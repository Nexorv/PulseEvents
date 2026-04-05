package com.voidpulse.pulseevents.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class MobSwarmEvent implements PulseEvent {

    private final JavaPlugin plugin;
    private BukkitTask task;

    public MobSwarmEvent(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Mob Swarm";
    }

    @Override
    public void start() {
        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                Location loc = p.getLocation();
                for (int i = 0; i < 2; i++) {
                    loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
                }
            }
        }, 0L, 60L);
    }

    @Override
    public void stop() {
        if (task != null) task.cancel();
    }

    @Override
    public int getDuration() {
        return 40;
    }
}