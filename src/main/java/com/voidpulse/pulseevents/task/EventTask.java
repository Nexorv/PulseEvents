package com.voidpulse.pulseevents.task;

import com.voidpulse.pulseevents.manager.EventManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class EventTask {

    public static void start(JavaPlugin plugin, EventManager manager) {

        int min = plugin.getConfig().getInt("events.min-interval") * 20;
        int max = plugin.getConfig().getInt("events.max-interval") * 20;

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            int delay = min + new Random().nextInt(max - min);

            Bukkit.getScheduler().runTaskLater(plugin, manager::startRandomEvent, delay);

        }, 20L * 10, 20L * 10);
    }
}