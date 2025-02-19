import java.util.Scanner;

/**
 * Test program for RomanNumeral.
 * 
 * @author your name here
 */
public class RomanMain {

	public static void main ( String[] args ) {
		// the following is the "official" way to programmatically determine whether
		// or not assertion checking is turned on
		// it is included here because it is easy to accidentally omit setting the
		// proper switch in the run configuration and thus not realize that
		// assertions aren't being checked
		boolean assertsEnabled = false;
		assert assertsEnabled = true; // Intentional side-effect!!!
		System.out.println("assertions are on: " + assertsEnabled);
		System.out.println();

		Scanner scanner = new Scanner(System.in);
		System.out
		    .println("enter a roman numeral or arabic number to convert, or a sum to compute");
		System.out.println("sums are entered in the form: + num1 num2");
		System.out.println("enter 'q' to quit");

		for ( ; true ; ) {

			System.out.print("> ");
			String input = scanner.next();
			if ( input.equals("q") ) {
				break;
			}

			if ( input.equals("+") ) { // sum
				String value1 = scanner.next(), value2 = scanner.next();
				RomanNumeral roman1, roman2;
				if ( Character.isDigit(value1.charAt(0)) ) { // int
					roman1 = new RomanNumeral(Integer.parseInt(value1));
				} else { // Roman numeral
					roman1 = new RomanNumeral(value1);
				}
				if ( Character.isDigit(value2.charAt(0)) ) { // int
					roman2 = new RomanNumeral(Integer.parseInt(value2));
				} else { // Roman numeral
					roman2 = new RomanNumeral(value2);
				}

				RomanNumeral sum = roman1.add(roman2);
				System.out.println(roman1.toString() + " + " + roman2.toString() + " = "
				    + sum.toString());
				System.out.println(roman1.toInt() + " + " + roman2.toInt() + " = "
				    + sum.toInt());

			} else if ( Character.isDigit(input.charAt(0)) ) { // int -> Roman numeral
				RomanNumeral roman = new RomanNumeral(Integer.parseInt(input));
				System.out.println(roman.toInt() + " = " + roman.toString());

			} else { // Roman numeral -> int
				RomanNumeral roman = new RomanNumeral(input);
				System.out.println(roman.toString() + " = " + roman.toInt());
			}
		}

		scanner.close();
	}
}
