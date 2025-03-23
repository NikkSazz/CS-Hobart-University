
/**
 * A set of strings.
 * 
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class StringSet {

	private String[] elements_; // the elements in the set, in lexicographic order
	                            // and without duplicates
	private int size_; // the number of elements in the set (0 <= size_ <=
	                   // elements_.length)

	/**
	 * Create an empty set.
	 */
	public StringSet () {
		this(5);
	}

	/**
	 * Create an empty set with the specified initial capacity.
	 */
	public StringSet ( int capacity ) {
		elements_ = new String[capacity];
		size_ = 0;
	}

	/**
	 * Create a set containing the specified elements. The elements must be in
	 * lexicographic order and contain no duplicates.
	 * 
	 * @param elements
	 *          elements, in lexicographic order and without duplicates
	 */
	StringSet ( String[] elements ) {
		this(elements,elements.length);
	}

	/**
	 * Create a set containing the first n of the specified elements. The elements
	 * must be in lexicographic order and contain no duplicates.
	 * 
	 * @param elements
	 *          elements, in lexicographic order and without duplicates
	 * @param n
	 *          number of elements to include
	 */
	private StringSet ( String[] elements, int n ) {
		size_ = n;
		elements_ = new String[n];
		for ( int i = 0 ; i < n ; i++ ) {
			elements_[i] = elements[i];
		}

		assert 0 <= size_ && size_ <= elements_.length;
		assert isOrderedAndUnique(elements_,size_);
	}

	/**
	 * Return a string representation of the set. Elements are listed in
	 * lexicographic order and are separated by commas e.g. { cat, dog, platypus,
	 * zebra }.
	 * 
	 * @return string representation of the set
	 */
	public String toString () {
		String str = "{ ";
		for ( int i = 0 ; i < size_ ; i++ ) {
			str += elements_[i];
			if ( i < size_ - 1 ) {
				str += ", ";
			}
		}
		str += " }";
		return str;
	}

	/**
	 * Two sets are equal if they contain the same elements.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals ( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		StringSet other = (StringSet) obj;
		if ( size_ != other.size_ ) {
			return false;
		}
		for ( int i = 0 ; i < size_ ; i++ ) {
			if ( elements_[i] == other.elements_[i] ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get the size of the set.
	 * 
	 * @return the number of elements in the set
	 */
	public int size () {
		return size_;
	}

	/**
	 * Add the specified element to the set. Does nothing if the element is
	 * already present.
	 * 
	 * @param elt
	 *          element to insert
	 */
	public void add ( String elt ) {
		
		if(find(elt) >= 0) {
			System.out.println("elements already contain 'elt'!");
			return; // breaks, as the set already contains element
		}
		
		var atCapacity = size() == elements_.length;
		System.out.println("At Capacity: " + atCapacity);
		if(atCapacity) {
			increaseArrSize();
		}
		
		for(int i = 0; i < size() + 1; i++) {
			if(i == size() + 1) {
				// lexicographic order final element
				elements_[i] = elt;
				break;
			}
			
			if(elt.compareTo(elements_[i]) < 0) {
				
				//insert elt at i
				
				for(int j = size(); j > i && j - 1 < 0; j--) {
					elements_[j] = elements_[j - 1];
				}
				elements_[i] = elt;
				size_++;
				break;
			}
			
		}
		
		
		assert 0 <= size_ && size_ <= elements_.length;
		assert isOrderedAndUnique(elements_,size_);
		assert contains(elt);
	}
	
	// doubles the size of the array, while keeping all values
	private void increaseArrSize() {
		var oldSize = elements_.length;
		var newArr = new String[oldSize * 2];
		
		for(int i = 0; i < oldSize; i++) {
			newArr[i] = elements_[i];
		}
		System.out.println("Elements Length increased from " + elements_.length + " to " + newArr.length);
		elements_ = newArr;
	}

	/**
	 * Remove the specified element from the set. Does nothing if the element is
	 * not present.
	 * 
	 * @param elt
	 *          element to remove
	 */
	public void remove ( String elt ) {
		int pos = find(elt); // element position

		// can't leave a gap, so shift elements that come after the one being
		// removed to close the gap
		for ( int i = pos ; i < size_ ; i++ ) {
			elements_[i] = elements_[i + 1];
		}

		// remove the element
		size_--;

		assert 0 <= size_ && size_ <= elements_.length;
		assert isOrderedAndUnique(elements_,size_);
		assert !contains(elt);
	}

	/**
	 * Determine if an element is in the set.
	 * 
	 * @param elt
	 *          element to find
	 * @return true if 'elt' is in the set, false otherwise
	 */
	public boolean contains ( String elt ) {
		int pos = find(elt);
		return pos != -1;
	}

	/**
	 * Return a new set containing the union of this set and 'other'.
	 * 
	 * @param other
	 *          other set in the union
	 * @return a new set containing the union of this set and 'other'
	 */
	public StringSet union ( StringSet other ) {
		// TODO: implement this! (optional)
		return new StringSet(elements_,size_);
	}

	/**
	 * Return a new set containing the intersection of this set and 'other'.
	 * 
	 * @param other
	 *          other set in the intersection
	 * @return a new set containing the intersection of this set and 'other'
	 */
	public StringSet intersection ( StringSet other ) {
		String[] combo = new String[Math.min(size_,other.size_)];

		int i = 0;
		for ( int i1 = 0, i2 = 0 ; i1 < size_ || i2 < other.size_ ; i++ ) {
			int order = elements_[i1].compareTo(other.elements_[i2]);
			if ( order == 0 ) {
				combo[i] = elements_[i1];
				i1++;
				i2++;
			} else if ( order < 0 ) {
				i1++;
			} else {
				i2++;
			}
		}

		return new StringSet(combo,i);
	}

	/**
	 * Find the position of the specified element, or -1 if it is not present.
	 * 
	 * @param elt
	 *          element
	 * @return position of elt or -1 if not present
	 */
	private int find ( String elt ) {
		int low = 0, high = size_; // current range is
		                           // elements_[low]..elements_[high-1]
		for ( ; low < high ; ) {
			int mid = (low + high) / 2;
			int order = elt.compareTo(elements_[mid]);
			if ( order == 0 ) {
				return mid;
			} else if ( order < 0 ) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	/**
	 * Check if specified array is in lexicographic order and contains no
	 * duplicates - returns true if so and false otherwise.
	 * 
	 * @param elements
	 *          array to check
	 * @param size
	 *          number of elements to check
	 * @return true if elements[0..size-1] are in lexicographic order and contain
	 *         no duplicates, false otherwise
	 */
	private boolean isOrderedAndUnique ( String[] elements, int size ) {
		for ( int i = 0 ; i < size - 1 ; i++ ) {
			if ( elements[i].compareTo(elements[i + 1]) >= 0 ) {
				// elements are the same, or i should come after i+1
				return false;
			}
		}
		return true;
	}

	/**
	 * Print the contents of the specified array.
	 * 
	 * @param elements
	 *          array to print
	 * @param size
	 *          number of elements to print
	 */
	private void print ( String[] elements, int size ) {
		String str = "{ ";
		for ( int i = 0 ; i < size_ ; i++ ) {
			str += elements_[i];
			if ( i < size_ - 1 ) {
				str += ", ";
			}
		}
		str += " }";
		System.out.println(str);
	}

}
