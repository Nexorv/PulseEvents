package com.voidpulse.pulseevents.listener;

import com.voidpulse.pulseevents.manager.EventManager;
import com.voidpulse.pulseevents.manager.LanguageManager;
import com.voidpulse.pulseevents.manager.WorldCheck;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class MilkBlockListener implements Listener {

    private final EventManager manager;
    private final WorldCheck worldCheck;
    private final LanguageManager lang;

    public MilkBlockListener(EventManager manager, WorldCheck worldCheck, LanguageManager lang) {
        this.manager = manager;
        this.worldCheck = worldCheck;
        this.lang = lang;
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {

        if (!manager.isEventRunning()) return;
        if (!worldCheck.isAllowed(e.getPlayer().getWorld())) return;

        if (e.getItem().getType() == Material.MILK_BUCKET) {
            e.setCancelled(true);

            e.getPlayer().sendMessage(
                    lang.get("milk-blocked")
            );
        }
    }
}