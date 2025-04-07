import java.util.*;

/**
 * Solve a maze using a stack to hold the discovered rooms. (depth-first search)
 * 
 * @author Nikolai Sazonov
 */
public class SolverStack extends SolverSubject implements Solver {

	private Maze maze_; // maze to solve

	Stack<MazePos> discoveredRooms_;
	
	Set<MazePos> exploredRooms_;

	// TODO [#2] declare the "discovered from" map (HashMap) - the key is a room
	// (MazePos) and the value is the room (MazePos) that the key room was
	// discovered from

	private boolean solved_; // true if the goal has been found, false if solving
	                         // is in progress or maze is unsolvable

	/**
	 * Create a new maze solver for the specified maze.
	 * 
	 * @param maze
	 *          maze to solve
	 */
	public SolverStack ( Maze maze ) {
		maze_ = maze;

		solved_ = false;

		discoveredRooms_ = new Stack<MazePos>();

		exploredRooms_ = new HashSet<MazePos>();

		// TODO [#2] initialize the "discovered from" map - initially empty

		discoveredRooms_.push(maze.getStart());

		// TODO [#2] add to the "discovered from" map: the maze start is
		// discovered from nowhere (null) - the key is the maze start, the value is
		// null
	}

	/**
	 * Is the solver done? (i.e. the maze has been solved or it has been
	 * determined to be unsolvable)
	 * 
	 * @return true if the solver is done, false otherwise
	 */
	@Override
	public boolean isDone () {
		// TODO [#1] the solver is done if solved_ is true (meaning the goal has
		// been found) or the discovered rooms collection is empty - add the "or the
		// discovered rooms collection is empty" part to the condition below
		return solved_ || discoveredRooms_.isEmpty();
	}

	/**
	 * Find the next unexplored (but discovered) room.
	 * 
	 * @return the next unexplored (but discovered) room, or null if there aren't
	 *         any more discovered rooms
	 */
	private MazePos getNextUnexplored () {
		// go until an unexplored room is found or we run out of rooms
		for ( ; true ; ) {
			if(discoveredRooms_.isEmpty()) { 
				return null; 
			}

			MazePos poped = discoveredRooms_.pop();

			if(!exploredRooms_.contains(poped)) { 
				return poped; 
			}

		}
	}

	/**
	 * Perform one step of solving the maze. (i.e. processes one discovered room)
	 * Requires that there be more solving to do, that is, the maze has not yet
	 * been solved or determined to be unsolvable.
	 */
	@Override
	public void step () {
		if ( isDone() ) {
			throw new IllegalArgumentException("maze has already been solved or is unsolvable");
		}

		// get the next discovered room (and remove it from that collection)
		MazePos curroom = getNextUnexplored();
		if ( curroom == null ) {
			return;
		}
		int currow = curroom.getRow(), curcol = curroom.getCol();

		exploredRooms_.add(curroom);

		if ( maze_.isGoal(currow,curcol) ) {
			solved_ = true;
			return;
		}

		for ( MazePos neighbor : curroom.getNeighbors() ) {
			if ( isMazeRoom(neighbor) ) {
				if ( !exploredRooms_.contains(neighbor) && !discoveredRooms_.contains(neighbor) ) {
					discoveredRooms_.add(neighbor);
				}
			}
		}

		// TODO [#2] ...and also add that the neighbor is discovered from curroom to
		// the "discovered from" map

		firePropertyChange(SOLVER_PROPERTY);
	}

	/**
	 * Get the solution path, from the start to the goal. Requires that the solver
	 * be done.
	 * 
	 * @return solution path (the list is empty if the maze is unsolvable)
	 */
	@Override
	public List<MazePos> getPath () {
		if ( !isDone() ) {
			throw new IllegalArgumentException("solver isn't done!");
		}

		// TODO [#2] create an empty Stack (holding MazePos) to hold the rooms found
		// on the path

		// find the rooms on the solution path from the "discovered from"
		// information
		for ( MazePos current = maze_.getGoal() ; current != null ;
		// TODO [#2] update current to be the room current was discovered from
		) {
			// TODO [#2] add current to the rooms on the path
		}

		// TODO [#2] create an empty List (holding MazePos) to hold the path

		// TODO [#2] one-by-one, remove all of the rooms from the stack and add them
		// to the path list

		// TODO [#2] return the path
		return null;
	}

	/**
	 * Get the number of rooms that have been explored so far.
	 * 
	 * @param numbers
	 *          of rooms explored so far
	 */
	@Override
	public int getNumExplored () {
		exploredRooms_.size();
		return -1;
	}

	/**
	 * Get the number of rooms that have been discovered so far. This counts
	 * everything that has been discovered as part of the solving process, even if
	 * the room has subsequently been explored.
	 * 
	 * @param numbers
	 *          of rooms discovered so far
	 */
	@Override
	public int getNumDiscovered () {
		return discoveredRooms_.size() + exploredRooms_.size();
	}

	/**
	 * Get whether the specified room has been explored.
	 * 
	 * @param pos
	 *          room to check (room must be within the bounds of the maze and not
	 *          a wall)
	 * @return true if the room has been explored, false if not
	 */
	@Override
	public boolean isExplored ( MazePos pos ) {
		if ( !isMazeRoom(pos) ) {
			throw new IllegalArgumentException("position " + pos
			    + " is outside the maze or is a wall");
		}

		return exploredRooms_.contains(pos);
	}

	/**
	 * Get whether the specified room has been discovered but not yet explored.
	 * 
	 * @param pos
	 *          room to check (room must be within the bounds of the maze and not
	 *          a wall)
	 * @return true if the room has been discovered (but not yet explored), false
	 *         if not
	 */
	@Override
	public boolean isDiscovered ( MazePos pos ) {
		if ( !isMazeRoom(pos) ) {
			throw new IllegalArgumentException("position " + pos
			    + " is outside the maze or is a wall");
		}

		return discoveredRooms_.contains(pos);
	}

	/**
	 * Get the maze this solver is solving.
	 * 
	 * @return the maze
	 */
	@Override
	public Maze getMaze () {
		return maze_;
	}

	/**
	 * Determine if the specified position is a valid room in the maze i.e. it is
	 * within the maze boundaries and isn't a wall.
	 * 
	 * @param pos
	 *          position
	 * @return true if pos is a valid room (within the maze boundaries and not a
	 *         wall)
	 */
	private boolean isMazeRoom ( MazePos pos ) {
		int row = pos.getRow(), col = pos.getCol();
		return (row >= 0 && row < maze_.getHeight() && col >= 0
		    && col < maze_.getWidth() && !maze_.isWall(row,col));
	}
}
