import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A field, which contains things and roaming creatures. The field is
 * represented as a grid, with each thing occupying exactly one grid cell and
 * each grid cell occupied by at most one thing at a time.
 */

public class Field {

	private int numrows_, numcols_; // dimensions of the field (rows and columns)

	// the things in the field; field_[row][col] is null if the grid cell is empty
	private Thing[][] field_;

	/**
	 * Create a new field with the specified dimensions.
	 * 
	 * @param numrows
	 *          number of rows (0 &lt; numrows)
	 * @param numcols
	 *          number of columns (0 &lt; numcols)
	 */

	public Field ( int numrows, int numcols ) {
		numrows_ = numrows;
		numcols_ = numcols;
		field_ = new Thing[numrows][numcols];
		clearField();
	}

	/**
	 * Return the thing at the specified row and column in the field. Returns null
	 * if the spot is empty.
	 * 
	 * @param row
	 *          row of thing to return (0 &lt;= row &lt; height())
	 * @param col
	 *          column of thing to return (0 &lt;= col &lt; width())
	 * @return thing at the specified row and column in the field
	 */

	public Thing at ( int row, int col ) {
		return field_[row][col];
	}

	/**
	 * Determine if the specified creature can move one spot in the specified
	 * direction. Returns true if so, false if the creature is blocked by another
	 * thing or would move outside the bounds of the field.
	 * 
	 * @param creature
	 *          the creature whose movement is to be checked (creature != null and
	 *          the creature's position is in the field)
	 * @param direction
	 *          the direction to check (Direction.MIN_DIRECTION &lt;= direction
	 *          &lt;= Direction.MAX_DIRECTION or direction == Direction.NONE)
	 * @return true if the creature can move in the specified direction, false
	 *         otherwise
	 */

	public boolean canMove ( Creature creature, int direction ) {
		int row = creature.getRow() + rowChange(direction);
		int col = creature.getColumn() + colChange(direction);

		return (0 <= row && 0 <= col && row < numrows_ && col < numcols_
		    && field_[row][col] == null);
	}

	/**
	 * Clear the field so that it is empty except for the hedge of bushes around
	 * the outside edge.
	 */

	public void clearField () {
		/* clear the field */
		for ( int row = 0 ; row < getNumRows() ; row++ ) {
			for ( int col = 0 ; col < getNumCols() ; col++ ) {
				if ( row == 0 || col == 0 || row == getNumRows() - 1
				    || col == getNumCols() - 1 ) {
					field_[row][col] = new Bush();
				} else {
					field_[row][col] = null;
				}
			}
		}
	}

	/**
	 * Determine the distance between the specified creature and the next thing,
	 * looking in the specified direction. The distance is 0 if direction is
	 * Direction.NONE. Returns the distance to the edge of the field if nothing
	 * blocks the creature's view.
	 * 
	 * @param creature
	 *          the creature who is looking (creature != null and the creature's
	 *          position is in the field)
	 * @param direction
	 *          the direction to look in (Direction.MIN_DIRECTION &lt;= direction
	 *          &lt;= Direction.MAX_DIRECTION or direction == Direction.NONE)
	 * @return the distance between the specified creature and the next thing in
	 *         the specified direction, or 0 if the direction is Direction.NONE
	 */

	public int distance ( Creature creature, int direction ) {

		if ( direction == Direction.NONE ) {
			return 0;
		}

		int drow = rowChange(direction), dcol = colChange(direction);

		int distance = 1;
		try {

			for ( int row = creature.getRow() + drow,
			    col = creature.getColumn() + dcol ; at(row,col) == null ; row +=
			        drow, col += dcol, distance++ ) {}

		} catch ( ArrayIndexOutOfBoundsException e ) {}

		return distance;
	}

	/**
	 * Returns the number of columns in the field.
	 * 
	 * @return the number of columns in the field
	 */

	public int getNumCols () {
		return numcols_;
	}

	/**
	 * Returns the number of rows in the field.
	 * 
	 * @return the number of rows in the field
	 */

	public int getNumRows () {
		return numrows_;
	}

	/**
	 * Return the thing seen by the specified creature, looking in the specified
	 * direction. Returns null if the direction is Direction.NONE or nothing is
	 * visible before the edge of the field is reached.
	 * 
	 * @param creature
	 *          the creature whose is looking (creature != null and the creature's
	 *          position is in the field)
	 * @param direction
	 *          the direction to look in (Direction.MIN_DIRECTION &lt;= direction
	 *          &lt;= Direction.MAX_DIRECTION or direction == Direction.NONE)
	 * @return the closest thing to the specified creature in the specified
	 *         direction, or null if the direction is Direction.NONE or nothing is
	 *         visible in the specified direction
	 */

	public Thing look ( Creature creature, int direction ) {

		if ( direction == Direction.NONE ) {
			return null;
		}

		int drow = rowChange(direction), dcol = colChange(direction);

		try {

			int row, col;
			for ( row = creature.getRow() + drow, col = creature.getColumn()
			    + dcol ; at(row,col) == null ; row += drow, col += dcol ) {}

			return at(row,col);

		} catch ( ArrayIndexOutOfBoundsException e ) {
			return null;
		}
	}

	/**
	 * Move the specified creature one space in the specified direction. If there
	 * is something already in that cell, it is overwritten.
	 * 
	 * @param creature
	 *          the creature to move (creature != null and the creature's position
	 *          is in the field)
	 * @param direction
	 *          the direction to move (canMove(creature,direction) is true, or it
	 *          is desired to overwrite the thing in the neighboring cell and the
	 *          neighboring cell is within the confines of the field)
	 */

	public void move ( Creature creature, int direction ) {

		int row = creature.getRow() + rowChange(direction);
		int col = creature.getColumn() + colChange(direction);

		field_[creature.getRow()][creature.getColumn()] = null;
		field_[row][col] = creature;
		creature.setPosition(row,col);
	}

	/**
	 * Return the thing in the cell which is a neighbor of the specified creature
	 * and is in the specified direction. Returns null if the direction is
	 * Direction.NONE, the cell is empty, or the cell is outside the bounds of the
	 * field.
	 * 
	 * @param creature
	 *          the creature whose neighbor is being found (creature != null and
	 *          the creature's position is in the field)
	 * @param direction
	 *          the direction to look in (Direction.MIN_DIRECTION &lt;= direction
	 *          &lt;= Direction.MAX_DIRECTION or direction == Direction.NONE and
	 *          the creature is in the field)
	 * @return the thing in the adjacent cell in the specified direction, or null
	 *         if the direction is Direction.NONE, the neighboring cell is empty,
	 *         or the creature is not in the field
	 */

	public Thing neighbor ( Creature creature, int direction ) {

		if ( direction == Direction.NONE ) {
			return null;
		}

		int drow = rowChange(direction), dcol = colChange(direction);
		try {

			return at(creature.getRow() + drow,creature.getColumn() + dcol);

		} catch ( ArrayIndexOutOfBoundsException e ) {
			return null;
		}
	}

	/**
	 * Draw the field, including everything in it.
	 * 
	 * @param g
	 *          graphics context
	 * @param cellsize
	 *          size for one grid cell
	 */

	public void draw ( GraphicsContext g, int cellsize ) {
		g.setFill(Color.WHITE);
		g.fillRect(0,0,numcols_ * cellsize,numrows_ * cellsize);
		g.setStroke(Color.LIGHTGRAY);
		g.strokeRect(0,0,numcols_ * cellsize - 1,numrows_ * cellsize - 1);

		for ( int row = 0 ; row < numrows_ ; row++ ) {
			for ( int col = 0 ; col < numcols_ ; col++ ) {
				int x = col * cellsize;
				int y = row * cellsize;

				Thing thing = at(row,col);
				if ( thing != null ) {
					thing.draw(g,x,y,cellsize,cellsize);
				}
				g.setStroke(Color.LIGHTGRAY);
				g.strokeRect(x,y,cellsize,cellsize);
			}
		}

	}

	/**
	 * Place the specified thing at a random open spot in the field. For
	 * creatures, the creature's position is updated to reflect where the creature
	 * was placed.
	 * 
	 * @param thing
	 *          the thing to place
	 */

	public void placeRandomly ( Thing thing ) {
		for ( ; true ; ) {
			int row = (int) (Math.random() * numrows_);
			int col = (int) (Math.random() * numcols_);
			if ( field_[row][col] == null ) {
				field_[row][col] = thing;
				if ( thing instanceof Creature ) {
					((Creature) thing).setPosition(row,col);
				}
				break;
			}
		}
	}

	/**
	 * Place the specified thing at the specified spot in the field. If there is
	 * already something there, it will be replaced. For creatures, the creature's
	 * position is updated to reflect where the creature was placed.
	 * 
	 * @param thing
	 *          the thing to place
	 * @param row
	 *          row in the field
	 * @param col
	 *          col in the field
	 */

	public void place ( Thing thing, int row, int col ) {
		field_[row][col] = thing;
		if ( thing instanceof Creature ) {
			((Creature) thing).setPosition(row,col);
		}
	}

	/**
	 * Determine the change in row number if moving in the specified direction.
	 * 
	 * @param direction
	 *          the direction being moved
	 * @return the change in row number resulting from the specified direction (-1
	 *         &lt;= returned value &lt;= 1)
	 */

	private int rowChange ( int direction ) {
		switch ( direction ) {

		case Direction.NORTH:
		case Direction.NE:
		case Direction.NW:
			return -1;

		case Direction.SOUTH:
		case Direction.SE:
		case Direction.SW:
			return 1;

		default:
			return 0;
		}
	}

	/**
	 * Determine the change in column number if moving in the specified direction.
	 * 
	 * @param direction
	 *          the direction being moved
	 * @return the change in column number resulting from the specified direction
	 *         (-1 &lt;= returned value &lt;= 1)
	 */

	private int colChange ( int direction ) {
		switch ( direction ) {

		case Direction.WEST:
		case Direction.SW:
		case Direction.NW:
			return -1;

		case Direction.EAST:
		case Direction.SE:
		case Direction.NE:
			return 1;

		default:
			return 0;
		}
	}

}
