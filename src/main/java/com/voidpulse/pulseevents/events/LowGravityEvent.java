package com.voidpulse.pulseevents.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class LowGravityEvent implements PulseEvent {

    private final JavaPlugin plugin;
    private BukkitTask task;

    public LowGravityEvent(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Low Gravity";
    }

    @Override
    public void start() {

        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            for (Player p : Bukkit.getOnlinePlayers()) {

                if (!p.isOnline()) continue;

                Vector vel = p.getVelocity();

                // lekka redukcja grawitacji
                if (vel.getY() < 0) {
                    vel.setY(vel.getY() * 0.6);
                } else {
                    vel.setY(vel.getY() + 0.03);
                }

                p.setVelocity(vel);
            }

        }, 0L, 1L); // co tick
    }

    @Override
    public void stop() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    @Override
    public int getDuration() {
        return 60;
    }
}