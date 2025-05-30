public enum Command {
    GO, MOVE,
    TAKE, GET,
    DROP, USE,
    LOOK,
    INVENTORY,
    HELP,
    COMMANDS,
    SCORE,
    QUIT;

    static void printCommands() {
    	
        System.out.println("\nCommands:");
        for(Command c : Command.values()) {
            System.out.print("\t" + c.name());
        }
        System.out.println("\n");
    }
    
    
    static Command from(String userInput) {
        if (userInput == null) {
            return null;
        }

        for (Command c : Command.values()) {
            if (c.name().equalsIgnoreCase(userInput)) {
                return c;
            }
        }

        return null; // Or a default value like Command.HELP
    }
}