package com.voidpulse.pulseevents.commands;

import com.voidpulse.pulseevents.update.UpdateChecker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PECommand implements CommandExecutor {

    private final UpdateChecker updateChecker;

    // 🔥 KONSTRUKTOR (MUSI BYĆ)
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

            // ✔ permission check OK
            if (!(sender instanceof Player) || sender.hasPermission("pulseevents.admin")) {

                // 🔴 ZABEZPIECZENIE (ważne!)
                if (updateChecker == null) {
                    sender.sendMessage("§cUpdate system not initialized!");
                    return true;
                }

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