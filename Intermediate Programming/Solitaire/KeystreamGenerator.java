

/**
 * handles generating the keystream values,
 * used for getting an integer value given from the passphrase, 
 * so only those using the same passphrase decrypt that message
 * 
 * @author Nikolai Sazonov
 */
public class KeystreamGenerator {
	
	SolitaireDeck deck_;
	
	public KeystreamGenerator(int deckSize, String passphrase) {
		deck_ = new SolitaireDeck(deckSize);
		
		// get same keystream sequence
		// *keying* the deck
		for(char c : passphrase.toCharArray()) {
			
			if(!Character.isLetter(c)) { continue; }
			
			deck_.swapJokerA();
			deck_.swapJokerB();
			deck_.tripleCut();
			deck_.countCut(deck_.getBottomCard().getValue());
			
			int number = Character.toUpperCase(c) - 'A' + 1; // Convert letter to number using ascii
			deck_.countCut(number);
			
		}
		
		// No reason to assert, as SolitaireDeck should assert everything
		
	}
	
	public int nextKeystreamValue() {
	    while (true) {
	        deck_.swapJokerA();
	        deck_.swapJokerB();
	        deck_.tripleCut();
	        deck_.countCut(deck_.getBottomCard().getValue());

	        int topCardVal = deck_.getTopCard().getValue();
	        var check = deck_.getNthCard(topCardVal);

	        if (!check.isJoker()) { 
	            return check.getValue();
	        }
	    }
	}

	
	String getDeckString() {
		return deck_.toString();
	}
	
}
