package net.niccossystem.systemteammanager.command;

import net.niccossystem.systemteammanager.SystemTeamManager;
import net.niccossystem.systemteammanager.team.SystemTeam;
import net.niccossystem.systemteammanager.team.TeamHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class RemoveCommand extends STMCommand {

    public static void execute(CommandSender caller, String[] args) {
        if (!caller.isOp() && !caller.hasPermission("stm.remove")) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM, CommandUsage.REMOVE);
            return;
        }
        
        if (args.length < 3) {
            STMCommand.notifyUsage(caller, WrongUsageType.ARGS_FEW, CommandUsage.REMOVE);
            return;
        }

        String finalString = "";
        for (String cur : args) {
            finalString += cur + " ";
        }
        int lastOccurence = finalString.lastIndexOf(args[args.length - 1] + " ");
        finalString = finalString.substring(args[0].length() + 1, lastOccurence - 1);
        
        TeamHandler handler = SystemTeamManager.getTeamHandler();
        for (SystemTeam team : handler.getTeams()) {
            if (team.getName().equalsIgnoreCase(finalString)) {
                String member = null;
                for (String s : team.getMembers()) {
                    if (s.equalsIgnoreCase(args[args.length - 1])) {
                        member = s;
                    }
                }
                if (member == null) {
                    STMCommand.notifyUsage(caller, WrongUsageType.MEMBER_NOTEXISTS, CommandUsage.REMOVE);
                    return;
                }
                
                team.removeMember(member);
                caller.sendMessage(ChatColor.GREEN + "Player \"" + member + "\" removed from team \"" + team.getName() + "\"");
                return;
            }
        }

        int teamNumber;
        try {
            teamNumber = Integer.valueOf(finalString);
            if (!(handler.getTeams().size() < teamNumber)) {
                SystemTeam team = handler.getTeams().get(teamNumber);
                String member = null;
                for (String s : team.getMembers()) {
                    if (s.equalsIgnoreCase(args[args.length - 1])) {
                        member = s;
                    }
                }
                if (member == null) {
                    STMCommand.notifyUsage(caller, WrongUsageType.MEMBER_NOTEXISTS, CommandUsage.REMOVE);
                    return;
                }
                
                team.removeMember(member);
                caller.sendMessage(ChatColor.GREEN + "Player \"" + member + "\" removed from team \"" + team.getName() + "\"");
                return;
            }
        }
        catch (Throwable t) {}

        STMCommand.notifyUsage(caller, WrongUsageType.NO_TEAM, CommandUsage.REMOVE);
    }

}
