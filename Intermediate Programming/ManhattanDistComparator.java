
import java.util.Comparator;

/**
 * Compare two maze positions based on the Manhattan distance between the maze
 * position and the goal.
 * 
 * @author Stina Bridgeman
 */
public class ManhattanDistComparator implements Comparator<MazePos> {

	/**
	 * The goal position.
	 */
	private MazePos goal_;

	/**
	 * Create a new comparator to compare positions to the specified goal.
	 * 
	 * @param goal
	 */
	public ManhattanDistComparator ( MazePos goal ) {
		goal_ = goal;
	}

	/**
	 * Compute the Manhattan distance between two positions.
	 * 
	 * @param pos1
	 * @param pos2
	 * @return the Manhattan distance between the specified positions
	 */
	private int distance ( MazePos pos1, MazePos pos2 ) {
		return Math.abs(pos1.getRow() - pos2.getRow())
		    + Math.abs(pos1.getCol() - pos2.getCol());
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare ( MazePos pos1, MazePos pos2 ) {
		return distance(pos1,goal_) - distance(pos2,goal_);
	}
}
