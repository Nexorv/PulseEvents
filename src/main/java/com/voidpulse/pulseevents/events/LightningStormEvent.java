package com.voidpulse.pulseevents.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class LightningStormEvent implements PulseEvent {

    private final JavaPlugin plugin;
    private BukkitTask task;

    public LightningStormEvent(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Lightning Storm";
    }

    @Override
    public void start() {
        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Math.random() < 0.4) {
                    p.getWorld().strikeLightning(p.getLocation());
                }
            }
        }, 0L, 40L);
    }

    @Override
    public void stop() {
        if (task != null) task.cancel();
    }

    @Override
    public int getDuration() {
        return 30;
    }
}