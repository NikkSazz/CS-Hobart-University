

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
		
		// testKeystreamGenerator();
		
		// TODO test decrypt
		// TODO test encrypt
		
		testEncryptDecrypt();
		
	}
	
	private static void testSolitaireDeck() {
		
    	System.out.println("\n\n\tTest Solitaire Deck\n");
		
		SolitaireDeck deck = new SolitaireDeck(26);
        String expected = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27A 27B";
        assert deck.toString().equals(expected) : "Deck does not match expected";
        System.out.println("Deck initialized correctly: " + deck.toString());
		
        
        // Test swapJokerA
        deck.swapJokerA();
        System.out.println("After swapJokerA: " + deck.toString());
        
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
	
	private static void testSwapJokerB() {
		
		// Test swapJokerB
		SolitaireDeck deck = new SolitaireDeck(26);
        System.out.println("Before swapJokerB: " + deck.toString());
        deck.swapJokerB();
        System.out.println("After swapJokerB: " + deck.toString());
        
        String nxtLast = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27B 27A";
        deck = new SolitaireDeck(26, nxtLast);
        System.out.println("Before swapJokerB next to last: " + deck.toString());
        deck.swapJokerB();
        System.out.println("After swapJokerB next to last: " + deck.toString());
        
        String first = "27B 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27A";
        deck = new SolitaireDeck(26, first);
        System.out.println("Before swapJokerB first: " + deck.toString());
        deck.swapJokerB();
        System.out.println("After swapJokerB first: " + deck.toString());
        
        String normal = "1 2 3 4 5 6 7 8 9 10 11 12 27B 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27A";
        deck = new SolitaireDeck(26, normal);
        System.out.println("Before swapJokerB normal: " + deck.toString());
        deck.swapJokerB();
        System.out.println("After swapJokerB normal: " + deck.toString());
		
	}
	
	private static void testKeystreamGenerator() {

    	System.out.println("\n\n\tTest Keystream Generator\n");
    	
		// Test constructor
        KeystreamGenerator generator = new KeystreamGenerator(26, "SECRET");
        System.out.println("Initialized deck: " + generator.getDeckString());
        
        for (int i = 0; i < 3; i++) {
            System.out.println("keystream value: " + generator.nextKeystreamValue());
        }
    }
	
    private static void testEncryptDecrypt() {
    	
    	System.out.println("\n\n\tTest Encrypt Decrypt\n");
    	
        final var message = "NIKOLAI SAZONOV";
        final var passphrase = "TEST";
        int deckSize = 26;
        System.out.println("Original message:\t" + message);
        
        String encryptedMessage = SolitaireEncoder.encrypt(message, passphrase, deckSize);
        System.out.println("Encrypted message:\t" + encryptedMessage);
        
        String decryptedMessage = SolitaireEncoder.decrypt(encryptedMessage, passphrase, deckSize);
        System.out.println("Decrypted message:\t" + decryptedMessage);
        
    }
	
}