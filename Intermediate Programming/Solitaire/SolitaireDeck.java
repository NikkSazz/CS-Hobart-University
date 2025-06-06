// package solitaire;

import java.util.ArrayList;

/**
 * Solitaire deck is used to get specialized integer values,
 * with a complex method of cycling cards. those integer values
 * are used to modify a string into a completely different set of alphabet
 * 
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
		// System.out.println("DeckSize = " + deckSize);
		deckSize_ = deckSize;
		
		// if(deckSize <= 2) { throw new Exception(""); }
		
		head_ = new DoubleListNode(new SolitaireCard(1));
		DoubleListNode curr = head_;
		DoubleListNode newNode;

		// i = 2 because 1 is already init with head_;
		for (int i = 1; i < deckSize; i++) {
			
			newNode = new DoubleListNode(new SolitaireCard(i + 1));
			
			curr.setNext(newNode);
			newNode.setPrev(curr);
			curr = newNode;
		
		}

		// System.out.println(this.toString());
		
		// Jokers aswell
	    DoubleListNode jokerA = new DoubleListNode(new SolitaireCard(deckSize + 1, 'A'));
	    curr.setNext(jokerA);
	    jokerA.setPrev(curr);
	    curr = jokerA;

	    DoubleListNode jokerB = new DoubleListNode(new SolitaireCard(deckSize + 1, 'B'));
	    curr.setNext(jokerB);
	    jokerB.setPrev(curr);
	    curr = jokerB;
		

		curr.setNext(head_);
		head_.setPrev(curr);
		

		// System.out.println(this.toString());
		
		assert checkStructure();
		// System.out.println(this.toString());
		assert checkContents() : "Constructor Contents are wrong";
		System.out.println("Initial Testing has passed");
	}
	
	/*
	 * Private method for testing
	 * String str = "1 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 27A 26 8 3 24 6 4";
	 */
	SolitaireDeck(int deckSize, String deck) {
		deckSize_ = deckSize;
		
		String[] cards = deck.split(" +");
		
		if(cards.length < 1) { 
			throw new IllegalArgumentException("String deck parameter improper format");
		}
		
		head_ = new DoubleListNode(new SolitaireCard(cards[0]));
		
		DoubleListNode curr = head_;
		DoubleListNode newNode;
		
		// i = 2 because 1 is already init with head_;
		for(int i = 2; i < cards.length; i++) {
			newNode = new DoubleListNode(new SolitaireCard(cards[i]));
			curr.setNext(newNode);
			newNode.setPrev(curr);
			curr = newNode;
		}
		
		// Finish circle
		curr.setNext(head_);
		head_.setPrev(curr);

		assert checkStructure();
		assert checkContents();
		System.out.println("Initial Tests have passed");
	}
	
	/*
	 * example
	 * "1 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 27A 26 8 3 24 6 4"
	 */
	public String toString() {

		assert checkStructure() : "Structure is wrong";
		assert checkContents();
		
		ArrayList<String> list = new ArrayList<>();

		var curr = head_;
		list.add(curr.getCard().toString());
		curr = curr.getNext();
		
		while(curr != head_) {
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

		assert checkStructure();
		assert checkContents();
	}
	
	public void swapJokerB() {
		
		DoubleListNode curr = head_;
		System.out.println("JokerB() While Start");
        while (!curr.getCard().isJokerB()) {
        	// System.out.print("\t" + curr.getCard().getValue());
            curr = curr.getNext();
        }
		System.out.println("JokerB() While End");
        DoubleListNode after = curr.getNext();
        DoubleListNode second = after.getNext();
        
        if(head_ == curr) {
        	// System.out.println("***JokerB is head***");
        	head_ = after;
        }
        
        
        // pop joker
        curr.getPrev().setNext(after);
        after.setPrev(curr.getPrev());

        // Put back joker in new spot
        DoubleListNode newNext = second.getNext();
        second.setNext(curr);
        curr.setPrev(second);
        curr.setNext(newNext);
        newNext.setPrev(curr);
		
		assert checkStructure();
		assert checkContents();
		
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
		
		DoubleListNode first = head_;
        while (!first.getCard().isJoker()) {
            first = first.getNext();
        }

        DoubleListNode second = first.getNext();
        while (!second.getCard().isJoker()) {
            second = second.getNext();
        }

        if (first == head_ || second.getNext() == head_) {
            head_ = second.getNext();
        } else {
            DoubleListNode beforeFirst = first.getPrev();
            DoubleListNode afterSecond = second.getNext();

            beforeFirst.setNext(afterSecond);
            afterSecond.setPrev(beforeFirst);

            DoubleListNode tail = head_.getPrev();
            tail.setNext(first);
            first.setPrev(tail);

            second.setNext(head_);
            head_.setPrev(second);

            head_ = afterSecond;
        }

		assert checkStructure();
		assert checkContents();
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
		pushed.setNext(head_);
		head_.setPrev(pushed);

		assert checkStructure();
		assert checkContents();
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
		
		int count = 0;
		
		while (count <= deckSize_ + 2) {
			
			// something is broken
			if (c == null || c.getPrev() == null || c.getNext() == null) {
	            return false; // Found a broken link
	        }

			
//			try {
//				if( c.getPrev().getNext() != c) {
//					return false;
//				}
//				if( c.getNext().getPrev() != c) {
//					return false;
//				}
//			} catch (NullPointerException e) {
//				return false;
//			}
//	        c = c.getNext();
			
			if (c.getPrev().getNext() != c || c.getNext().getPrev() != c) {
	            return false;
	        }
			
	        c = c.getNext();
	        count++;		
	        
	        if (c == head_) { // fully looped
	            break;
	        }
	        
		}
		return count == deckSize_ + 2; // visited all possible numbers, plus two jokers??
	}

	private boolean checkContents() {
		
		boolean[] bArr = new boolean[deckSize_+2];
		
		var c = head_;
		
		for(int a = 1; a <= deckSize_+2; a++) {
			
			if(c.getCard().isJokerA()) {
				
				if(bArr[0]) {
					// Joker A already Exists
					return false;
				}
				
				bArr[0] = true;
				continue;
			}
			else if(c.getCard().isJokerB()) {
				
				if(bArr[bArr.length-1]) {
					// Joker B already Exists
					return false;
				}
				bArr[bArr.length-1] = true;
				continue;
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