package flip;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Nikolai Sazonov
 */
public class ooFlip {

	private static Scanner scanner = new Scanner(System.in);
	private static player player1;
	private static player player2;
	private static ArrayList<Integer> middle = new ArrayList<>();

	public static void main ( String[] args ) {

		System.out.print("Please Enter Player One's Name: ");
		String p1Name = scanner.nextLine();
		System.out.print("Please Enter Player Two's Name: ");
		String p2Name = scanner.nextLine();
		player1 = new player(1,p1Name);
		player2 = new player(2,p2Name);

		while ( player1.getScore() < 50 && player2.getScore() < 50 ) {
			player1.dice.resetToFlippable();
			player2.dice.resetToFlippable();
			middle.clear();

			boolean isPlayer1Turn =
			    player1.dice.diceCount() <= player2.dice.diceCount();
			System.out.println((isPlayer1Turn ? player1.name : player2.name)
			    + " goes first!\n");

			while ( player1.dice.diceCount() > 0 && player2.dice.diceCount() > 0 ) {
				printGameState();
				var currentPlayer = isPlayer1Turn ? player1 : player2;
				var opponent = isPlayer1Turn ? player2 : player1;

				System.out.println(currentPlayer.name + "'s turn.");
				String input = currentPlayer.getInput(scanner);

				if ( input.startsWith("p") ) {
					currentPlayer.play(input,opponent,middle);
				} else if ( input.startsWith("f") ) {
					currentPlayer.flip(input);
				}

				isPlayer1Turn = !isPlayer1Turn;
			}

			handleRoundEnd();
		}

		declareWinner();
	}

	private static void printGameState () {
		System.out
		    .println("\n-------------------------------------------------------------------------------");
		player1.printDice();
		player2.printDice();
		printMiddle();
	}

	private static void printMiddle () {
		System.out.print("The Middle:\t");
		for ( int die : middle ) {
			System.out.print(die + " ");
		}
		System.out.println();
	}

	private static void handleRoundEnd () {
		player winner = player1.dice.diceCount() > 0 ? player2 : player1;
		player loser = player1.dice.diceCount() > 0 ? player1 : player2;
		int points = loser.dice.diceCount();

		winner.addPoints(points);

		System.out.println(winner.name + " has won the round!");
		System.out
		    .println(points + " points have been added to " + winner.name + "\n");
		System.out.println(player1.name + " has " + player1.getScore() + " points");
		System.out
		    .println(player2.name + " has " + player2.getScore() + " points\n");
	}

	private static void declareWinner () {
		player winner = player1.getScore() >= 50 ? player1 : player2;
		System.out.println("\t" + winner.name + " HAS WON!!");
	}
}

// Required Class from the document

class player {
	dice dice;
	int id;
	public String name;
	int score;

	public player ( int id, String name ) {
		this.name = name;

		score = 0;
		this.id = id;
		this.dice = new dice();
	} // player Constructor

	public int getId () {
		return id;
	}

	public String getInput ( Scanner scanner ) {
		System.out.println("Please choose to either:");
		System.out.println("\tFlip (f) one of your dice (ex: f2), or");
		System.out
		    .println("\tPlay (p) one of the opponent's dice to the middle (ex: p4)");

		String input = scanner.nextLine();
		while ( !isValidInput(input) ) {
			System.out.println("Invalid input. Try again:");
			input = scanner.nextLine();
		}
		return input;
	}

	private boolean isValidInput ( String input ) {
		if ( input.length() != 2 ) return false;

		char action = input.charAt(0);
		int index = Character.getNumericValue(input.charAt(1)) - 1;

		if ( action == 'f' && index >= 0 && index < dice.diceCount() ) return true;
		if ( action == 'p' && index >= 0 && index < dice.diceCount() ) return true;

		return false;
	}

	public void printDice () {
		System.out.print(name + ":\t");
		for ( int die : dice.dice ) {
			System.out.print(die + " ");
		}
		System.out.println();
	}

	public int getScore () {
		return score;
	} // getScore()

	public void addPoints ( int points ) {
		System.out
		    .println(points + " points have been added to " + name + "'s points");
		score += points;
	} // addPoints()

} // class player

class dice {
	ArrayList<Integer> dice;
	ArrayList<Boolean> flippable;

	public dice () {
		dice = new ArrayList<Integer>();
		for ( int i = 0 ; i < 5 ; i++ ) {
			dice.add(rollDice());
			flippable.add(false);
		} // for i 1 - 5

	}// dice constructor

	private int rollDice () {
		var r = new Random();
		return r.nextInt(6) + 1;
	}

	public void flipDiceAt ( int i ) {
		var newVal = Math.abs(dice.get(i) - 7);
		dice.set(i,newVal);
		flippable.set(i,true);
	} // flipDiceAt

	public Boolean isFlippable ( int i ) {
		return !flippable.get(i);
	}

	public void resetToFlippable () {
		for ( int i = 0 ; i < flippable.size() ; i++ ) {
			flippable.set(i,false);
		}
	}

	public void addDice ( int dice ) {
		this.dice.add(dice);
	}

	public void addDice ( ArrayList<Integer> arr ) {
		for ( var i : arr ) {
			dice.add(i);
		}
	} // addDice with list

	public void removeDice ( int index ) {
		dice.remove(index);
	}

	public void removeDice ( ArrayList<Integer> indexes ) {
		dice.removeAll(indexes);
	}

	public int diceCount () {
		return dice.size();
	}

	public void print () {
		System.out.print("Dice:");
		for ( var i : dice ) {
			System.out.println("\t" + i);
		}
	}

} // class dice
