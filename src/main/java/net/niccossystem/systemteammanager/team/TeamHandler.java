package net.niccossystem.systemteammanager.team;

import java.util.ArrayList;


public class TeamHandler {
    
    private ArrayList<SystemTeam> teams = new ArrayList<SystemTeam>();
    
    public void addTeam(SystemTeam team) {
        teams.add(team);
    }

    public void removeTeam(String team) {      
        teams.remove(team);
    }

    public SystemTeam findMemberGroup(String playerName) {
        for (SystemTeam s : teams) {
            if (s.getMembers().contains(playerName)) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<SystemTeam> getTeams() {
        return teams;
    }
    
    public boolean teamExists(SystemTeam team) {
        return teams.contains(team);
    }
}
