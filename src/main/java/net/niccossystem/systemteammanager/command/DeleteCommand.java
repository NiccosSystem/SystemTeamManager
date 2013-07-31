package net.niccossystem.systemteammanager.command;

import java.util.ArrayList;
import net.niccossystem.systemteammanager.SystemTeamManager;
import net.niccossystem.systemteammanager.team.SystemTeam;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class DeleteCommand extends STMCommand {

    public static void execute(CommandSender caller, String[] args) {
        if (!caller.isOp() && !caller.hasPermission("stm.delete")) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM, CommandUsage.DELETE);
            return;
        }

        String teamName = "";

        if (args.length < 2) {
            STMCommand.notifyUsage(caller, WrongUsageType.ARGS_FEW, CommandUsage.DELETE);
            return;
        }
        else if (args.length >= 2) {
            for (String current : args) {
                teamName += current + " ";
            }
            teamName = teamName.substring(args[0].length() + 1);
            teamName = teamName.trim();
        }

        ArrayList<SystemTeam> teams = SystemTeamManager.getTeamHandler().getTeams();
        for (SystemTeam curTeam : teams) {
            if (curTeam.getName().equalsIgnoreCase(teamName)) {
                teams.remove(curTeam);
                caller.sendMessage(ChatColor.GREEN + "Team \"" + curTeam.getName() + "\" deleted!");
                return;
            }
        }

        int id;
        try {
            id = Integer.valueOf(teamName);
        }
        catch (Throwable t) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_TEAM, CommandUsage.DELETE);
            return;
        }

        if (!(teams.size() < id)) {
            SystemTeam team = teams.get(id);
            teams.remove(team);
            caller.sendMessage(ChatColor.GREEN + "Team \"" + team.getName() + "\" deleted!");
            return;
        }
    }

}
