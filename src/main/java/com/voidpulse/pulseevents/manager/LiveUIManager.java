package com.voidpulse.pulseevents.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class LiveUIManager {

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
    // TITLE (TO CI BRAKOWAŁO)
    // =========================
    public void sendStartTitle(String eventName) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(
                    ChatColor.GOLD + eventName,
                    ChatColor.YELLOW + "Event started!",
                    10,
                    40,
                    10
            );
        }
    }

    // =========================
    // ACTIONBAR
    // =========================
    public void sendActionBar(int players) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(
                    net.md_5.bungee.api.ChatMessageType.ACTION_BAR,
                    new net.md_5.bungee.api.chat.TextComponent(
                            "Players in event: " + players
                    )
            );
        }
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