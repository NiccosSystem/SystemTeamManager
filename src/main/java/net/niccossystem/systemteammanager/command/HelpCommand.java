package net.niccossystem.systemteammanager.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class HelpCommand {

    public static void execute(CommandSender caller, String[] args) {
        caller.sendMessage(ChatColor.GREEN + "SystemTeamManager Help");
        for (TeamSubCommand tsc : TeamSubCommand.values()) {
            for (CommandUsage cu : CommandUsage.values()) {
                if (cu.toString().equalsIgnoreCase(tsc.toString())) {
                    caller.sendMessage(ChatColor.YELLOW + "- " + cu.getUsage());
                }
            }
        }
    }
}
