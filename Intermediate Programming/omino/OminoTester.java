
/**
 * Tester for Omino methods.
 * 
 * @author Nikolai Sazonov
 */
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
		
		// Polymino Testers
		
		var p = new Polymino();
	}

}
