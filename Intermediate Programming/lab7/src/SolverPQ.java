import java.util.*;

/**
 * Solve a maze using a Priority Queue to hold the discovered rooms. (depth-first search)
 * 
 * @author Nikolai Sazonov
 */
public class SolverPQ extends SolverSubject implements Solver {

	private Maze maze_; // maze to solve

	PriorityQueue<MazePos> discoveredRooms_;
	
	Set<MazePos> exploredRooms_;

	HashMap<MazePos, MazePos> discoveredFrom_;

	private boolean solved_; // true if the goal has been found, false if solving
	                         // is in progress or maze is unsolvable

	/**
	 * Create a new maze solver for the specified maze.
	 * 
	 * @param maze
	 *          maze to solve
	 */
	public SolverPQ ( Maze maze ) {
		maze_ = maze;

		solved_ = false;

		discoveredRooms_ = new PriorityQueue<MazePos>();

		exploredRooms_ = new HashSet<MazePos>();

		discoveredFrom_ = new HashMap<MazePos, MazePos>();

		discoveredRooms_.add(maze.getStart());

		discoveredFrom_.put(maze.getStart(), null);

	}

	/**
	 * Is the solver done? (i.e. the maze has been solved or it has been
	 * determined to be unsolvable)
	 * 
	 * @return true if the solver is done, false otherwise
	 */
	@Override
	public boolean isDone () {
		return solved_ || discoveredRooms_.isEmpty();
	}

	/**
	 * Find the next unexplored (but discovered) room.
	 * 
	 * @return the next unexplored (but discovered) room, or null if there aren't
	 *         any more discovered rooms
	 */
	private MazePos getNextUnexplored() {
		while (!discoveredRooms_.isEmpty()) {
			MazePos room = discoveredRooms_.poll(); // remove first item
			if (!exploredRooms_.contains(room)) {
				return room;
			}
		}
		return null;
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

					discoveredFrom_.put(neighbor, curroom);
				}
			}
		}


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

		Stack<MazePos> roomsFoundOnPath = new Stack<>();

		// Backtrack from goal to start using discoveredFrom map
		for (MazePos current = maze_.getGoal(); current != null; current = discoveredFrom_.get(current)) {
			roomsFoundOnPath.add(current);
		}
	
		// Convert stack to list (from start to goal)
		List<MazePos> path = new ArrayList<>();
		
		while (!roomsFoundOnPath.isEmpty()) {
			path.add(roomsFoundOnPath.pop());
		}
	
		return path;
	}

	/**
	 * Get the number of rooms that have been explored so far.
	 * 
	 * @param numbers
	 *          of rooms explored so far
	 */
	@Override
	public int getNumExplored () {
		return exploredRooms_.size();
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