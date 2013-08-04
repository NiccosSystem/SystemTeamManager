package net.niccossystem.systemteammanager.command;

import java.util.Arrays;
import net.niccossystem.systemteammanager.SystemTeamManager;
import net.niccossystem.systemteammanager.team.SystemTeam;
import net.niccossystem.systemteammanager.team.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddCommand extends STMCommand {

    public static void execute(CommandSender caller, String[] args) {
        if (!caller.isOp() && !caller.hasPermission("stm.add")) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM,
                CommandUsage.ADD);
            return;
        }
        if (args.length < 3) {
            STMCommand.notifyUsage(caller, WrongUsageType.ARGS_FEW,
                CommandUsage.ADD);
            return;
        }

        TeamHandler handler = SystemTeamManager.getTeamHandler();
        
        int teamId = handler.getTeams().size() + 1;
        try {
            teamId = Integer.parseInt(args[1]);
        }
        catch (Throwable t) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_TEAM, CommandUsage.ADD);
            return;
        }        
        if (teamId > handler.getTeams().size()) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_TEAM, CommandUsage.ADD);
            return;
        }
        
        SystemTeam team = handler.getTeams().get(teamId);
        String[] playersToAdd = Arrays.copyOfRange(args, 2, args.length);
        for (String player : playersToAdd) {
            SystemTeam t = handler.findMemberGroup(player);
            if (t != null) {
                caller.sendMessage("§4Player \"" + player + "\" is already on \"" + t.getName() + "\"!");
                continue;
            }
            Player p = Bukkit.getServer().getPlayer(player);
            if (p == null) {
                caller.sendMessage("§4Player \"" + player + "\" is not online!");
                continue;
            }
            team.addMember(p.getName());
        }
    }

}
