package cs225;
import java.util.ArrayList;
import java.util.Random;
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
        @SuppressWarnings("unused")
        var m = new player("Middle");

        while(players[0].points <= 50 && players[1].points <= 50){
            setupRound();
            playRound();
        } // While players.points <= 50 
        
        
        if(players[0].points <= 50) {
            System.out.println("Player One Wins");
        } // if player 1 wins
        else {
            System.out.println("Player Two Wins");
        } // if player 2 wins

    } // main

    private static void playRound() {
        System.out.println("playRound");
    } // playRound

    private static void setupRound() {
        m.clearHand();
    }
} // class ooflip

class player {

    String name;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Integer> hand;
    public int points;

    public player(String n) {
        name = n;
        points = 0;
        hand = new ArrayList<>();
    }

    public void randomizeHand() {
        hand.clear();
        var r = new Random();
        for(int i = 0; i < 5; i++){
            hand.add(r.nextInt(6) + 1);
        }
    } // randomize Hand

    public void printHand() {
        System.out.println(name + "'s hand:\n");
        for(var i : hand){
            System.out.print("\t" + i);
        }
        System.out.println("\n");
    }

    public void addToHand(int val) {
        hand.add(val);
    }

    public void clearHand() {
        hand.clear();
    }

    public void wins(int points) {
        points += this.points;
        System.out.println(name + "'s points have been increased to " + points + "!");
    } // wins

    public String name() {
        return name;
    }
}
