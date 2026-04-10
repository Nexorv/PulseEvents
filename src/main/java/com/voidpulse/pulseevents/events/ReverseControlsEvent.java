package com.voidpulse.pulseevents.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class ReverseControlsEvent implements PulseEvent, Listener {

    private final JavaPlugin plugin;
    private boolean active = false;

    public ReverseControlsEvent(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Reverse Controls";
    }

    @Override
    public void start() {
        active = true;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void stop() {
        active = false;
        HandlerList.unregisterAll(this);
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if (!active) return;

        Player p = e.getPlayer();

        if (e.getFrom().getX() == e.getTo().getX()
                && e.getFrom().getZ() == e.getTo().getZ()) {
            return;
        }

        Vector from = e.getFrom().toVector();
        Vector to = e.getTo().toVector();

        Vector movement = to.subtract(from);

        if (movement.length() == 0) return;

        Vector reversed = movement.multiply(-1);

        p.setVelocity(reversed.multiply(0.5));
    }
}