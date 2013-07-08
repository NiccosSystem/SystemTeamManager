package net.niccossystem.systemteammanager.command;

import net.niccossystem.systemteammanager.SystemTeamManager;
import net.niccossystem.systemteammanager.team.SystemTeam;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CreateCommand extends STMCommand {

    public static void execute(CommandSender caller, String[] args) {
        if (!caller.isOp() && !caller.hasPermission("stm.create")) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM, CommandUsage.CREATE);
            return;
        }
        
        String teamName = "";

        if (args.length < 2) {
            STMCommand.notifyUsage(caller, WrongUsageType.ARGS_FEW, CommandUsage.CREATE);
            return;
        }
        else if (args.length >= 2) {
            for (String current : args) {
                teamName += current + " ";
            }
            teamName = teamName.substring(args[0].length() + 1);
            teamName = teamName.trim();
        }

        SystemTeam newTeam = new SystemTeam(teamName);
        if (SystemTeamManager.getTeamHandler().teamExists(newTeam)) {
            STMCommand.notifyUsage(caller, WrongUsageType.TEAM_EXISTS, CommandUsage.CREATE);
            return;
        } else {
            SystemTeamManager.getTeamHandler().addTeam(newTeam);
            caller.sendMessage(ChatColor.GREEN + "Team \"" + teamName + "\" created!");
        }
    }
}
