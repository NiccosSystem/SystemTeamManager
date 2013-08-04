package net.niccossystem.systemteammanager.team;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SystemTeam {

    private String name;
    private final List<String> members;
    private final int totalHealth = 0;

    public SystemTeam(String name) {
        this.name = name;
        members = new LinkedList<String>();
    }

    public SystemTeam(String name, List<String> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public List<String> getMembers() {
        return members;
    }

    public int getTeamHealth() {
        return totalHealth;
    }

    public void addMember(String playerName) {
        members.add(playerName);
    }

    public void removeMember(String playerName) {
        for (String member : members) {
            if (member.equalsIgnoreCase(playerName)) {
                members.remove(member);
                return;
            }
        }
    }

    public void teleportTo(CommandSender caller, Location loc) {
        int teleportedCount = 0;
        for (String member : getMembers()) {
            boolean isOnline = false;
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getName().equalsIgnoreCase(member)) {
                    isOnline = true;
                    p.teleport(loc);
                    teleportedCount++;
                }
            }
            if (!isOnline) {
                caller.sendMessage(ChatColor.RED + "Player \"" + member
                    + "\" on team \"" + getName() + "\" is not online!");
            }
        }
        caller.sendMessage(String.valueOf(teleportedCount)
            + " members of team \"" + getName()
            + "\" have been teleported!");
    }

    public void teleportTo(Location loc) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (members.contains(p.getName())) {
                p.teleport(loc);
                p.sendMessage(String.format(
                    "%sYour team was teleported to X:%s Y:%s Z:%s",
                    ChatColor.GREEN, loc.getX(), loc.getY(), loc.getZ()));
            }
        }
    }

    public boolean hasMember(String player) {
        boolean hasPlayer = false;
        for (String member : members) {
            if (member.equalsIgnoreCase(player)) {
                hasPlayer = true;
            }
        }
        return hasPlayer;
    }
}
