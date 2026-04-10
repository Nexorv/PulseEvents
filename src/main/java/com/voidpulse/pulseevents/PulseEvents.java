package com.voidpulse.pulseevents;

import com.voidpulse.pulseevents.commands.PECommand;
import com.voidpulse.pulseevents.events.*;
import com.voidpulse.pulseevents.manager.LanguageManager;
import com.voidpulse.pulseevents.manager.AnnouncementManager;
import com.voidpulse.pulseevents.manager.EventManager;
import com.voidpulse.pulseevents.manager.LiveUIManager;
import com.voidpulse.pulseevents.task.EventTask;
import com.voidpulse.pulseevents.manager.WorldCheck;
import com.voidpulse.pulseevents.update.UpdateChecker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import com.voidpulse.pulseevents.manager.*;
import com.voidpulse.pulseevents.listener.MilkBlockListener;

public class PulseEvents extends JavaPlugin {

    private static PulseEvents instance;
    private EventManager eventManager;
    private LanguageManager lang;
    private UpdateChecker updateChecker;
    private AnnouncementManager announcementManager;
    private LiveUIManager liveUIManager;
    private WorldCheck worldCheck;

    @Override
    public void onEnable() {
        instance = this;

        // config
        saveDefaultConfig();

        // language system
        lang = new LanguageManager(this);

        // event system
        eventManager = new EventManager();

        updateChecker = new UpdateChecker(this, "Ryvox0/PulseEvents");

        // Komendy
        getCommand("pe").setExecutor(new PECommand(updateChecker));

        // rejestracja eventów
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
        eventManager.registerEvent(new TargetPlayerEvent(this));
        eventManager.registerEvent(new SpinEvent(this));

        // task (config-based)
        EventTask.start(this, eventManager);


        updateChecker.check();
        worldCheck = new WorldCheck(this);

        announcementManager = new AnnouncementManager(this);
        announcementManager.startAnnouncements();


        liveUIManager = new LiveUIManager();
        getServer().getPluginManager().registerEvents(
                new MilkBlockListener(eventManager, worldCheck, lang),
                this
        );


        getLogger().info("PulseEvents enabled!");
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p= event.getPlayer();

        if (p.isOp() || p.hasPermission("pulseevents.admin")) {
            if (updateChecker.getLatestVersion() != null)
                p.sendMessage("§eUpdate available: §a" + updateChecker.getLatestVersion());
        }
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

    public WorldCheck getWorldCheck() {
        return worldCheck;
    }
}