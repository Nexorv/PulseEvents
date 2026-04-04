package com.voidpulse.pulseevents.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class LowGravityEvent implements PulseEvent {

    private final JavaPlugin plugin;

    public LowGravityEvent(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Low Gravity";
    }

    @Override
    public void start() {
        Bukkit.getOnlinePlayers().forEach(p ->
                p.setVelocity(new Vector(0, 0.3, 0))
        );
    }

    @Override
    public void stop() {
        // nic nie trzeba resetować globalnie
    }

    @Override
    public int getDuration() {
        return 60;
    }
}