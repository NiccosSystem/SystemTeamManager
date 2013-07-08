package net.niccossystem.systemteammanager.command;

import net.niccossystem.systemteammanager.SystemTeamManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;


public class LoadCommand extends STMCommand {
    
    public static void execute(CommandSender caller, String[] args) {
        if (!caller.isOp() && !caller.hasPermission("stm.load")) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM, CommandUsage.LOAD);
            return;
        }
        
        if (!SystemTeamManager.getSaveHandler().load()) {
            caller.sendMessage(ChatColor.RED + "Failed to load teams from config.yml. (Most likely there are no teams saved");
            return;
        }
        caller.sendMessage(ChatColor.GREEN + "Teams loaded from config.yml!");
    }
}
