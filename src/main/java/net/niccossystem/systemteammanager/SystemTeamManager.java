package net.niccossystem.systemteammanager;

import net.niccossystem.systemteammanager.command.TeamCommand;
import net.niccossystem.systemteammanager.team.TeamHandler;
import net.niccossystem.systemteammanager.team.TeamSaveHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class SystemTeamManager extends JavaPlugin {

    private static TeamHandler tHandler;
    private static TeamSaveHandler saveHandler;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        SystemTeamManager.saveHandler = new TeamSaveHandler(this);
        SystemTeamManager.tHandler = new TeamHandler();
        getCommand("stm").setExecutor(new TeamCommand());
    }

    @Override
    public void onDisable() {

    }

    public static TeamHandler getTeamHandler() {
        return SystemTeamManager.tHandler;
    }

    public static TeamSaveHandler getSaveHandler() {
        return SystemTeamManager.saveHandler;
    }
}
