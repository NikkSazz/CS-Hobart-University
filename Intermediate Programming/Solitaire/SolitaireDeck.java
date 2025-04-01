// package solitaire;

import java.util.ArrayList;

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
		ArrayList<String> list = new ArrayList<>();

		var curr = head_;
		for(int i = 1; i <= deckSize_; i++) {
			list.add(curr.getCard().toString());
			curr = curr.getNext();
		}
		
		return String.join(" ", list);
		// using an arrayList is slightly more efficient than str+="..", as Strings are immutable in Java
	}
	
	public void swapJokerA() {
		
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
	
	/*
	 * the cards above the first joker 
	 *  (the one closest to the top of the deck) 
	 *  are exchanged with the cards below the second joker. 
	 *  For this step, it doesn't matter which joker is A or B.
	 *  
	 *  before: 1 23 25 2 22 27B . . . 27A 8 3 24 6 4
	 *	  after:  8 3 24 6 4 27B . . . 27A 1 23 25 2 22
	 *  
	 */
	public void tripleCut() {
		
		// = null in case they were not modified, it should not affect anything
		DoubleListNode firstLeft = head_;
		DoubleListNode firstRight = null;
		DoubleListNode firstJoker = null;
		
		DoubleListNode secondLeft = null;
		DoubleListNode secondRight = head_.getPrev();
		DoubleListNode secondJoker = null;
		
		var card = head_.getNext();
		for(int i = 1; i < deckSize_; i++) {
			
			if(card.getCard().isJoker()) {
				if(firstJoker == null) {
					firstJoker = card;
					firstRight = card.getPrev();
				}
				else {
					secondJoker = card;
					secondLeft = card.getNext();
					
					// found both jokers
					break;
				}
			}
			card = card.getNext();
		}
		
		firstLeft.setPrev(secondJoker);
		secondJoker.setNext(firstLeft);
		
		firstRight.setNext(secondLeft);
		secondLeft.setPrev(firstRight);
		
		secondRight.setNext(firstJoker);
		firstJoker.setPrev(secondRight);
		
		head_ = secondLeft;
	}
	
	public void countCut(int n) {
		
		if (n < 1) { return; }
		
		// remove bottom card from the deck
		var poppedCard = head_.getPrev().getCard();
		head_.setPrev(head_.getPrev().getPrev());
		
		// Count down a specified number of cards from the top of the deck
		var card = head_;
		for(int i = 1; i <= n; i++) {
			card = card.getNext();
		}
		
		var countedLeft = head_;
		var countedRight = card;
		var otherLeft = card.getNext();
		var otherRight = head_.getPrev();
		
		otherLeft.setPrev(countedRight);
		countedRight.setNext(otherLeft);
		
		otherRight.setNext(countedLeft);
		countedLeft.setPrev(otherRight);
		
		head_ = otherLeft;
		
		// add poppedCard to bottom of the deck

		var pushed = new DoubleListNode(poppedCard, head_.getPrev(), head_);
		head_.setPrev(pushed);
	}
	
	
	public SolitaireCard getBottomCard() {
		return head_.getPrev().getCard();
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
	
	
	private boolean checkStructure() {
		var c = head_;
		for(int a = 1; a <= deckSize_+2; a++) {
			
			if( c.getPrev().getNext() != c) {
				return false;
			}
			if( c.getNext().getPrev() != c) {
				return false;
			}
			
			c = c.getNext();
			
		}
		return true;
	}
	
	private boolean checkContents() {
		
		boolean[] bArr = new boolean[deckSize_+1];
		
		var c = head_;
		for(int a = 1; a <= deckSize_+2; a++) {
			
			if(c.getCard().isJokerA()) {
				
				if(bArr[0]) {
					// Joker A already Exists
					return false;
				}
				
				bArr[0] = true;
				
			}
			else if(c.getCard().isJokerB()) {
				if(bArr[bArr.length-1]) {
					// Joker B already Exists
					return false;
				}
				bArr[bArr.length-1] = true;
			}
			
			int cardVal = c.getCard().getValue();
			if(bArr[cardVal]) {
				// Card with such value already Exists
				return false;
			}
			bArr[cardVal] = true;
			
			c = c.getNext();
			
		} // for
		
		return true;
	}
	
}
