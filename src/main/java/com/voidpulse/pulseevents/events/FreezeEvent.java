package com.voidpulse.pulseevents.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FreezeEvent implements PulseEvent {

    public FreezeEvent(JavaPlugin plugin) {
    }

    @Override
    public String getName() {
        return "Freeze";
    }

    @Override
    public void start() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, getDuration() * 20, 10, false, false));
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, getDuration() * 20, 200, false, false));
        }
    }

    @Override
    public void stop() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.removePotionEffect(PotionEffectType.SLOW);
            p.removePotionEffect(PotionEffectType.JUMP);
        }
    }

    @Override
    public int getDuration() {
        return 10;
    }
}