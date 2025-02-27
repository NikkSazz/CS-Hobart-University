
/**
 * 
 *  Block encapsulates the position of a polyomino block.
 *  
 * @author Nikolai Sazonov
 */
public class Block {

	// Row
	int row;
	
	// Column
	int column;
	
	// Constructor
	public Block(int r, int c) {
		this.row = r;
		this.column = c;
	}
	
	
	// returns row
	public int getRow() {
		return row;
	}

	// returns column
	public int getColumn() {
		return column;
	}
}
