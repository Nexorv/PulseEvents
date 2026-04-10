package com.voidpulse.pulseevents.update;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@SuppressWarnings("deprecation")
public class UpdateChecker {

    private final JavaPlugin plugin;
    private final String repo; // np. Wilczek015/PulseEvents
    private String latestVersion = null;

    public UpdateChecker(JavaPlugin plugin, String repo) {
        this.plugin = plugin;
        this.repo = repo;
    }

    public void check() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                URL url = new URL("https://api.github.com/repos/" + repo + "/releases/latest");

                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder json = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                reader.close();

                // proste parsowanie (bez bibliotek)
                String data = json.toString();
                int index = data.indexOf("\"tag_name\":\"");
                if (index != -1) {
                    int start = index + 12;
                    int end = data.indexOf("\"", start);
                    latestVersion = data.substring(start, end);
                }

                if (latestVersion != null) {
                    compare();
                }

            } catch (Exception e) {
                plugin.getLogger().warning("Update check failed.");
            }
        });
    }

    private void compare() {
        String current = plugin.getDescription().getVersion();

        // usuń "v" jeśli masz v1.0.0
        String cleanLatest = latestVersion.replace("v", "");

        if (!current.equalsIgnoreCase(cleanLatest)) {
            notifyAdmins();
        }
    }

    private void notifyAdmins() {
        String msg = "§8[§6PulseEvents§8] §eNew version available: §a" + latestVersion +
                " §7(You have: §c" + plugin.getDescription().getVersion() + "§7)\n" +
                "§eDownload: §bhttps://github.com/" + repo + "/releases/latest";

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.isOp() || p.hasPermission("pulseevents.admin")) {
                p.sendMessage(msg);
            }
        }

        plugin.getLogger().info("New update available: " + latestVersion);
    }

    public String getLatestVersion() {
        return latestVersion;
    }
}