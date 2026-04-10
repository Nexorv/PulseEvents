package com.voidpulse.pulseevents.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class LiveUIManager {

    public LiveUIManager() {
    }
    private BossBar bossBar;

    // =========================
    // START UI
    // =========================
    public void start(String eventName) {

        bossBar = Bukkit.createBossBar(
                ChatColor.GREEN + "Event: " + eventName,
                BarColor.BLUE,
                BarStyle.SOLID
        );

        for (Player p : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(p);
        }

        bossBar.setVisible(true);
    }

    // =========================
    // STOP UI
    // =========================
    public void stop() {

        if (bossBar != null) {
            bossBar.removeAll();
            bossBar.setVisible(false);
            bossBar = null;
        }
    }
}