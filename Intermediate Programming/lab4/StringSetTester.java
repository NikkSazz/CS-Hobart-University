/**
 * Tester for StringSet.
 * 
 * @author your name here
 */

public class StringSetTester {

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

		String[] input = { "cat", "dog", "emu", "moose", "platypus" };
		String[] notinput =
		    { "aardvark", "chameleon", "duck", "iguana", "ocelot", "zebra" };

		testAdd("add",new StringSet(input),"iguana",
		        "{ cat, dog, emu, iguana, moose, platypus }",6);
		testAdd("add first",new StringSet(input),"aardvark",
		        "{ aardvark, cat, dog, emu, moose, platypus }",6);
		testAdd("add last",new StringSet(input),"zebra",
		        "{ cat, dog, emu, moose, platypus, zebra }",6);
		testAdd("add duplicate",new StringSet(input),"moose",
		        "{ cat, dog, emu, moose, platypus }",5);

		testRemove("remove",new StringSet(input),"emu",
		           "{ cat, dog, moose, platypus }",4);
		testRemove("remove first",new StringSet(input),"cat",
		           "{ dog, emu, moose, platypus }",4);
		testRemove("remove last",new StringSet(input),"platypus",
		           "{ cat, dog, emu, moose }",4);
		testRemove("remove not present",new StringSet(input),"iguana",
		           "{ cat, dog, emu, moose, platypus }",5);

		// white box tests - binary search is tricky to get right, so test every
		// position (for present) and between every position (for not present)
		for ( int i = 0 ; i < input.length ; i++ ) {
			testContains("contains - " + i + " [" + input[i] + "]",
			             new StringSet(input),input[i],true);
		}
		for ( int i = 0 ; i < notinput.length ; i++ ) {
			testContains("contains not present - " + i + " [" + notinput[i] + "]",
			             new StringSet(input),notinput[i],false);
		}

		String[] input2 = { "cat", "chameleon", "emu", "moose", "ocelot", "zebra" };

		// black box tests - additional white box tests are needed for completeness
		testUnion("union",new StringSet(input),new StringSet(input2),
		          "{ cat, chameleon, dog, emu, moose, ocelot, platypus, zebra }",8);
		testIntersection("intersection",new StringSet(input),new StringSet(input2),
		                 "{ cat, emu, moose }",3);
	}

	public static void testAdd ( String name, StringSet set, String elt,
	                             String expected, int expectedSize ) {

		System.out.print(" -- " + name + " ... ");

		try {

			set.add(elt);

			if ( expected.equals(set.toString()) && expectedSize == set.size() ) {
				System.out.println("passed!");
			} else {
				System.out.println("failed!");
			}

			System.out.println("        got: [size " + set.size() + "] " + set);
			System.out
			    .println("   expected: [size " + expectedSize + "] " + expected);

		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			System.out.println();
		}
	}

	public static void testRemove ( String name, StringSet set, String elt,
	                                String expected, int expectedSize ) {

		System.out.print(" -- " + name + " ... ");

		try {

			set.remove(elt);

			if ( expected.equals(set.toString()) && expectedSize == set.size() ) {
				System.out.println("passed!");
			} else {
				System.out.println("failed!");
			}

			System.out.println("        got: [size " + set.size() + "] " + set);
			System.out
			    .println("   expected: [size " + expectedSize + "] " + expected);

		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			System.out.println();
		}
	}

	public static void testContains ( String name, StringSet set, String elt,
	                                  boolean expected ) {

		System.out.print(" -- " + name + " ... ");

		try {

			boolean result = set.contains(elt);

			if ( expected == result ) {
				System.out.println("passed!");
			} else {
				System.out.println("failed!");
			}

			System.out.println("        got: " + result);
			System.out.println("   expected: " + expected);

		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			System.out.println();
		}
	}

	public static void testUnion ( String name, StringSet set, StringSet other,
	                               String expected, int expectedSize ) {

		System.out.print(" -- " + name + " ... ");

		try {

			StringSet result = set.union(other);

			if ( expected.equals(result.toString())
			    && expectedSize == result.size() ) {
				System.out.println("passed!");
			} else {
				System.out.println("failed!");
			}

			System.out.println("        got: [size " + result.size() + "] " + result);
			System.out
			    .println("   expected: [size " + expectedSize + "] " + expected);

		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			System.out.println();
		}
	}

	public static void testIntersection ( String name, StringSet set,
	                                      StringSet other, String expected,
	                                      int expectedSize ) {

		System.out.print(" -- " + name + " ... ");

		try {

			StringSet result = set.intersection(other);

			if ( expected.equals(result.toString())
			    && expectedSize == result.size() ) {
				System.out.println("passed!");
			} else {
				System.out.println("failed!");
			}

			System.out.println("        got: [size " + result.size() + "] " + result);
			System.out
			    .println("   expected: [size " + expectedSize + "] " + expected);

		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			System.out.println();
		}
	}
}
