package cs225;
import java.util.Scanner;

/*
 * @author Nikolai Sazonov
 */

class ooflip {
    private static final Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("\nWelcome from OOFlip!");

        player[] players = new player[2];


        System.out.println("Please Enter Player One's Name: ");
		players[0] = new player(s.nextLine());

		System.out.println("\nPlease Enter Player Two's Name: ");
		players[1] = new player(s.nextLine());
        
        System.out.println(players[0].name());
        System.out.println(players[1].name());
    }
}

class player {
    String name;
    int[] hand;
    int points;

    public player(String n){
        name = n;
        points = 0;
    }

    public String name() {
        return name;
    }
}
