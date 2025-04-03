import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Defines the interface for a maze solver.
 * 
 * @author ssb
 */
public interface Solver {

	/**
	 * Is the solver done? (i.e. the maze has been solved or it has been
	 * determined to be unsolvable)
	 * 
	 * @return true if the solver is done, false otherwise
	 */
	public boolean isDone ();

	/**
	 * Perform one step of solving the maze. (i.e. processes one discovered room)
	 * Requires that there be more solving to do, that is, the maze has not yet
	 * been solved or determined to be unsolvable.
	 */
	public void step ();

	/**
	 * Get the solution path, from the start to the goal. Requires that the solver
	 * be done.
	 * 
	 * @return solution path (the list is empty if the maze is unsolvable)
	 */
	public List<MazePos> getPath ();

	/**
	 * Get the number of rooms that have been explored so far.
	 * 
	 * @param numbers
	 *          of rooms explored so far
	 */
	public int getNumExplored ();

	/**
	 * Get the number of rooms that have been discovered so far. This counts
	 * everything that has been discovered as part of the solving process, even if
	 * the room has subsequently been explored.
	 * 
	 * @param numbers
	 *          of rooms discovered so far
	 */
	public int getNumDiscovered ();

	/**
	 * Get whether the specified room has been explored.
	 * 
	 * @param pos
	 *          room to check (room must be within the bounds of the maze and not
	 *          a wall)
	 * @return true if the room has been explored, false if not
	 */
	public boolean isExplored ( MazePos pos );

	/**
	 * Get whether the specified room has been discovered but not yet explored.
	 * 
	 * @param pos
	 *          room to check (room must be within the bounds of the maze and not
	 *          a wall)
	 * @return true if the room has been discovered (but not yet explored), false
	 *         if not
	 */
	public boolean isDiscovered ( MazePos pos );

	/**
	 * Get the maze this solver is solving.
	 * 
	 * @return the maze
	 */
	public Maze getMaze ();

	public void addPropertyChangeListener ( PropertyChangeListener listener );

}