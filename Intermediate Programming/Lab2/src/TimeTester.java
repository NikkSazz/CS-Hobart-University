/**
 * Tester for time conversion.
 * 
 * @author your name here
 */
public class TimeTester {

	public static void main ( String[] args ) {
		// demo of running a test case
		runTestCase("demo","0815Z","EDT","4:15 AM (EDT)");
		runTestCase("Wrong Time Zone","0815Z","MSK","11:15 AM (MSK)");
		runTestCase("12 AM v 12 PM","1700Z","EST","12:00 PM (EST)");
		runTestCase("Standard Test","0900Z","EST","4:00 AM (EST)");
		runTestCase("CDT Test","1900Z","CDT","2:00 PM (CDT)");
	}

	public static void runTestCase ( String name, String zulu, String timezone,
	                                 String result ) {
		System.out.println("\n -- " + name);

		Time time = new TimeVariant1(zulu);
		String converted = time.convert(timezone);

		System.out.print(" got: " + converted + " / expected: " + result + " ... ");

		if ( result.equals(converted) ) {
			System.out.println("passed!");
		} else {
			System.out.println("failed!");
		}
	}
}
