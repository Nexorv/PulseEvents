package com.voidpulse.pulseevents.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class RandomTeleportEvent implements PulseEvent {

    private final JavaPlugin plugin;
    private BukkitTask task;

    public RandomTeleportEvent(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Random Teleport";
    }

    @Override
    public void start() {
        World w = Bukkit.getWorlds().get(0);

        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                int x = (int) (Math.random() * 200 - 100);
                int z = (int) (Math.random() * 200 - 100);
                int y = w.getHighestBlockYAt(x, z);

                p.teleport(new Location(w, x, y, z));
            }
        }, 0L, 100L);
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