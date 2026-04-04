package com.voidpulse.pulseevents.manager;

import com.voidpulse.pulseevents.PulseEvents;
import com.voidpulse.pulseevents.events.PulseEvent;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {

    private final PulseEvents plugin;
    private final List<PulseEvent> events = new ArrayList<>();
    private PulseEvent current;

    public EventManager(PulseEvents plugin) {
        this.plugin = plugin;
    }

    public void registerEvent(PulseEvent event) {
        events.add(event);
    }

    public void startRandomEvent() {
        if (events.isEmpty()) {
            Bukkit.broadcastMessage(
                    plugin.getLang().get("prefix") +
                            plugin.getLang().get("no-events")
            );
            return;
        }

        if (current != null) {
            current.stop();
        }

        current = events.get(new Random().nextInt(events.size()));

        Bukkit.broadcastMessage(
                plugin.getLang().get("prefix") +
                        plugin.getLang().get("event-start")
                                .replace("%event%", current.getName())
        );

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

        Bukkit.broadcastMessage(
                plugin.getLang().get("prefix") +
                        plugin.getLang().get("event-end")
                                .replace("%event%", current.getName())
        );

        current = null;
    }
}