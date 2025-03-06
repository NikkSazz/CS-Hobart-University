/**
 * A node for a singly-linked list storing integer elements.
 * @author Nikolai Sazonov
 */
public class ListNode {

	private int elt_; // the element
	private ListNode next_; // the next node

	/**
	 * Create a new node with the specified element. The next node is null.
	 * 
	 * @param elt
	 *          element for the node to contain
	 */
	public ListNode ( int elt ) {
		super();
		elt_ = elt;
		next_ = null;
	}

	/**
	 * Create a new node with the specified element and next node.
	 * 
	 * @param elt
	 *          element for the node to contain
	 * @param next
	 *          next node in the list
	 */
	public ListNode ( int elt, ListNode next ) {
		super();
		elt_ = elt;
		next_ = next;
	}

	/**
	 * Retrieve the element stored in the node.
	 * 
	 * @return this node's element
	 */
	public int getElt () {
		return elt_;
	}

	/**
	 * Retrieve the next node in the list.
	 * 
	 * @return the next node
	 */
	public ListNode getNext () {
		return next_;
	}

	/**
	 * Set the next node.
	 * 
	 * @param next
	 *          the next node
	 */
	public void setNext ( ListNode next ) {
		next_ = next;
	}

}
