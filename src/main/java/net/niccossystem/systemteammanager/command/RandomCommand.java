package net.niccossystem.systemteammanager.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.niccossystem.systemteammanager.SystemTeamManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomCommand extends STMCommand {

    public static void execute(CommandSender caller, String[] args) {
        if (!caller.isOp() && !caller.hasPermission("stm.random")) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM,
                CommandUsage.RANDOM);
            return;
        }
        if (args.length < 2) {
            STMCommand.notifyUsage(caller, WrongUsageType.ARGS_AMNT,
                CommandUsage.RANDOM);
            return;
        }

        try {
            Integer.parseInt(args[1]);
        }
        catch (NumberFormatException nfe) {
            STMCommand.notifyUsage(caller, WrongUsageType.WRONG_ARGS,
                CommandUsage.RANDOM);
            return;
        }

        int teamSize = Integer.parseInt(args[1]);

        List<String> players = new ArrayList<String>();
        for (Player player : caller.getServer().getOnlinePlayers()) {
            if (SystemTeamManager.getTeamHandler().findMemberGroup(
                player.getName()) != null) {
                caller.sendMessage("§3Player " + player.getName()
                    + " is already on a team!");
                continue;
            }
            players.add(player.getName());
        }
        if (args.length == 3) {
            List<String> ignoredPlayers = Arrays.asList(args[2].split(","));
            for (String remove : ignoredPlayers) {
                players.remove(remove);
            }
        }

        SystemTeamManager.getTeamHandler().randomizeTeams(players, teamSize);
        caller.sendMessage(ChatColor.GREEN + "Teams randomized");
    }
}
