/**
 * @author Nikolai Sazonov
 * @author Cayden Hill
 */
public class Lab5Main {

	/**
	 * Build a linked list containing the elements of array, in that order.
	 * 
	 * @param array
	 *          the elements
	 * @return a linked list containing the elements in the same order
	 */
	public static ListNode buildList ( int[] array ) {
		// Modified code
		
		if(array.length <= 0) {
			return null;
		}
		
		 ListNode head = new ListNode(array[0]);
	   ListNode tail = head;
	    
	    for (int i = 1; i < array.length; i++) {
        ListNode newNode = new ListNode(array[i]);
        tail.setNext(newNode);
        tail = newNode;
	    }

    return head;
	}

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
	 * Return the position (index) of the first occurrence of elt in the list.
	 * Positions are 0-based, so the first node is at position 0.
	 * 
	 * @param head
	 *          the head of the list
	 * @param elt
	 *          the element to find
	 * @return the position of the first occurrence of elt, or -1 if elt does not
	 *         occur in the list
	 */
	public static int indexOf ( ListNode head, int elt ) {
		// Modified Code
		int index = 0;
		
		// precondition, if head is null at start, return -1;
		while(head != null) {
			if(head.getElt() == elt) {
				return index;
			}
			head = head.getNext();
			index++;
		}
		
		// Does not occur
		return -1;
	}

	/**
	 * Remove the span of nodes between the first and last occurrences of the
	 * specified element. The list is unchanged if the element only occurs once.
	 * For example, if the list contains 3 6 2 8 4 1 2 9 2 7 8 3 and elt is 2, the
	 * result should be 3 6 2 7 8 3 - everything after the first 2 up to and
	 * including the last 2 is removed. If the list contains 3 6 2 8 4 1 3 7 8 and
	 * elt is 2, the result should be 3 6 2 8 4 1 3 7 8 because 2 only occurs
	 * once.
	 * 
	 * @param head
	 *          the head of the list
	 * @param elt
	 *          element
	 * @return the new head of the list
	 */
	public static ListNode compact ( ListNode head, int elt ) {
		// Modified Code
		ListNode middle = null;
		ListNode newTail = null;
		
		for(ListNode checker = head; checker != null; checker = checker.getNext()) {
			
			if(checker.getElt() == elt) {
				if(middle == null) {
					middle = checker;
				}
				else {
					newTail = checker.getNext(); // including last *elt*
				}
				
			} // if elt == elt
		} // for
		
		if(middle != null && newTail != null) {
			middle.setNext(newTail);
			return head;
		}
		
		return head;
	}

	/**
	 * Insert the specified element at the specified position. Positions are
	 * 0-based, so the first node is at position 0.
	 * 
	 * @param head
	 *          head of the list
	 * @param pos
	 *          the insertion position
	 * @param elt
	 *          the element to insert
	 * @return head of the resulting list
	 */
	public static ListNode insertAtIndex ( ListNode head, int pos, int elt ) {
		// TODO implement!
		return null;
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
		// TODO implement!
		return null;
	}

	public static void main ( String[] args ) {

		// create a linked list with elements 10, 20, 30, 40, 50, 30, 60, 70
		ListNode head = setTestHead();
		
		
		// Testcase of indexOf();
		int indexOf10 = Lab5Main.indexOf(head,10);
		System.out.println("Index of 10: " + indexOf10);
		
		int indexOf40 = Lab5Main.indexOf(head,40);
		System.out.println("Index of 40: " + indexOf40);
		
		int indexOf100 = Lab5Main.indexOf(head,100);
		System.out.println("Index of 100: " + indexOf100);
		System.out.println("\n");
		
		println(head);
		
		// Testcase of compacted()
		ListNode compacted;
		System.out.println("\nCompacted at 30, twoElt:");
		compacted = Lab5Main.compact(head,30);
		println(compacted);
		
		head = setTestHead();
		System.out.println("\nCompacted at 20, oneElt:");
		compacted = Lab5Main.compact(head,20);
		println(compacted);
		
		head = setTestHead();
		System.out.println("\nCompacted at 100, zeroElt:");
		compacted = Lab5Main.compact(head,100);
		println(compacted);
		
		
		// Testcase of buildList()
		System.out.println("\nBuilt list of 1 2 3 4 5: ");
		int[] arr = { 1, 2, 3, 4, 5 };
		ListNode builtList = Lab5Main.buildList(arr);
		println(builtList);
		
	}

	private static ListNode setTestHead() {
	// create a linked list with elements 10, 20, 30, 40, 50, 30, 60, 70
			ListNode head = new ListNode(10);
			ListNode tail = head; // tail points to the last node
			tail.setNext(new ListNode(20));
			tail = tail.getNext();
			tail.setNext(new ListNode(30));
			tail = tail.getNext();
			tail.setNext(new ListNode(40));
			tail = tail.getNext();
			tail.setNext(new ListNode(50));
			tail = tail.getNext();
			tail.setNext(new ListNode(30));
			tail = tail.getNext();
			tail.setNext(new ListNode(60));
			tail = tail.getNext();
			tail.setNext(new ListNode(70));
			tail = tail.getNext();	
			return head;
	}
}
