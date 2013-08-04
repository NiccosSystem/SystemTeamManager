package net.niccossystem.systemteammanager.command;

import org.bukkit.command.CommandSender;

public enum TeamSubCommand {
    CREATE("create") {

        @Override
        public void execute(CommandSender caller, String[] args) {
            CreateCommand.execute(caller, args);
        }
    },
    DELETE("delete") {

        @Override
        public void execute(CommandSender caller, String[] args) {
            DeleteCommand.execute(caller, args);
        }
    },
    ADD("add") {

        @Override
        public void execute(CommandSender caller, String[] args) {
            AddCommand.execute(caller, args);
        }
    },
    REMOVE("remove") {

        @Override
        public void execute(CommandSender caller, String[] args) {
            RemoveCommand.execute(caller, args);
        }
    },
    LIST("list") {

        @Override
        public void execute(CommandSender caller, String[] args) {
            ListCommand.execute(caller, args);
        }
    },
    SAVE("save") {

        @Override
        public void execute(CommandSender caller, String[] args) {
            SaveCommand.execute(caller, args);
        }
    },
    LOAD("load") {

        @Override
        public void execute(CommandSender caller, String[] args) {
            LoadCommand.execute(caller, args);
        }
    },
    HELP("help") {

        @Override
        public void execute(CommandSender caller, String[] args) {
            HelpCommand.execute(caller, args);
        }
    },
    SCATTER("scatter") {

        @Override
        public void execute(CommandSender caller, String[] args) {
            ScatterCommand.execute(caller, args);
        }
    },
    RENAME("rename") {

        @Override
        public void execute(CommandSender caller, String[] args) {
            RenameCommand.execute(caller, args);
        }
    },
    RANDOM("randomize") {

        @Override
        public void execute(CommandSender caller, String[] args) {
            RandomCommand.execute(caller, args);
        }
    };

    private String name;

    TeamSubCommand(String name) {
        this.name = name;
    }

    public abstract void execute(CommandSender caller, String[] args);

    public String getName() {
        return name;
    }
}
