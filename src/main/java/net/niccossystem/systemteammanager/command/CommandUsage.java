package net.niccossystem.systemteammanager.command;

public enum CommandUsage {
    BASE("/stm - Use a /stm help for help!"),
    CREATE("/stm create <teamname> - Create a team"),
    DELETE("/stm delete <teamname/id> - Delete a team"),
    ADD("/stm add <teamname/id> <player> - Add a player to a team"),
    REMOVE("/stm remove <teamname/id> <player> - Remove a player from a team"),
    LIST("/stm list - List all teams and their players"),
    SAVE("/stm save - Saves all teams and their players to the config file"),
    LOAD("/stm load - Loads all saved teams and players, if any, from the config file"),
    SCATTER("/stm scatter <radius> <centre x,z> - Scatters teams in a square in a radius around centre x,y");

    private String usage;

    CommandUsage(String usage) {
        this.usage = usage;
    }

    public String getUsage() {
        return usage;
    }
}
