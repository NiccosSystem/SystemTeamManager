package net.niccossystem.systemteammanager.command;

import net.niccossystem.systemteammanager.SystemTeamManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;


public class SaveCommand extends STMCommand {
    
    public static void execute(CommandSender caller, String[] args) {
        if (!caller.isOp() && !caller.hasPermission("stm.save")) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM, CommandUsage.SAVE);
            return;
        }
        
        SystemTeamManager.getSaveHandler().save();
        caller.sendMessage(ChatColor.GREEN + "Teams saved to config.yml!");
    }
}
