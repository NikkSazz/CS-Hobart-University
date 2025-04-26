
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Adventure Project Nikolai Sazonov\n");
        play();
    }

    public static void play() {

        // TO DO: Keybind ShortCut creation

        System.out.println("");
        Scanner scanner = new Scanner(System.in);

        Command.printCommands();

        while (true) { 
            String userInput = scanner.nextLine().toUpperCase();

            Command command = Command.valueOf(userInput);

            switch (command) {
                case GO -> System.out.println("Go Command.");
                case MOVE -> System.out.println("Move.");
                case TAKE -> System.out.println("Take.");
                case GET -> System.out.println("Get command.");
                case DROP -> System.out.println("You drop an item from your inventory.");
                case LOOK -> System.out.println("you looked  around.");
                case INVENTORY -> System.out.println("You check your inventory.");
                case HELP -> System.out.println("You asked for help");
                case SCORE -> System.out.println("Your current score is 69.");
                case QUIT -> {
                    System.out.println("Goodbye! Thanks for playing.");
                    scanner.close();
                    return; // stop playing
                }
                default -> System.out.println("Unknown command.");
            }

        }
    }
    
}