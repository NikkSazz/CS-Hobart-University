/**
 * Tester for Rock Paper Scissors.
 * 
 * @author your name here
 */
public class RPSTester {

	public static void main ( String[] args ) {

		runTestCaseRound("R v R", 'R', 'R', -1);
		runTestCaseRound("R v P", 'R', 'P', 2);
		runTestCaseRound("R v S", 'R', 'S', 1);
		
		runTestCaseRound("P v P", 'P', 'P', -1);
		runTestCaseRound("P v R", 'P', 'R', 1);
		runTestCaseRound("P v S", 'P', 'S', 2);
		
		runTestCaseRound("S v S", 'S', 'S', -1);
		runTestCaseRound("S v R", 'S', 'R', 2);
		runTestCaseRound("S v P", 'S', 'P', 1);
		
		System.out.println("\n\n");
		
		// Game
		runTestCaseGame("Standard", "RRPPSS", "RPSRPS", 2, 2);
		runTestCaseGame("Not Equal Length", "RRPPSS", "RPSRPSSS", 2, 2);
		runTestCaseGame("Identical", "RPSRPS", "RPSRPS", 2, -1);
		runTestCaseGame("wins > rounds", "RP", "RP", 3, -1);
		
	}
	
	private static void runTestCaseRound(String caseName, Character o, Character t, Integer expctd) {
		System.out.println("-- " + caseName + " Round");
		var got = RPSVariant1.getRoundWinner(o, t);
		System.out.println("        got: " + got);
		System.out.println("   expected: " + expctd);
		System.out.println(got == expctd ? "Passed" : "Failed" + "\n");

	}

	private static void runTestCaseGame(String caseName, String p1, String p2, int nW, int expctd) {
		System.out.println("\n-- " + caseName + " Round");
		System.out.println("how many wins needed? " + nW);
		var got = RPSVariant1.getGameWinner(p1, p2, nW);
		System.out.println("        got: " + got);
		System.out.println("   expected: " + expctd);
		System.out.println(got == expctd ? "Passed" : "Failed");
	} // func
}
