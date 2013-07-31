package net.niccossystem.systemteammanager.team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamHandler {

    private final ArrayList<SystemTeam> teams = new ArrayList<SystemTeam>();

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

    public void randomizeTeams(List<String> players, int teamSize) {
        int teams = (int) Math.floor(players.size() / teamSize);
        Random random = new Random();
        for (int i = 0; i < teams; i++) {
            SystemTeam newTeam = new SystemTeam("Team " + String.valueOf(i));
            for (i = 0; i < teamSize; i++) {
                int randomMember = random.nextInt(players.size());
                newTeam.addMember(players.get(randomMember));
                players.remove(randomMember);
            }
        }
    }
}
