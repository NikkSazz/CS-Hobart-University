/**
 * A notion of the eight directions things can move in.
 */

public class Direction {

	// The allowed directions.

	public static final int NONE = -1, NORTH = 0, NE = 1, EAST = 2, SE = 3,
	    SOUTH = 4, SW = 5, WEST = 6, NW = 7;

	/**
	 * The smallest possible direction value.
	 */
	public static final int MIN_DIRECTION = 0;

	/**
	 * The largest possible direction value.
	 */
	public static final int MAX_DIRECTION = 7;

	/**
	 * The number of direction values.
	 */
	public static final int NUM_DIRECTIONS = (MAX_DIRECTION - MIN_DIRECTION + 1);

	/**
	 * Return a direction-value which is the specified turn from the specified
	 * direction value. The turn amount is the number of directions to turn - e.g.
	 * turn(Direction.NORTH,2) will return Direction.EAST. A negative amount means
	 * to turn counter-clockwise, a positive amount turns clockwise.
	 * 
	 * @param dir
	 *          the direction (MIN_DIRECTION <= dir <= MAX_DIRECTION)
	 * @param amount
	 *          the number of directions to turn from the current direction
	 *          (positive = clockwise, negative = counter-clockwise)
	 * @return the new direction
	 */

	public static int turn ( int dir, int amount ) {
		if ( dir == NONE ) {
			return NONE;
		} else {
			int newdir = dir + amount;
			while ( newdir < MIN_DIRECTION ) {
				newdir += NUM_DIRECTIONS;
			}

			return (newdir - MIN_DIRECTION) % NUM_DIRECTIONS + MIN_DIRECTION;
		}
	}

	/**
	 * Return a random direction-value.
	 * 
	 * @return random direction value
	 */

	public static int random () {
		return (int) (Math.random() * NUM_DIRECTIONS) + MIN_DIRECTION;
	}

}
