/**
 * A binary tree node with an element.
 */
public class TreeNode<T> {

	private T element_;
	private TreeNode<T> left_, right_;
	private TreeNode<T> parent_;

	/**
	 * Create a tree node with the specified element. Parent and children are
	 * null.
	 * 
	 * @param element
	 *          the element
	 */
	public TreeNode ( T element ) {
		this(element,null,null,null);
	}

	/**
	 * Create a tree node with the specified element and children. Parent is null.
	 * 
	 * @param element
	 *          the element
	 * @param left
	 *          left child
	 * @param right
	 *          right child
	 */
	public TreeNode ( T element, TreeNode<T> left, TreeNode<T> right ) {
		this(element,left,right,null);
	}

	/**
	 * Create a tree node with the specified element and parent. The children are
	 * null.
	 * 
	 * @param element
	 *          the element
	 * @param parent
	 *          the parent
	 */
	public TreeNode ( T element, TreeNode<T> parent ) {
		this(element,null,null,parent);
	}

	/**
	 * Create a tree node with the specified values.
	 * 
	 * @param element
	 *          the element
	 * @param left
	 *          left child
	 * @param right
	 *          right child
	 * @param parent
	 *          parent
	 */
	public TreeNode ( T element, TreeNode<T> left, TreeNode<T> right,
	                  TreeNode<T> parent ) {
		super();
		element_ = element;
		left_ = left;
		right_ = right;
		parent_ = parent;
	}

	/**
	 * Create a dummy leaf storing no element.
	 * 
	 * @param parent
	 *          the leaf's parent
	 */
	public TreeNode ( TreeNode<T> parent ) {
		this(null,null,null,parent);
	}

	/**
	 * Get the element.
	 * 
	 * @return the element
	 */

	public T getElement () {
		return element_;
	}

	/**
	 * Set the element.
	 * 
	 * @param element
	 *          the element
	 */
	public void setElement ( T element ) {
		element_ = element;
	}

	/**
	 * Get the left child.
	 * 
	 * @return the left child
	 */

	public TreeNode<T> getLeft () {
		return left_;
	}

	/**
	 * Set the left child.
	 * 
	 * @param left
	 *          the left child
	 */

	public void setLeft ( TreeNode<T> left ) {
		left_ = left;
	}

	/**
	 * Get the right child.
	 * 
	 * @return the right child
	 */

	public TreeNode<T> getRight () {
		return right_;
	}

	/**
	 * Set the right child.
	 * 
	 * @param right
	 *          the right child
	 */

	public void setRight ( TreeNode<T> right ) {
		right_ = right;
	}

	/**
	 * Get the parent.
	 * 
	 * @return the parent
	 */

	public TreeNode<T> getParent () {
		return parent_;
	}

	/**
	 * Set the parent.
	 * 
	 * @param parent
	 *          the parent
	 */
	public void setParent ( TreeNode<T> parent ) {
		parent_ = parent;
	}

}
