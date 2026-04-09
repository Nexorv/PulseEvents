package com.voidpulse.pulseevents.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementManager {

    private final JavaPlugin plugin;
    private final List<BukkitTask> tasks = new ArrayList<>();

    public AnnouncementManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void startAnnouncements() {
        List<Integer> times = plugin.getConfig().getIntegerList("announcements.times");

        for (int time : times) {
            BukkitTask task = Bukkit.getScheduler().runTaskLater(plugin, () -> {
                String msg = plugin.getConfig().getString("announcements.message")
                        .replace("%time%", String.valueOf(time));

                Bukkit.broadcastMessage(color(msg));
            }, (long) (20L * (60 - time))); // przykładowo pod event timer
        }
    }

    private String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
