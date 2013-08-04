package net.niccossystem.systemteammanager.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender caller, Command cmd, String label,
        String[] args) {
        if (args.length < 1) {
            STMCommand.notifyUsage(caller, WrongUsageType.ARGS_FEW,
                CommandUsage.BASE);
            return true;
        }

        boolean cmdExists = false;
        for (TeamSubCommand tsc : TeamSubCommand.values()) {
            if (tsc.getName().equalsIgnoreCase(args[0])) {
                cmdExists = true;
                tsc.execute(caller, args);
            }
        }

        if (!cmdExists) {
            STMCommand.notifyUsage(caller, WrongUsageType.WRONG_SUB,
                CommandUsage.BASE);
        }

        return true;
    }
}
