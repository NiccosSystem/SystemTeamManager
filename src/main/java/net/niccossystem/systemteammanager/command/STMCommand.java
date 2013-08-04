package net.niccossystem.systemteammanager.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class STMCommand {

    public static void notifyUsage(CommandSender caller, WrongUsageType wut,
        CommandUsage cu) {
        String msg = String.format("%s%s \n%sUsage: %s", ChatColor.RED,
            wut.getMessage(), ChatColor.YELLOW, cu.getUsage());
        caller.sendMessage(msg);
    }
}
