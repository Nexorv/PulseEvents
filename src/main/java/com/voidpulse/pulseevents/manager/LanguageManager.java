package com.voidpulse.pulseevents.lang;

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
        String langFile = plugin.getConfig().getString("settings.language", "en");

        File file = new File(plugin.getDataFolder(), "lang/" + langFile + ".yml");

        if (!file.exists()) {
            plugin.saveResource("lang/" + langFile + ".yml", false);
        }

        lang = YamlConfiguration.loadConfiguration(file);
    }

    public String get(String key) {
        return lang.getString(key, key)
                .replace("&", "§");
    }
}