package net.niccossystem.systemteammanager.command;

public enum WrongUsageType {
    ARGS_FEW("Not enough arguments!"), ARGS_AMNT("Wrong amount of arguments!"), WRONG_SUB(
        "No such sub-command!"), LONG_NAME("That team name is too long!"), TEAM_EXISTS(
        "That team already exists!"), NO_TEAM("There is no such team!"), MEMBER_EXISTS(
        "That player is already in that team!"), MEMBER_OTHER(
        "That player is already on another team!"), MEMBER_NOTEXISTS(
        "No such player on that team!"), PLAYER_OFFLINE(
        "That player is not online!"), WRONG_ARGS("Wrong arguments!"), NO_PERM(
        "You haven't got permission for this command!");

    private String message;

    WrongUsageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
