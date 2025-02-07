package flip2;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
*
*/
public class flip {
	
	private static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		
		// Get player names
		System.out.println("Please Enter Player One's Name: ");
		String oneName = s.nextLine();
		System.out.println("\nPlease Enter Player Two's Name: ");
		String twoName = s.nextLine();
		System.out.println();

		// init players
		int onePts = 0;
		int twoPts = 0;
		ArrayList<Integer> p1;
		ArrayList<Integer> p2;
		var middle = new ArrayList<Integer>();
		
		
		
		// start game until a player has no dies
		while(onePts < 50 && twoPts < 50) {
			//
			p1 = setup();
			p2 = setup();
			middle.clear();
			
			// who goes first, if sum of player ones dice is greater than the second, one goes
			Boolean p1Go = listSum(p1) <= listSum(p2) ? true : false;
			System.out.print(p1Go ? oneName : twoName);
			System.out.println(" goes first!\n");
			

			var onePrevFlip = "f0";
			var twoPrevFlip = "f0";
			
			//game starts
			while(p1.size() > 0 && p2.size() > 0) {
				System.out.println("\n"
						+ "-------------------------------------------------------------------------------"
						+ "\nNext turn..\nHands:");
				printHand(oneName, p1);
				printHand(twoName, p2);
				printHand("The Middle", middle);
				
				String playerName = p1Go ? oneName : twoName;
				System.out.print("\n" + playerName + "'s turn. Please chose to either\n"
						+ "\tFlip (f) one of your dice (ex: f2), or\n"
						+ "\tPlay (p) put one of the opponent's dice to the middle (ex: p4)\n"
						+ "\tleave no spaces, or any extra characters");
				String input = s.nextLine();
				
				while(!isValidInput(input, p1Go ? p1 : p2, p1Go ? p2 : p1)
						&& !input.equals(p1Go ? onePrevFlip : twoPrevFlip)) {
					System.out.println("Please try again: ");
					input = s.nextLine();
				} // while isValidInput
				
				
				char[] c = input.toCharArray();
				int inputVal = Character.getNumericValue(c[1]);
				inputVal--;//zero-indexed
				System.out.println();
				if(c[0] == 'p') {
					//reset flips
					if(p1Go)
					onePrevFlip = "f0";
					else
					twoPrevFlip = "f0";
					
					System.out.println(playerName + " has chosen to Play! ");
					var val = p1Go ? p2.remove(inputVal) : p1.remove(inputVal);
					middle.add(val);
					
					play(middle, p1Go ? p2 : p1, val, p1Go ? twoName : oneName);
				} // play
				else { // flip
					System.out.println(playerName + " has chosen to Flip! ");
					if(p1Go) {
						onePrevFlip = input;
						int newval = Math.abs(p1.get(inputVal) - 7);
						p1.set(inputVal, newval);
					}
					else {
						twoPrevFlip = input;
						int newval = Math.abs(p2.get(inputVal) - 7);
						p2.set(inputVal, newval);
					}
				}// flip
				
				p1Go = !p1Go;
			} // while has dice
			

			// who won
			String winner = p2.size() == 0 ? oneName : twoName;
			System.out.println(winner + " has won the round");
			// how many points to add
			int sum = 0;
			sum = listSum(p2.size() == 0 ? p1 : p2);
			System.out.println(sum + " points have been added to " + winner);
			if(p2.size() == 0)
				onePts += sum;
			else
				twoPts += sum;
			
			
			// print points
			System.out.println(oneName + " has " + onePts + " points");
			System.out.println(twoName + " has " + twoPts + " points\n");
			
			
		} // while 50 pts
		
		String winner = onePts < 50 ? twoName : oneName;
		System.out.println("\t" + winner + " HAS WON!!");
		
	} // main
	
	private static void play(ArrayList<Integer> m, ArrayList<Integer> p, int val, String name) {
		System.out.println("\n\t" + name + " can take from the middle, please select the index of the dice you want to take"
				+ "\nSelect one dice at a time, you can take more after, value of the taken dies should not exceed '"
				+ val + "'");
		
		// does middle have a possible dice to take
		while(val > 0) {
			int midMin = minFromMiddle(m);
			if(val <= midMin) {
				System.out.println("\n\tNo avaliable dice to take from middle\n");
				break;
			}
			
			System.out.println("please select the index of the dice you want to take\n"
					+ "\nSelect one dice at a time, you can take more after, value of the taken dice should not exceed"
					+ " '" + val + "'\t You can also press 'c' to continue without taking dice");
			
			printHand("middle", m);
			// Get input and validate
			String input = s.nextLine();
			while(!properTakeFromMiddle(input, m.size())) {
				System.out.println("Please type correctly, an index between 1-" + m.size());
				input = s.nextLine();
			}
			
			if(input.equals("c")) {
				break;
			}
			
			char[] c = input.toCharArray();
			var midIndex = Character.getNumericValue(c[0]);
			var v = m.remove(midIndex - 1);
			p.add(v);
			
			System.out.println(v + " has been added to " + name + "'s Hand");
			
		}//while val > 0
		
	} // play
	
	private static Boolean properTakeFromMiddle(String s, int mSize) {
		char[] c = s.toCharArray();
		if(c.length != 1) {
			System.out.println("Input must be one key");
			return false;
		}
		
		if(c[0] == 'c') {
			return true;
		}
		
		if(Character.isDigit(c[0])) {
			int n = Character.getNumericValue(c[0]);
			if(n > mSize || n < 0) {
				System.out.println("Input must be within 1-" + mSize);
				return false;
			}
		}
		return true;
	} // properTakeFromMiddle
	
	private static int minFromMiddle(ArrayList<Integer> m) {
		int min = Integer.MAX_VALUE;
		for(var i : m) {
			min = Math.min(min, i);
		}
		return min;
	}
	
	private static int listSum(ArrayList<Integer> l) {
		int sum = 0;
		for(int i : l) {
			sum += i;
		} // foreach
		return sum;
	} // listSum
	
	private static Boolean isValidInput(String input, ArrayList<Integer> p1, ArrayList<Integer> p2) {
		char[] c = input.toCharArray();
		if(c.length != 2) {
			System.out.println("Input length not 2");
			return false;
		}
			
		
		if(c[0] != 'f' && c[0] != 'p') {
			System.out.println("First part must be either 'f' or 'p'");
			return false;
		}
		
		if(Character.isDigit(c[1])) {
			int number = Character.getNumericValue(c[1]);
			if(c[0] == 'p') {
				if(number >= 1 && number <= p2.size())
					return true;
				else {
					System.out.println("Number must be within 1-"+p1.size());
					return false;
				}
			}
			else { // if 'f'
				if(number >= 1 && number <= p1.size())
					return true;
				else {
					System.out.println("Number must be within 1-"+p1.size());
					return false;
				}
			}
		}	
		
		System.out.println("Second Character is not a digit");
		return false;
		
	} // isValidInput
	
	private static ArrayList<Integer> setup(){
		var p = new ArrayList<Integer>();
		var r = new Random();
		for(int i = 0; i < 5; i++) {
			p.add(r.nextInt(6) + 1);
		}
		return p;
	} // setup
	
	
	private static void printHand(String pname, ArrayList<Integer> p1) {
		System.out.print("\n" + pname +":\t");
		for(var d : p1) {
			System.out.print(d + " ");
		}
		System.out.println();
	} // print hand
} // class Flip
