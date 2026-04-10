package com.voidpulse.pulseevents;

import com.voidpulse.pulseevents.commands.PECommand;
import com.voidpulse.pulseevents.events.*;
import com.voidpulse.pulseevents.listener.JoinListener;
import com.voidpulse.pulseevents.listener.MilkBlockListener;
import com.voidpulse.pulseevents.manager.*;
import com.voidpulse.pulseevents.task.EventTask;
import com.voidpulse.pulseevents.update.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@SuppressWarnings("unused")
public final class PulseEvents extends JavaPlugin {

    private EventManager eventManager;
    private AnnouncementManager announcementManager;
    private LiveUIManager liveUIManager;
    private LanguageManager lang;
    private WorldCheck worldCheck;
    private UpdateChecker updateChecker;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        initManagers();
        registerEvents();
        registerCommands();
        registerGameEvents();
        startSystems();

        getLogger().info("PulseEvents enabled!");
    }

    // =========================
    // INIT
    // =========================

    private void initManagers() {
        lang = new LanguageManager(this);

        eventManager = new EventManager(this);

        announcementManager = new AnnouncementManager(this, eventManager);

        liveUIManager = new LiveUIManager();

        worldCheck = new WorldCheck(this);

        updateChecker = new UpdateChecker(this, "Ryvox0/PulseEvents");
    }

    // =========================
    // LISTENERS
    // =========================

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(
                new MilkBlockListener(eventManager, worldCheck, lang),
                this
        );

        getServer().getPluginManager().registerEvents(
                new JoinListener(updateChecker),
                this
        );
    }

    // =========================
    // COMMANDS
    // =========================

    private void registerCommands() {
        Objects.requireNonNull(getCommand("pe"))
                .setExecutor(new PECommand(this));
    }

    // =========================
    // GAME EVENTS
    // =========================

    private void registerGameEvents() {

        eventManager.registerEvent(new LowGravityEvent(this));
        eventManager.registerEvent(new CoinRainEvent(this));
        eventManager.registerEvent(new LightningStormEvent(this));
        eventManager.registerEvent(new TNTRainEvent(this));
        eventManager.registerEvent(new MobSwarmEvent(this));
        eventManager.registerEvent(new RandomTeleportEvent(this));
        eventManager.registerEvent(new FireFeetEvent(this));
        eventManager.registerEvent(new FreezeEvent(this));
        eventManager.registerEvent(new ReverseControlsEvent(this));
        eventManager.registerEvent(new BlackHoleEvent(this));
        eventManager.registerEvent(new RandomEffectsEvent(this));

        eventManager.registerEvent(new TargetPlayerEvent(this)); // FIX

        eventManager.registerEvent(new SpinEvent(this));
    }

    // =========================
    // START SYSTEMS
    // =========================

    private void startSystems() {
        EventTask.start(this, eventManager);

        announcementManager.startAnnouncements();
        updateChecker.check();
    }

    // =========================
    // GETTERS (KEEP IF YOU USE DI MIX)
    // =========================

    public EventManager getEventManager() {
        return eventManager;
    }

    public AnnouncementManager getAnnouncementManager() {
        return announcementManager;
    }

    public LiveUIManager getLiveUIManager() {
        return liveUIManager;
    }

    public LanguageManager getLang() {
        return lang;
    }

    public WorldCheck getWorldCheck() {
        return worldCheck;
    }

    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
}