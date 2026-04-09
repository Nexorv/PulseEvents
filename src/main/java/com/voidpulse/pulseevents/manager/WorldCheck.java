package com.voidpulse.pulseevents.manager;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class WorldCheck {

    private final JavaPlugin plugin;

    public WorldCheck(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean isAllowed(World world) {
        if (!plugin.getConfig().getBoolean("multiworld.enabled")) return true;

        List<String> allowed = plugin.getConfig().getStringList("multiworld.allowed-worlds");
        return allowed.contains(world.getName());
    }
}
