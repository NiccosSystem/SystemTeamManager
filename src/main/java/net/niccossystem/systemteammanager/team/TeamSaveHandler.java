package net.niccossystem.systemteammanager.team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.niccossystem.systemteammanager.SystemTeamManager;
import org.bukkit.configuration.file.FileConfiguration;

public class TeamSaveHandler {

    private final FileConfiguration config;
    private final SystemTeamManager plugin;

    public TeamSaveHandler(SystemTeamManager systemTeamManager) {
        config = systemTeamManager.getConfig();
        plugin = systemTeamManager;
    }

    public void save() {
        HashMap<String, ArrayList<String>> teams = new HashMap<String, ArrayList<String>>();

        for (SystemTeam sTeam : SystemTeamManager.getTeamHandler().getTeams()) {
            teams.put(sTeam.getName(), new ArrayList<String>());
            for (String member : sTeam.getMembers()) {
                teams.get(sTeam.getName()).add(member);
            }
        }

        if (config.getConfigurationSection("Teams") == null) {
            config.createSection("Teams", teams);
        }
        else {
            config.getConfigurationSection("Teams").getValues(false).clear();
            config.set("Teams", teams);
        }

        plugin.saveConfig();
    }

    @SuppressWarnings("unchecked")
    public boolean load() {
        Map<String, Object> savedTeams = config
            .getConfigurationSection("Teams").getValues(false);
        if (savedTeams == null) {
            return false;
        }

        for (String teamName : savedTeams.keySet()) {
            ArrayList<String> members = (ArrayList<String>) savedTeams
                .get(teamName);
            SystemTeam loadedTeam = new SystemTeam(teamName, members);
            SystemTeamManager.getTeamHandler().addTeam(loadedTeam);
        }
        return true;
    }
}
