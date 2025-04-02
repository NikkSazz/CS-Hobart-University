

/**
 *  * @author Nikolai Sazonov
 */
public class SolitaireTester {

	/**
	 * @param args
	 */
	public static void main ( String[] args ) {
		
		testSolitaireDeck();
		
		// TODO test KeystreamGenerator

		// TODO test decrypt

		// TODO test encrypt
		
	}
	
	private static void testSolitaireDeck() {
		
		SolitaireDeck deck = new SolitaireDeck(26);
        String expected = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27A 27B";
        assert deck.toString().equals(expected) : "Deck does not match expected";
        System.out.println("Deck initialized correctly: " + deck.toString());
		
        
        // Test swapJokerA
        deck.swapJokerA();
        System.out.println("After swapJokerA: " + deck.toString());
        
        // Test swapJokerB
        deck.swapJokerB();
        System.out.println("After swapJokerB: " + deck.toString());
        
        // Test getBottomCard
        System.out.println("Bottom Card: " + deck.getBottomCard().getValue());
        
        // Test getNthCard
        int nthCard = deck.getNthCard(0).getValue();
        System.out.println("0th Card: " + nthCard);
        nthCard = deck.getNthCard(5).getValue();
        System.out.println("5th Card: " + nthCard);

        // Test tripleCut
        deck.tripleCut();
        System.out.println("After tripleCut: " + deck);
        
        // Test countCut
        deck.countCut(deck.getTopCard().getValue());
        System.out.println("After countCut: " + deck);
	}

}
