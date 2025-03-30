// package solitaire;

/**
 * represents the deck of cards used to generate
 * keystream values. Stores cards and supports
 * deck-manipulation operations.
 * 
 * @author Nikolai Sazonov
 */
public class SolitaireDeck {
		
	private DoubleListNode head_;
	private int deckSize_;
	
	
	public SolitaireDeck(int deckSize) {
		deckSize_ = deckSize;
		
		// if(deckSize <= 2) { throw new Exception(""); }
		
		head_ = new DoubleListNode(new SolitaireCard(1));
		DoubleListNode curr = head_;
		DoubleListNode newNode;
		for (int i = 1; i <= deckSize; i++) {
			newNode = new DoubleListNode(new SolitaireCard(i));
			curr.setNext(newNode);
			newNode.setPrev(curr);
			curr = newNode;
		}
		
		// Jokers aswell
		newNode = new DoubleListNode(new SolitaireCard(deckSize + 1, 'A'));
		curr.setNext(newNode);
		newNode.setPrev(curr);
		curr = newNode;
		
		newNode = new DoubleListNode(new SolitaireCard(deckSize + 1, 'B'));
		curr.setNext(newNode);
		newNode.setPrev(curr);
		curr = newNode;
		
		// Finish circle
		curr.setNext(head_);
		head_.setPrev(curr);
		
	}
	
	/*
	 * Private method for testing
	 * String str = "1 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 27A 26 8 3 24 6 4";
	 */
	private SolitaireDeck(int deckSize, String deck) throws Exception {
		deckSize_ = deckSize;
		
		String[] cards = deck.split(" +");
		
		if(cards.length < 1) { 
			throw new Exception("String deck parameter improper format");
		}
		
		head_ = new DoubleListNode(new SolitaireCard(cards[0]));
		
		DoubleListNode curr = head_;
		DoubleListNode newNode;
		
		for(int i = 1; i < cards.length; i++) {
			newNode = new DoubleListNode(new SolitaireCard(cards[i]));
			curr.setNext(newNode);
			newNode.setPrev(curr);
			curr = newNode;
		}
		
		// Finish circle
		curr.setNext(head_);
		head_.setPrev(curr);
	}
	
	public String toString() {
		// TODO: Impliment
		return "";
	}
	
	public void swapJokerB() {
		// TODO: Impliment
	}
	
	public void swapJokerA() {
		// TODO: Impliment
	}
	
	public void countCut() {
		// TODO: Impliment
	}
	
	public void tripleCut() {
		// TODO: Impliment
	}
	
	public SolitaireCard getBottomCard() {
		var card = head_;
		for(int i = 0; i < deckSize_ / 2; i++) {
			card = card.getNext();
		}
		return card.getCard();
	}
	
	public SolitaireCard getNthCard(int n) {
		var card = head_;
		for(int i = 0; i < n; i++) {
			card = card.getNext();
		}
		return card.getCard();
	}
	
	public SolitaireCard getTopCard() {
		return head_.getCard();
	}
	
	public int getDeckSize() {
		return deckSize_;
	}
	
}
