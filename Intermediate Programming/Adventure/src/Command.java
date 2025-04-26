public enum Command {
    GO, MOVE,
    TAKE, GET,
    DROP,
    LOOK,
    INVENTORY,
    HELP,
    SCORE,
    QUIT;

    static void printCommands() {
        System.out.println("\n");
        for(Command c : Command.values()) {
            System.out.println(c.name());
        }
        System.out.println("\n");
    }
}
