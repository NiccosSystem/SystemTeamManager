package net.niccossystem.systemteammanager.command;

import net.niccossystem.systemteammanager.SystemTeamManager;
import net.niccossystem.systemteammanager.team.SystemTeam;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class TeleportCommand extends STMCommand {
    
    public static void execute(CommandSender caller, String[] args) {
        //Disabled for now
        for (SystemTeam st : SystemTeamManager.getTeamHandler().getTeams()) {
            st.teleportTo(caller, ((Player) caller).getLocation());
        }
    }
}
