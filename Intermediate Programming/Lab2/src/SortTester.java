/**
 * Tester for sort3.
 * 
 * @author your name here
 */
public class SortTester {

	public static void main ( String[] args ) {
		// demo of running a test case
		runTestCase("demo",10,20,30,"10 20 30");
		runTestCase("Switch B - C ", 2, 8, 4, "2 4 8");
		runTestCase("Switch A - C ", 9, 2, 7, "2 7 9");
		runTestCase("Switch A - B ", 9, 8, 10, "8 9 10");
		runTestCase("All Equal ", 2, 2, 2, "2 2 2");
		runTestCase("All Negative ", -2, -8, -4, "-8 -4 -2");
		runTestCase("All Equal and Negative ", -2, -2, -2, "-2 -2 -2");
		runTestCase("NAN -0, 0, 1", -0, 0, 1, "0 0 1");
		runTestCase("Two Zeros", 0, 0, 1, "0 0 1");
		runTestCase("Two Ones", 0, 1, 1, "0 1 1");
		runTestCase("NAN 2 ", 8, -0, -1, "-1 0 8");
		runTestCase("With Zero ", 2, 0, 6, "0 2 6");
		runTestCase("Negative Zero Positive ", -1, 0, 1, "-1 0 1");
		
	}

	/**
	 * Run a test case for sort3.
	 * 
	 * @param testcase
	 *          name of test case
	 * @param a
	 *          input to sort3 (a)
	 * @param b
	 *          input to sort3 (b)
	 * @param c
	 *          input to sort3 (c)
	 * @param expected
	 *          expected result (string printed)
	 */
	public static void runTestCase ( String testcase, int a, int b, int c,
	                                 String expected ) {
		System.out.println(" -- " + testcase);

		System.out.print("        got: ");
		Sort3Variant1.sort3(a,b,c);
		System.out.println("   expected: " + expected);
		System.out.println();
	}

}
