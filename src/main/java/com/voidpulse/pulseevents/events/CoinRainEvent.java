package com.voidpulse.pulseevents.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class CoinRainEvent implements PulseEvent {

    private final JavaPlugin plugin;
    private BukkitTask task;

    public CoinRainEvent(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Coin Rain";
    }

    @Override
    public void start() {

        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            for (Player p : Bukkit.getOnlinePlayers()) {

                Item item = p.getWorld().dropItem(
                        p.getLocation().add(0, 6, 0),
                        new ItemStack(Material.GOLD_NUGGET)
                );

                item.setPickupDelay(0);

                item.setVelocity(new Vector(
                        (Math.random() - 0.5) * 0.4,
                        0.5,
                        (Math.random() - 0.5) * 0.4
                ));
            }

        }, 0L, 10L); // co 0.5 sekundy
    }

    @Override
    public void stop() {
        if (task != null) task.cancel();
    }

    @Override
    public int getDuration() {
        return 45;
    }
}