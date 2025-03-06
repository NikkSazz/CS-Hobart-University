import java.awt.Color;

/**
 * Tester for Omino methods.
 * 
 * @author Nikolai Sazonov
 */
@SuppressWarnings("unused")
public class OminoTester {

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

		// -- add test cases below --------------------------------------------
		
		// Polymino Testers ???
		
		// get next rotation
		
		// get blocks
	}
	

	@SuppressWarnings("unused")
	private String blocksToString(Block[] b) {
		String s = "";
		for( var i : b ) {
			s += i.row + " " + i.column + " ";
		}
		return s;
	}

}
