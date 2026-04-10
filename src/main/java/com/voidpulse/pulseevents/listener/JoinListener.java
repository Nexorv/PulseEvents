package com.voidpulse.pulseevents.listener;

import com.voidpulse.pulseevents.update.UpdateChecker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final UpdateChecker updateChecker;

    public JoinListener(UpdateChecker updateChecker) {
        this.updateChecker = updateChecker;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        if (event.getPlayer().isOp()
                || event.getPlayer().hasPermission("pulseevents.admin")) {

            String latest = updateChecker.getLatestVersion();

            if (latest != null) {
                event.getPlayer().sendMessage("§eUpdate available: §a" + latest);
            }
        }
    }
}