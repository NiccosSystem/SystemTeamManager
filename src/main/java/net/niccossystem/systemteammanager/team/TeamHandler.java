package net.niccossystem.systemteammanager.team;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TeamHandler {

    private final List<SystemTeam> teams = new LinkedList<SystemTeam>();

    public void addTeam(SystemTeam team) {
        teams.add(team);
    }

    public void removeTeam(String team) {
        teams.remove(team);
    }

    public SystemTeam findMemberGroup(String playerName) {
        for (SystemTeam s : teams) {
            for (String member : s.getMembers()) {
                if (member.equalsIgnoreCase(playerName)) {
                    return s;
                }
            }
        }
        return null;
    }

    public List<SystemTeam> getTeams() {
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
