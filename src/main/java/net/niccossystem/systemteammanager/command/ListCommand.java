package net.niccossystem.systemteammanager.command;

import net.niccossystem.systemteammanager.SystemTeamManager;
import net.niccossystem.systemteammanager.team.SystemTeam;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ListCommand extends STMCommand {

    public static void execute(CommandSender caller, String[] args) {
        boolean other = false;

        caller.sendMessage(ChatColor.GRAY + "Current teams:");

        if (SystemTeamManager.getTeamHandler().getTeams().isEmpty()) {
            caller.sendMessage(ChatColor.GREEN + "No teams!");
            return;
        }

        for (SystemTeam team : SystemTeamManager.getTeamHandler().getTeams()) {
            ChatColor colour = other ? ChatColor.GREEN : ChatColor.BLUE;
            caller.sendMessage(colour + String.valueOf(SystemTeamManager.getTeamHandler().getTeams().indexOf(team)) + ". " + team.getName() + ":");

            for (String member : team.getMembers()) {
                caller.sendMessage(colour + "- " + member);
            }
            other = other ? false : true;
        }
    }

}
