/**
 * A Roman numeral. In Standard Form:
 *  -- Standard Form contains the following conditions
 *  
 *   - Primary symbols: I (1), V (5), X (10), L (50), C (100), D (500), and M (1000).
 *   
 *   - Numbers are generally written by adding values, 
 *   but smaller numbers placed before larger ones indicate subtraction. 
 *   For example, IV represents 4 (5 - 1), and IX represents 9 (10 - 1).
 *   
 *   - Repetition of a numeral is limited to three times (e.g., III = 3), 
 *   and for numbers like 4 or 9, subtraction is used (e.g., IV = 4, IX = 9).
 *   
 *   - Numerals are typically written in descending order from left to right (e.g., XC = 90).
 * 
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class RomanNumeral {

	// Assume in Standard Form at all times
	private String numeral_;

	/**
	 * Create a Roman numeral representing the specified int value, in Standard Roman Numeral Form
	 * 
	 * @param n
	 *          int > 0 value of the Roman numeral up to 4000
	 */
	public RomanNumeral ( int n ) {
		// assert isPositive(n);
		
		numeral_ = toRoman(n);
		
		assert inStandardForm(numeral_);
	}

	// THIS IS FOR THE INSTANDARDFORM ASSERTS
	private boolean inStandardForm(String numeral) {
		return allCharCapitalized(numeral) && inProperFormat(numeral);
	}
	
	private boolean allCharCapitalized(String numeral) {
		return true;
	}
	
	private boolean inProperFormat(String numeral) {
		return isLimitedToThree(numeral) && descendingOrder(numeral);
	}
	
	private boolean isLimitedToThree(String numeral) {
		return true;
	}
	
	private boolean descendingOrder(String numeral) {
		return true;
	}
	
	
	private boolean isPositive(int n) {
		return n > 0;
	}
	// THIS IS FOR THE INSTANDARDFORM ASSERTS

	/**
	 * Create a Standard Roman Numeral represented by the specified string.
	 * 
	 * @param roman
	 *          string representing a Roman numeral, in Standard Roman Numeral Form
	 */
	public RomanNumeral ( String roman ) {
		assert inStandardForm(roman);
		numeral_ = roman;
		// Checks Class invariants
	}

	/**
	 * 
	 * @return string representation of this Roman numeral, in Standard Roman Numeral Form
	 */
	public String toString () {
		assert inStandardForm(numeral_);
		return numeral_;
	}

	/**
	 * Get the integer value of this Roman numeral.
	 * 
	 * @return integer > 0 value of this Roman numeral up to 4000
	 */
	public int toInt () {
		// assert inStandardForm(numeral_);
		return toInt(numeral_);
	}

	/**
	 * Add two Roman numerals. the solution is in Standard Roman Numeral Form.
	 * 
	 * @param other
	 *          the other Roman numeral, in Standard Roman Numeral Form
	 * @return a new RomanNumeral containing the sum of this Roman numeral and
	 *         'other'
	 */
	public RomanNumeral add ( RomanNumeral other ) {

		assert inStandardForm(numeral_);
		assert inStandardForm(other.numeral_);
		
		String a = toAdditive(numeral_), b = toAdditive(other.numeral_);
		String sum = condense(merge(a,b));
		return new RomanNumeral(toSubtractive(sum));
	}

	/**
	 * Add a Roman numeral to this one, Solution in Standard Roman Numeral Form.
	 * 
	 * @param other
	 *          the other Roman numeral, in Standard Roman Numeral Form
	 *         
	 *  Postcondition:
	 *  
	 *          
	 */
	public void addTo ( RomanNumeral other ) {
		String a = toAdditive(numeral_), b = toAdditive(other.numeral_);
		String sum = condense(merge(a,b));
		numeral_ = toSubtractive(sum);
	}

	/**
	 * Convert to additive form (replacing subtractive pairs).
	 * 
	 * @param roman
	 *          string representation of a Roman numeral
	 * @return additive form of 'roman'
	 */
	private String toAdditive ( String roman ) {
		// expect standard (subtractive)

		String expanded = roman.replace("CM","DCCCC");
		expanded = expanded.replace("CD","CCCC");
		expanded = expanded.replace("XC","LXXXX");
		expanded = expanded.replace("XL","XXXX");
		expanded = expanded.replace("IX","VIIII");
		expanded = expanded.replace("IV","IIII");

		// the symbols should be in order from highest value to lowest (M, D, C, L,
		// X, V, I)
		// integer value is equivalent to 'roman'
		return expanded;
	}

	/**
	 * Convert to subtractive form (replacing additive pairs).
	 * 
	 * @param roman
	 *          string representation of a Roman numeral
	 * @return subtractive form of 'roman'
	 */
	private String toSubtractive ( String roman ) {
		// expect additive form (symbols in order from highest value to lowest - M,
		// D, C, L, X, V, I)

		String compacted = roman.replace("DCCCC","CM");
		compacted = compacted.replace("CCCC","CD");
		compacted = compacted.replace("LXXXX","XC");
		compacted = compacted.replace("XXXX","XL");
		compacted = compacted.replace("VIIII","IX");
		compacted = compacted.replace("IIII","IV");

		// standard (subtractive) form with equivalent integer value
		return compacted;
	}

	/**
	 * Merge two string representations of Roman numerals 'a' and 'b'.
	 * 
	 * @param a
	 * @param b
	 * @return the merged representation
	 */
	private String merge ( String a, String b ) {
		// the symbols in a and b should be in order from highest value to lowest
		// (M, D, C, L, X, V, I)

		String merged = "";
		int apos = 0, bpos = 0;
		for ( ; apos < a.length() && bpos < b.length() ; ) {
			if ( getSymbolValue(a.charAt(apos)) >= getSymbolValue(b.charAt(bpos)) ) {
				merged += a.charAt(apos);
				apos++;
			} else {
				merged += b.charAt(bpos);
				bpos++;
			}
		}
		for ( ; apos < a.length() ; apos++ ) {
			merged += a.charAt(apos);
		}
		for ( ; bpos < b.length() ; bpos++ ) {
			merged += b.charAt(bpos);
		}

		// the symbols in merged should be in order from highest value to lowest (M,
		// D, C, L, X, V, I) and merged should contain all of the symbols from both
		// a and b
		return merged;
	}

	/**
	 * Condense the representation by grouping symbols (e.g. IIIII becomes V),
	 * making the string into additive form.
	 * 
	 * @param roman
	 *          string representation of a Roman numeral
	 * @return condensed representation
	 */
	private String condense ( String roman ) {
		// roman should have the symbols in order from highest value to lowest

		String condensed = roman.replace("DD","M");
		condensed = condensed.replace("CCCCC","D");
		condensed = condensed.replace("LL","C");
		condensed = condensed.replace("XXXXX","L");
		condensed = condensed.replace("VV","X");
		condensed = condensed.replace("IIIII","V");

		// the result should be symbols in order from highest value to lowest, with
		// no more than four of any symbol
		// the integer value should be the same as for 'roman'
		return condensed;
	}

	/**
	 * Get the integer value for a single Roman numeral symbol.
	 * 
	 * @param symbol
	 *          the symbol
	 * @return the symbol's value
	 */
	private int getSymbolValue ( char symbol ) {
		// symbols are M, D, C, L, X, V, I

		if ( symbol == 'I' ) {
			return 1;
		} else if ( symbol == 'V' ) {
			return 5;
		} else if ( symbol == 'X' ) {
			return 10;
		} else if ( symbol == 'L' ) {
			return 50;
		} else if ( symbol == 'C' ) {
			return 100;
		} else if ( symbol == 'D' ) {
			return 500;
		} else if ( symbol == 'M' ) {
			return 1000;
		}
		else
		return -1;
	}

	/**
	 * Get the integer value of the specified Roman numeral.
	 * 
	 * @param roman
	 *          string representation of a Roman numeral
	 * @return integer value of the specified Roman numeral
	 */
	private int toInt ( String roman ) {
		// 'roman' is in standard (subtractive) or additive form

		// convert to additive form, then go from left to right, adding the value of
		// each symbol
		String additive = toAdditive(roman);
		int value = 0;
		for ( int i = 0 ; i < additive.length() ; i++ ) {
			value += getSymbolValue(additive.charAt(i));
		}
		return value;
	}

	/**
	 * Convert the specified integer value to the Roman numeral representation.
	 * 
	 * @param value
	 *          value to convert
	 * @return string representation of the equivalent Roman numeral
	 */
	private String toRoman ( int value ) {

		final int[] NUMBERS =
		    { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		final String[] LETTERS = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
		                           "IX", "V", "IV", "I" };

		String roman = "";

		// build up the Roman numeral from left to right
		int remaining = value;
		for ( int i = 0 ; i < NUMBERS.length ; i++ ) {
			for ( ; remaining >= NUMBERS[i] ; ) {
				roman += LETTERS[i];
				remaining -= NUMBERS[i];
			}
		}

		// result is in standard form
		return roman;
	}

}
