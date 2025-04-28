
import java.util.Scanner;

public class Main {

	static Player player_;
	
    public static void main(String[] args) {
        System.out.println("Adventure Project Nikolai Sazonov\n");
        
        String roomsFilePath = "data/rooms.txt";
        
        String startingRoom = "Red Square";
        player_ = new Player(roomsFilePath, startingRoom);
        
        play();
    }

    public static void play() {

        // TO DO: Keybind ShortCut creation
    	// Have a dictionary key: string val: command
    	// make sure shortcuts do not contain spaces

        
        Scanner scanner = new Scanner(System.in);

        Command.printCommands();
        Direction.printDirections();

        while (true) { 
        	
        	System.out.print("Please Write a Command: ");
            String userInput = scanner.nextLine().toUpperCase();

            // the first word is the command
            String cmd = userInput.split(" ")[0];
            Command command = Command.from(cmd);

            if (command == null) {
                System.out.println("Invalid command. Please try again.");
                continue;
            }
            
            switch (command) {
                case GO, MOVE -> goCommand(userInput);
                
                case TAKE, GET -> System.out.println("Take.");
                
                case DROP -> System.out.println("You drop an item from your inventory.");
                case LOOK -> player_.look();
                case INVENTORY -> System.out.println("You check your inventory.");
                case HELP -> System.out.println("You asked for help");
                case COMMANDS -> System.out.println("Commands");
                case SCORE -> System.out.println("Your current score is 69.");
                
                case QUIT -> {
                    System.out.println("Goodbye! Thanks for playing.");
                    scanner.close();
                    return;
                }
            }

        }
    }
    
    public static void goCommand(String input) {
    	System.out.println("Go Command.");
    	
    	String[] split = input.trim().split(" ");
    	
    	if(split.length != 2) {
    		System.out.println("Commands must have one space in between two words");
    		return;
    	}
    	
    	Direction direction = Direction.getDirection(split[1]);
    	
    	if(direction == null) {
    		System.out.println("Please input a possible Direction, you can type help for options");
    		return;
    	}
    	
    	switch(direction) {
    		case NORTH:
    				if(!player_.canMoveTo(0)) {
    					System.out.println("Cannot go this direction");
    					return;
    				}
    				player_.moveNorth();
    			break;
    			
    		case SOUTH:
    				if(!player_.canMoveTo(1)) {
    					System.out.println("Cannot go this direction");
    					return;
    				}
    				player_.moveSouth();
    			break;
    			
    		case EAST:
				if(!player_.canMoveTo(2)) {
					System.out.println("Cannot go this direction");
					return;
				}
				player_.moveEast();
			break;
    			
    		case WEST:
    				if(!player_.canMoveTo(3)) {
						System.out.println("Cannot go this direction");
						return;
					}
    				player_.moveWest();
    			break;
    			
    		default:
    			System.out.println("**Default switch for go command, something is wrong**");
    			break;
    	}
    }
    
}