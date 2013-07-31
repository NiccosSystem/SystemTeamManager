package net.niccossystem.systemteammanager.command;

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
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM, CommandUsage.ADD);
            return;
        }
        if (args.length < 3) {
            STMCommand.notifyUsage(caller, WrongUsageType.ARGS_FEW, CommandUsage.ADD);
            return;
        }

        String finalString = "";
        for (String cur : args) {
            finalString += cur + " ";
        }
        int lastOccurence = finalString.lastIndexOf(args[args.length - 1] + " ");
        finalString = finalString.substring(args[0].length() + 1, lastOccurence - 1);

        Player newMember = null;
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (p.getName().equalsIgnoreCase(args[args.length - 1])) {
                newMember = p;
            }
        }
        if (newMember == null) {
            STMCommand.notifyUsage(caller, WrongUsageType.PLAYER_OFFLINE, CommandUsage.ADD);
            return;
        }

        TeamHandler handler = SystemTeamManager.getTeamHandler();
        for (SystemTeam s : handler.getTeams()) {
            if (s.getName().equalsIgnoreCase(finalString)) {
                if (s.getMembers().contains(newMember.getName())) {
                    STMCommand.notifyUsage(caller, WrongUsageType.MEMBER_EXISTS, CommandUsage.ADD);
                    return;
                }
                SystemTeam team = handler.findMemberGroup(newMember.getName());
                if (team != null) {
                    STMCommand.notifyUsage(caller, WrongUsageType.MEMBER_OTHER, CommandUsage.ADD);
                    return;
                }

                s.addMember(newMember.getName());
                caller.sendMessage(ChatColor.GREEN + "Player \"" + newMember.getName() + "\" added to team \"" + s.getName() + "\"");
                return;
            }
        }

        int teamNumber;
        try {
            teamNumber = Integer.valueOf(finalString);
            if (!(handler.getTeams().size() < teamNumber)) {
                SystemTeam team = handler.getTeams().get(teamNumber);
                if (team.getMembers().contains(newMember.getName())) {
                    STMCommand.notifyUsage(caller, WrongUsageType.MEMBER_EXISTS, CommandUsage.ADD);
                    return;
                }

                SystemTeam memberTeam = handler.findMemberGroup(newMember.getName());
                if (memberTeam != null) {
                    STMCommand.notifyUsage(caller, WrongUsageType.MEMBER_OTHER, CommandUsage.ADD);
                    return;
                }

                team.addMember(newMember.getName());
                caller.sendMessage(ChatColor.GREEN + "Player \"" + newMember.getName() + "\" added to team \"" + team.getName() + "\"");
                return;
            }
        }
        catch (Throwable t) {}

        STMCommand.notifyUsage(caller, WrongUsageType.NO_TEAM, CommandUsage.ADD);
    }

}
