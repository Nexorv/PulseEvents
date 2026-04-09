package com.voidpulse.pulseevents.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class LanguageManager {

    private final JavaPlugin plugin;
    private FileConfiguration lang;

    public LanguageManager(JavaPlugin plugin) {
        this.plugin = plugin;
        load();
    }

    public void load() {
        String langName = plugin.getConfig().getString("language", "en");

        File file = new File(plugin.getDataFolder(), "lang/" + langName + ".yml");

        if (!file.exists()) {
            plugin.saveResource("lang/en.yml", false);
            file = new File(plugin.getDataFolder(), "lang/en.yml");
        }

        lang = YamlConfiguration.loadConfiguration(file);
    }

    public String get(String key) {
        return lang.getString(key, "&cMissing: " + key)
                .replace("&", "§");
    }
}