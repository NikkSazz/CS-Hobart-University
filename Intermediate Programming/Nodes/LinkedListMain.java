package cs225Project;

import java.util.ArrayList;

public class LinkedListMain {

	/**
	 * Print the contents of a linked list with a newline at the end.
	 * 
	 * @param head
	 *          the head of the list
	 */
	public static void println ( ListNode head ) {
		for ( ListNode runner = head ; runner != null ; runner =
		    runner.getNext() ) {
			System.out.print(runner.getElt() + " ");
		}
		System.out.println();
	}

	/**
	 * Insert a new node containing the specified element at the head of the list.
	 * 
	 * @param head
	 *          the head of the list
	 * @param elt
	 *          element to insert
	 * @return the new head of the list
	 */
	public static ListNode insertAtHead ( ListNode head, int elt ) {
		return new ListNode(elt,head);
	}

	/**
	 * Retrieve the node in the specified position. (The first node is at position
	 * 0.)
	 * 
	 * @param head
	 *          the head of the list (head != null)
	 * @param pos
	 *          position of the node to retrieve (pos >= 0 and pos < length of
	 *          list)
	 * @return the node in the specified position
	 */
	public static ListNode getNth ( ListNode head, int pos ) {
		if ( head == null ) {
			throw new IllegalArgumentException("got empty list!");
		}
		if ( pos < 0 ) {
			throw new IllegalArgumentException("need pos >= 0; got " + pos);
		}

		// advance runner to correct position or the end of the list is found
		ListNode runner = head;
		for ( int count = 0 ; count < pos && runner != null ; count++ ) {
			runner = runner.getNext();
		}
		// reached end of the list before pos - invalid position
		if ( runner == null ) {
			throw new IllegalArgumentException("pos too big; got " + pos);
		}
		return runner;
	}

	/**
	 * Insert the specified element after the specified node.
	 * 
	 * @param node
	 *          node to insert after
	 * @param elt
	 *          element to insert
	 */
	public static void insertAfter ( ListNode node, int elt ) {
		ListNode inserted = new ListNode(elt, node.getNext());
		node.setNext(inserted);
	}

	/**
	 * Insert the specified element before the specified node.
	 * 
	 * @param head
	 *          head of the list (head != null)
	 * @param node
	 *          node to insert before (node != null)
	 * @param elt
	 *          element to insert
	 * @return head of the resulting list
	 */
	public static ListNode insertBefore ( ListNode head, ListNode node,
	                                      int elt ) {
		if ( head == null ) {
			throw new IllegalArgumentException("got empty list!");
		}
		if ( node == null ) {
			throw new IllegalArgumentException("node can't be null");
		}
		
		// insert at the head
		if ( node == head ) {
			return new ListNode(elt,head);
		}
		
		// insert elsewhere
		ListNode before, after = node;

		for ( before = head ; before.getNext() != node ; before =
		    before.getNext() ) {}

		ListNode newnode = new ListNode(elt,after);
		before.setNext(newnode);
		return head;
	}

	/**
	 * Split the list into two lists, alternating nodes. For example, if the list
	 * contains 1 2 3 4 5 6 7, the resulting lists will contain 1 3 5 7 and 2 4 6.
	 * 
	 * @param head
	 *          head of the list
	 * @return the heads of the two new lists
	 */
	public static ListNode[] splitAlternating ( ListNode head ) {
		
		// get each element starting from the end to 0th 
		var intarr = new ArrayList<Integer>();
		while( head != null ) {
			intarr.add(head.getElt());
			//System.out.println("Added " + head.getElt());
			head = head.getNext();
		}
		
		// init tail of nodes
		ListNode[] list = new ListNode[2];
		list[0] = new ListNode(intarr.get(0));
		list[1] = new ListNode(intarr.get(1));
		
		// make a new node, keeping the old nodes head as the tail
		// alternate [] every other index 
		for(int i = 2; i < intarr.size(); i++) {
			list[i % 2] = new ListNode(intarr.get(i), list[i % 2]);
		}
		
		return list;
	}

	/**
	 * Remove and return the tail node of the list.
	 * 
	 * @param head
	 *          head of the list (list must have at least two nodes)
	 * @return the node removed (the former tail)
	 */
	public static ListNode removeTail ( ListNode head ) {
		// TODO implement!
		return null;
	}

	/**
	 * Move the first n nodes of the list to the end.
	 * 
	 * @param head
	 *          head of the list
	 * @param nfalse
	 *          number of nodes to move (0 <= n < length of list)
	 * @return new head of the list
	 */
	public static ListNode moveToEnd ( ListNode head, int n ) {
		// TODO implement!
		return null;
	}

	/**
	 * Insert the specified element at the specified position.
	 * 
	 * @param head
	 *          head of the list
	 * @param pos
	 *          the insertion position
	 * @param elt
	 *          the element to insert
	 */
	public static void insertAtIndex ( ListNode head, int pos, int elt ) {
		for(int i = 0; i < pos; i++) {
			head = head.getNext();
		}
		ListNode insert = new ListNode(elt, head.getNext());
		head.setNext(insert);
	}

	public static void main ( String[] args ) {

		// create a linked list with elements 1, 2, 3, 4
		ListNode head = new ListNode(1);
		ListNode tail = head; // tail points to the last node
		tail.setNext(new ListNode(2));
		tail = tail.getNext();
		tail.setNext(new ListNode(3));
		tail = tail.getNext();
		tail.setNext(new ListNode(4));
		tail = tail.getNext();
		tail.setNext(new ListNode(5));
		tail = tail.getNext();
		tail.setNext(new ListNode(6));
		tail = tail.getNext();
		tail.setNext(new ListNode(7));
		tail = tail.getNext();

		/*
		println(head);
		println(tail);

		head = insertAtHead(head,5);
		println(head);

		System.out.println("pos 0: " + getNth(head,0).getElt());
		System.out.println("pos 2: " + getNth(head,2).getElt());
		System.out.println("pos 4: " + getNth(head,4).getElt());

		head = insertBefore(head,getNth(head,2),6);
		println(head);
		head = insertBefore(head,head,7);
		println(head);
		*/
		
		System.out.println("Splitting");
		println(head);
		var split = splitAlternating(head);
		for(var i : split) {
			System.out.println("Split at index");
			println(i);
		}
	}

}