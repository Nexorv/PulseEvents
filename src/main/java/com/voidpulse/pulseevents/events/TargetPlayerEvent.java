package com.voidpulse.pulseevents.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ALL")
public class TargetPlayerEvent implements PulseEvent {

    private Player target;
    private final JavaPlugin plugin;

    public TargetPlayerEvent(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Target Player";
    }

    @Override
    public void start() {

        List<Player> players = List.copyOf(Bukkit.getOnlinePlayers());

        if (players.isEmpty()) {
            Bukkit.broadcastMessage("§cNo players to target!");
            return;
        }

        target = players.get(ThreadLocalRandom.current().nextInt(players.size()));

        Bukkit.broadcastMessage("§cTARGET: §e" + target.getName());
    }

    @Override
    public void stop() {
        target = null;
    }

    @Override
    public int getDuration() {
        return 40;
    }
}