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
	
	/*
	 * example
	 * "1 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 27A 26 8 3 24 6 4"
	 */
	public String toString() {
		// TODO: Impliment
		return "";
	}
	
	public void swapJokerA() {
		// TODO: Impliment
		
		DoubleListNode jokerA = null;
		
		
		if(head_.getCard().isJokerA()) {
			jokerA = head_;
		}
		else {
			
			var card = head_.getNext();
			while(jokerA == null) {
				if(card.getCard().isJokerA()) {
					jokerA = card;
					break;
				}
				card = card.getNext();
			}
			
		}
		
		// prev is the card before jokerA before the operation.
		//  and is the card before swap after.
		DoubleListNode prev = head_.getPrev();
		// Swap is the next card before the operation
		DoubleListNode swap = jokerA.getNext();
		// Next is the card after jokerA after the operation
		DoubleListNode next = swap.getNext();
				
		
		prev.setNext(swap);
		swap.setPrev(prev);

		swap.setNext(jokerA);
		jokerA.setPrev(swap);
		
		jokerA.setNext(next);
		next.setPrev(jokerA);
		
		
		// if jokerA is first, the top card of the deck DOES change
		if(head_.getCard().isJokerA()) {
			head_ = swap;
		}
	}
	
	public void swapJokerB() {
		// TODO: Impliment
		
		DoubleListNode jokerB = null;
		
		// init jokerB, or find jokerB
		if(head_.getCard().isJokerB()) {
			jokerB = head_;
			// move head_ 2 to the right
			head_ = head_.getNext().getNext();
		}
		else {
			// find jokerB
			var card = head_.getNext();
			while(jokerB == null) {
				if(card.getCard().isJokerA()) {
					jokerB = card;
					break;
				}
				card = card.getNext();
			}
			
		}
		
		DoubleListNode left = jokerB.getPrev();
		DoubleListNode first = jokerB.getNext();
		DoubleListNode second = first.getNext();
		DoubleListNode right = second.getNext();

		left.setNext(first);
		first.setPrev(left);

		jokerB.setNext(right);
		right.setPrev(jokerB);

		second.setNext(right);
		right.setPrev(second);
		
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
