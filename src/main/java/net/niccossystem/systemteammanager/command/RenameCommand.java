package net.niccossystem.systemteammanager.command;

import net.niccossystem.systemteammanager.SystemTeamManager;
import net.niccossystem.systemteammanager.team.SystemTeam;
import org.bukkit.command.CommandSender;

public class RenameCommand extends STMCommand {

    public static void execute(CommandSender caller, String[] args) {
        if (!caller.isOp() && !caller.hasPermission("stm.rename")) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM, CommandUsage.RENAME);
            return;
        }
        if (args.length != 3) {
            STMCommand.notifyUsage(caller, WrongUsageType.ARGS_FEW, CommandUsage.RENAME);
            return;
        }

        try {
            Integer.parseInt(args[1]);
        }
        catch (NumberFormatException nfe) {
            STMCommand.notifyUsage(caller, WrongUsageType.WRONG_ARGS, CommandUsage.RENAME);
            return;
        }

        SystemTeam team = SystemTeamManager.getTeamHandler().getTeams().get(Integer.parseInt(args[0]));
        if (team == null) {
            STMCommand.notifyUsage(caller, WrongUsageType.WRONG_ARGS, CommandUsage.RENAME);
            return;
        }

        String newName = "";
        for (int i = 2; i < args.length; i++) {
            newName += args[i];
        }
        newName = newName.trim();

        team.setName(newName);
    }
}
