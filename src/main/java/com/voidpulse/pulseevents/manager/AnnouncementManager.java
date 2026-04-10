package com.voidpulse.pulseevents.manager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("deprecation")
public class AnnouncementManager {

    private final JavaPlugin plugin;
    private final EventManager eventManager;

    public AnnouncementManager(JavaPlugin plugin, EventManager eventManager) {
        this.plugin = plugin;
        this.eventManager = eventManager;
    }

    public void startAnnouncements() {

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            if (eventManager.isEventRunning()) return;

            Bukkit.broadcastMessage("§eEvent will start soon...");

        }, 0L, 20L * 60);
    }
}