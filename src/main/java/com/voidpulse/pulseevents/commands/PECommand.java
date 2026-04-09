package com.voidpulse.pulseevents.commands;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import com.voidpulse.pulseevents.update.UpdateChecker;

public class PECommand implements CommandExecutor {

    private final UpdateChecker updateChecker;

    public PECommand(UpdateChecker updateChecker) {
        this.updateChecker = updateChecker;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("§e/pulseevents update");
            return true;
        }

        if (args[0].equalsIgnoreCase("update")) {

            if (!(sender instanceof Player) || sender.hasPermission("pulseevents.admin")) {

                sender.sendMessage("§7Checking for updates...");
                updateChecker.check();

                sender.sendMessage("§aCheck started (async).");
            } else {
                sender.sendMessage("§cNo permission.");
            }
            return true;
        }

        return false;
    }
}
