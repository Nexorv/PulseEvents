package com.voidpulse.pulseevents;

import com.voidpulse.pulseevents.events.CoinRainEvent;
import com.voidpulse.pulseevents.events.LowGravityEvent;
import com.voidpulse.pulseevents.lang.LanguageManager;
import com.voidpulse.pulseevents.manager.EventManager;
import com.voidpulse.pulseevents.task.EventTask;
import org.bukkit.plugin.java.JavaPlugin;

public class PulseEvents extends JavaPlugin {

    private static PulseEvents instance;
    private EventManager eventManager;
    private LanguageManager lang;

    @Override
    public void onEnable() {
        instance = this;

        // config
        saveDefaultConfig();

        // language system
        lang = new LanguageManager(this);

        // event system
        eventManager = new EventManager(this);

        // rejestracja eventów
        eventManager.registerEvent(new LowGravityEvent(this));
        eventManager.registerEvent(new CoinRainEvent(this));

        // task (config-based)
        EventTask.start(this, eventManager);

        getLogger().info("PulseEvents enabled!");
    }

    public static PulseEvents getInstance() {
        return instance;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public LanguageManager getLang() {
        return lang;
    }
}