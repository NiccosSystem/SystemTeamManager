package net.niccossystem.systemteammanager.command;

import java.util.Arrays;
import java.util.List;
import net.niccossystem.systemteammanager.SystemTeamManager;
import net.niccossystem.systemteammanager.team.SystemTeam;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class DeleteCommand extends STMCommand {

    public static void execute(CommandSender caller, String[] args) {
        if (!caller.isOp() && !caller.hasPermission("stm.delete")) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM,
                CommandUsage.DELETE);
            return;
        }

        String teamName = "";

        if (args.length < 2) {
            STMCommand.notifyUsage(caller, WrongUsageType.ARGS_FEW,
                CommandUsage.DELETE);
            return;
        }
        
        String[] teamsInString = Arrays.copyOfRange(args, 1, args.length);

        List<SystemTeam> teams = SystemTeamManager.getTeamHandler().getTeams();
        for (String currentTeamId : teamsInString) {
            try {
                int id = Integer.parseInt(currentTeamId);
                if (teams.size() < id) {
                    caller.sendMessage("§4No such team ID \"" + currentTeamId + "\"!");
                    continue;
                }
                SystemTeam t = teams.get(id);
                teams.remove(t);
                caller.sendMessage("§aTeam \"" + t.getName() + "\" deleted!");
                continue;
            }
            catch (Throwable t) {
                caller.sendMessage("§4No such team ID \"" + currentTeamId + "\"!");
                continue;
            }
        }
    }

}
