package flip;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
*
*/
public class Flip {
	
	private static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		
		// Get player names
		System.out.println("Please Enter Player One's Name: ");
		String oneName = s.nextLine();
		System.out.println("\nPlease Enter Player Two's Name: ");
		String twoName = s.nextLine();
		System.out.println();
		/*
		var oneName = "Player One";
		var twoName = "Player Two";
		*/
		// init players
		ArrayList<Integer> p1 = setup();
		printHand(oneName, p1);
		ArrayList<Integer> p2 = setup();
		printHand(twoName, p2);
		var middle = new ArrayList<Integer>();
		printHand("The Middle", middle);
		
		// start game until a player has no dies
		while(p1.size() > 0 && p2.size() > 0) {
			playerGo(oneName, p1, p2, twoName);
		}
		
	} // main
	
	private static void playerGo(String playerName,  ArrayList<Integer> p1, ArrayList<Integer> p2, String oppName) {
		System.out.print("\n" + playerName + "'s turn. Please chose to either\n"
				+ "\tFlip (f) one of your dice (ex: f2), or\n"
				+ "\tPlay (p) put one of the opponent's dice to the middle (ex: p4)\n"
				+ "\tleave no spaces, or any extra characters");
		
		String input = s.nextLine();
		// is input valid
		/*
		while(!isValidInput(input, p1, p2)) {
			System.out.println("\n" + input + " is not a valid input,\n please type either 'f' or 'p'\n"
					+ " followed by a number between 0...6");
			input = s.nextLine();
		}*/
		
	} // playerGo
	
	private static Boolean isValidInput(String input, ArrayList<Integer> p1, ArrayList<Integer> p2) {
		char[] c = input.toCharArray();
		if(c.length > 2 || c.length < 2)
			return false;
		
		if(c[0] != 'f' && c[0] != 'p')
			return false;
		
		if(Character.isDigit(c[1])) {
			int number = Character.getNumericValue(c[1]);
			if(number >= 1 && number <= 6)
				return true;
		}	
		return false; // second char not a digit
	}
	
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



