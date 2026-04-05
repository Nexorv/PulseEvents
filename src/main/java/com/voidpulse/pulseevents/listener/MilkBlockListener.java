package com.voidpulse.pulseevents.listener;

import com.voidpulse.pulseevents.manager.EventManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class MilkBlockListener implements Listener {

    private final EventManager manager;

    public MilkBlockListener(EventManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {

        if (!manager.isEventRunning()) return;

        if (e.getItem().getType() == Material.MILK_BUCKET) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cNie możesz użyć mleka podczas eventu!");
        }
    }
}