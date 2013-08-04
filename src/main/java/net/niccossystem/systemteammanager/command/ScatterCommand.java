package net.niccossystem.systemteammanager.command;

import java.util.ArrayList;
import net.niccossystem.systemteammanager.SystemTeamManager;
import net.niccossystem.systemteammanager.scatter.TeamScatter;
import net.niccossystem.systemteammanager.team.SystemTeam;
import net.niccossystem.systemteammanager.team.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScatterCommand extends STMCommand {

    public static void execute(CommandSender caller, String[] args) {
        if (!caller.isOp() && !caller.hasPermission("stm.scatter")) {
            STMCommand.notifyUsage(caller, WrongUsageType.NO_PERM,
                CommandUsage.SCATTER);
            return;
        }

        if (!(caller instanceof Player)) {
            caller.sendMessage(ChatColor.RED
                + "Need to be player to execute this command");
        }
        if (args.length < 3) {
            STMCommand.notifyUsage(caller, WrongUsageType.ARGS_FEW,
                CommandUsage.SCATTER);
            return;
        }
        if (!args[2].contains(",")) {
            STMCommand.notifyUsage(caller, WrongUsageType.WRONG_ARGS,
                CommandUsage.SCATTER);
        }

        String[] centreString = args[2].split(",");
        int[] centre;
        int radius;
        try {
            centre = new int[] { Integer.parseInt(centreString[0]),
                Integer.parseInt(centreString[1]) };
            radius = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            STMCommand.notifyUsage(caller, WrongUsageType.WRONG_ARGS,
                CommandUsage.SCATTER);
            return;
        }

        Player p = (Player) caller;
        TeamHandler handler = SystemTeamManager.getTeamHandler();
        Location centreLoc = new Location(p.getWorld(), centre[0], 0.0D,
            centre[1]);
        ArrayList<Location> locations = TeamScatter.getScatterLocations(radius,
            handler.getTeams().size(), centreLoc);

        for (SystemTeam st : handler.getTeams()) {
            for (String player : st.getMembers()) {
                Player player_ = Bukkit.getServer().getPlayer(player);
                if (player_ != null) {
                    player_.getInventory().clear();
                    player_.getEquipment().clear();
                }
                else {
                    caller.sendMessage("Player " + player + " is not online!");
                }
            }
            Location teamScatterLoc = locations.get(0);
            st.teleportTo(teamScatterLoc);
            locations.remove(teamScatterLoc);
        }

    }
}
