

/**
 * handles generating the keystream values
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
		
		deck_.swapJokerA();
		deck_.swapJokerB();
		deck_.tripleCut();
		deck_.countCut(deck_.getBottomCard().getValue());
		
		int topCardVal = deck_.getTopCard().getValue();
		
		var check = deck_.getNthCard(topCardVal);
		if(check.isJoker()) { 
			return check.getValue();
		}
		
		// repeat if not joker
		return nextKeystreamValue();
	}
	
	String getDeckString() {
		return deck_.toString();
	}
	
}
