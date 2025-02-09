package cs225;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Main class
public class ooflip {
    public static void main(String[] args) {
        var game = new Game();
        game.start();
    }
}

// Game class to manage the overall game logic
class Game {
    private Scanner scanner = new Scanner(System.in);
    private Player player1;
    private Player player2;
    private ArrayList<Integer> middle = new ArrayList<>();

    public Game() {
        System.out.print("Please Enter Player One's Name: ");
        String p1Name = scanner.nextLine();
        System.out.print("Please Enter Player Two's Name: ");
        String p2Name = scanner.nextLine();
        player1 = new Player(p1Name);
        player2 = new Player(p2Name);
    }

    public void start() {
        while (player1.getPoints() < 50 && player2.getPoints() < 50) {
            player1.initializeHand();
            player2.initializeHand();
            middle.clear();

            boolean isPlayer1Turn = player1.getHandSum() <= player2.getHandSum();
            System.out.println((isPlayer1Turn ? player1.getName() : player2.getName()) + " goes first!\n");

            while (player1.hasDice() && player2.hasDice()) {

                printGameState();
                var currentPlayer = isPlayer1Turn ? player1 : player2;
                var opponent = isPlayer1Turn ? player2 : player1;

                System.out.println(currentPlayer.getName() + "'s turn.");
                String input = currentPlayer.getInput(scanner, opponent.getHand());

                if (input.startsWith("p")) {
                    currentPlayer.play(input, opponent, middle);
                } else if (input.startsWith("f")) {
                    currentPlayer.flip(input);
                }

                isPlayer1Turn = !!!isPlayer1Turn;
            } // while both have dice

            handleRoundEnd();
        }

        declareWinner();
    }

    private void printGameState() {
        System.out.println("\n-------------------------------------------------------------------------------");
        player1.printHand();
        player2.printHand();
        printMiddle();
    }

    private void printMiddle() {
        System.out.print("The Middle:\t");
        for (int die : middle) {
            System.out.print(die + " ");
        }
        System.out.println();
    }

    private void handleRoundEnd() {
        Player winner = player1.hasDice() ? player2 : player1;
        Player loser = player1.hasDice() ? player1 : player2;
        int points = loser.getHandSum();
        winner.addPoints(points);

        System.out.println(winner.getName() + " has won the round!");
        System.out.println(points + " points have been added to " + winner.getName() + "\n");
        System.out.println(player1.getName() + " has " + player1.getPoints() + " points");
        System.out.println(player2.getName() + " has " + player2.getPoints() + " points\n");
    }

    private void declareWinner() {
        Player winner = player1.getPoints() >= 50 ? player1 : player2;
        System.out.println("\t" + winner.getName() + " HAS WON!!");
    }
}

// Player class to manage player-specific data and actions
class Player {
    private final String name;
    private final ArrayList<Integer> hand = new ArrayList<>();
    private int points = 0;
    private String lastFlipCommand = "f0";

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void initializeHand() {
        hand.clear();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            hand.add(random.nextInt(6) + 1);
        }
    }

    public boolean hasDice() {
        return !hand.isEmpty();
    }

    public int getHandSum() {
        return hand.stream().mapToInt(Integer::intValue).sum();
    }

    public void printHand() {
        System.out.print(name + ":\t");
        for (int die : hand) {
            System.out.print(die + " ");
        }
        System.out.println();
    }

    public String getInput(Scanner scanner, ArrayList<Integer> opponentHand) {
        System.out.println("Please choose to either:");
        System.out.println("\tFlip (f) one of your dice (ex: f2), or");
        System.out.println("\tPlay (p) put one of the opponent's dice to the middle (ex: p4)");

        String input = scanner.nextLine();
        while (!isValidInput(input, opponentHand)) {
            System.out.println("Invalid input. Try again:");
            input = scanner.nextLine();
        }
        return input;
    }

    private boolean isValidInput(String input, ArrayList<Integer> opponentHand) {
        if (input.length() != 2) return false;

        char action = input.charAt(0);
        int index = Character.getNumericValue(input.charAt(1)) - 1;

        if (action == 'f' && index >= 0 && index < hand.size()) return true;
        if (action == 'p' && index >= 0 && index < opponentHand.size()) return true;

        return false;
    }

    public void play(String input, Player opponent, ArrayList<Integer> middle) {
        int index = Character.getNumericValue(input.charAt(1)) - 1;
        int die = opponent.hand.remove(index);
        middle.add(die);

        System.out.println(name + " played " + die + " to the middle.");
    }

    public void flip(String input) {
        int index = Character.getNumericValue(input.charAt(1)) - 1;
        int flippedValue = 7 - hand.get(index);
        hand.set(index, flippedValue);

        System.out.println(name + " flipped a die to " + flippedValue + ".");
    }
}
