package net.niccossystem.systemteammanager.command;

public enum CommandUsage {
    BASE("/stm - Use a /stm help for help!"), CREATE(
        "/stm create <teamname> - Create a team"), DELETE(
        "/stm delete <teamId1> [teamId2 teamId3 ...] - Delete teams"), ADD(
        "/stm add <teamId> <player1> [player2 player3 ...] - Add players to a team"), REMOVE(
        "/stm remove <teamId> <player1> [player2 player3 ...] - Remove players from a team"), RENAME(
        "/stm rename <teamId> <teamname> - Renames the specified team"), RANDOM(
        "/stm randomize <teamsize> [ignoredplayer1,ignoredplayer2] - Makes random teams of <teamsize>"), LIST(
        "/stm list - List all teams and their players"), SAVE(
        "/stm save - Saves all teams and their players to the config file"), LOAD(
        "/stm load - Loads all saved teams and players, if any, from the config file"), SCATTER(
        "/stm scatter <radius> <centre x,z> - Scatters teams in a square in a radius around centre x,y");

    private String usage;

    CommandUsage(String usage) {
        this.usage = usage;
    }

    public String getUsage() {
        return usage;
    }
}
