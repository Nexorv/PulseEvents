package com.voidpulse.pulseevents.manager;

import com.voidpulse.pulseevents.events.PulseEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings({"deprecation", "unused"})
public class EventManager {

    private final JavaPlugin plugin;
    private final List<PulseEvent> events = new ArrayList<>();
    private PulseEvent current;

    public EventManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerEvent(PulseEvent event) {
        events.add(event);
    }

    public boolean isEventRunning() {
        return current != null;
    }

    public PulseEvent getCurrentEvent() {
        return current;
    }

    public void startRandomEvent() {

        if (events.isEmpty()) {
            Bukkit.broadcastMessage("§cNo events available!");
            return;
        }

        if (current != null) {
            current.stop();
        }

        current = events.get(new Random().nextInt(events.size()));

        Bukkit.broadcastMessage("§aEvent started: " + current.getName());

        current.start();

        Bukkit.getScheduler().runTaskLater(
                plugin,
                this::stopCurrent,
                current.getDuration() * 20L
        );
    }

    public void stopCurrent() {

        if (current == null) return;

        current.stop();

        Bukkit.broadcastMessage("§cEvent ended: " + current.getName());

        current = null;
    }
}