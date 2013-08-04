package net.niccossystem.systemteammanager.command;

import java.util.Arrays;
import net.niccossystem.systemteammanager.SystemTeamManager;
import net.niccossystem.systemteammanager.team.SystemTeam;
import net.niccossystem.systemteammanager.team.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveCommand extends STMCommand {

    public static void execute(CommandSender caller, String[] args) {
        if (!caller.isOp() && !caller.hasPermission("stm.remove")) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM,
                CommandUsage.REMOVE);
            return;
        }
        if (args.length < 3) {
            STMCommand.notifyUsage(caller, WrongUsageType.ARGS_FEW,
                CommandUsage.REMOVE);
            return;
        }

        TeamHandler handler = SystemTeamManager.getTeamHandler();
        
        int teamId = handler.getTeams().size() + 1;
        try {
            teamId = Integer.parseInt(args[1]);
        }
        catch (Throwable t) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_TEAM, CommandUsage.REMOVE);
            return;
        }        
        if (teamId > handler.getTeams().size()) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_TEAM, CommandUsage.REMOVE);
            return;
        }
        
        SystemTeam team = handler.getTeams().get(teamId);
        String[] playersToRemove = Arrays.copyOfRange(args, 2, args.length);
        for (String player : playersToRemove) {
            if (!team.hasMember(player)) {
                caller.sendMessage("Team \"" + team.getName() + "\" does not have player \"" + player + "\"!");
                continue;
            }
            team.removeMember(player);
        }
    }

}
