package solitaire;

/**
 * represents the deck of cards used to generate
 * keystream values. Stores cards and supports
 * deck-manipulation operations.
 * 
 * @author Nikolai Sazonov
 */
public class SolitaireDeck {
	
	private class Node {
		SolitaireCard card_;
		Node next_;
		Node prev_;
		
		public Node(SolitaireCard c) {
			this.card_ = c;
			this.next_ = null;
			this.prev_ = null;
		}
	}
	
	private Node head_;
	private int deckSize_;
	
	
	public SolitaireDeck(int deckSize) {
		deckSize_ = deckSize;
	}
	
	/*
	 * Private method for testing
	 */
	private SolitaireDeck(int deckSize, String deck) {
		
	}
	
}
