package com.voidpulse.pulseevents.events;

import org.bukkit.Material;

public interface PulseEvent {

    String getName();

    void start();

    void stop();

    int getDuration();

    default String getKey() {
        return getName()
                .toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-+", "")
                .replaceAll("-+$", "");
    }

    default String getChanceConfigPath() {
        return "events." + getKey() + ".chance";
    }

    default Material getMenuMaterial() {
        return switch (getKey()) {
            case "coin-rain" -> Material.SUNFLOWER;
            case "lightning-storm" -> Material.LIGHTNING_ROD;
            case "tnt-rain" -> Material.TNT;
            case "mob-swarm" -> Material.ZOMBIE_HEAD;
            case "random-teleport" -> Material.ENDER_PEARL;
            case "fire-feet" -> Material.FLINT_AND_STEEL;
            case "freeze" -> Material.ICE;
            case "black-hole" -> Material.OBSIDIAN;
            case "random-effects" -> Material.POTION;
            case "target-player" -> Material.CROSSBOW;
            case "spin" -> Material.COMPASS;
            default -> Material.NETHER_STAR;
        };
    }
}
