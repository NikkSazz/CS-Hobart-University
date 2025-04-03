import java.util.Arrays;

/**
 * A single maze position - row and column.
 * 
 * @author ssb
 */
public class MazePos {

	/**
	 * The maze position.
	 */
	private int row_, col_;

	/**
	 * Create a maze position with row -1 and col -1.
	 */
	public MazePos () {
		row_ = -1;
		col_ = -1;
	}

	/**
	 * Create a maze position with the specified values.
	 * 
	 * @param row
	 * @param col
	 */
	public MazePos ( int row, int col ) {
		row_ = row;
		col_ = col;
	}

	/**
	 * Get the column part of this maze position.
	 * 
	 * @return the col
	 */
	public int getCol () {
		return col_;
	}

	/**
	 * Get the row part of this maze position.
	 * 
	 * @return the row
	 */
	public int getRow () {
		return row_;
	}

	/**
	 * Get the four positions adjacent to this one. (up, down, left, right)
	 * 
	 * @return the four adjacent positions
	 */
	public Iterable<MazePos> getNeighbors () {
		MazePos[] neighbors =
		    { new MazePos(row_ - 1,col_), new MazePos(row_ + 1,col_),
		      new MazePos(row_,col_ - 1), new MazePos(row_,col_ + 1) };
		return Arrays.asList(neighbors);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString () {
		return "(" + row_ + "," + col_ + ")";
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + col_;
		result = prime * result + row_;
		return result;
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		MazePos other = (MazePos) obj;
		if ( col_ != other.col_ ) return false;
		if ( row_ != other.row_ ) return false;
		return true;
	}

}
