
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A maze.
 * 
 * @author ssb
 */
public class Maze {

	/**
	 * The maze itself. grid_[row][col] is true if there is a wall, false if the
	 * square is open.
	 */
	private boolean[][] grid_;

	/**
	 * Dimensions of the maze.
	 */
	private int width_, height_;

	/**
	 * Start and goal positions in the maze.
	 */
	private MazePos start_, goal_;

	/**
	 * Create a new maze.
	 * 
	 * @param grid
	 *          the maze grid (grid[row][col] is true if there is a wall, false if
	 *          the square is open)
	 * @param start
	 *          the starting position in the maze
	 * @param goal
	 *          the goal position in the maze
	 * @throws IllegalArgumentException
	 *           if any of the parameters is null or the grid has a dimension of 0
	 */
	public Maze ( boolean[][] grid, MazePos start, MazePos goal ) {
		if ( grid == null ) {
			throw new IllegalArgumentException("grid cannot be null");
		}
		if ( start == null ) {
			throw new IllegalArgumentException("start position cannot be null");
		}
		if ( goal == null ) {
			throw new IllegalArgumentException("goal position cannot be null");
		}
		if ( grid.length == 0 || grid[0].length == 0 ) {
			throw new IllegalArgumentException("the maze grid must have dimensions > 0");
		}

		start_ = start;
		goal_ = goal;
		width_ = grid[0].length;
		height_ = grid.length;
		grid_ = new boolean[height_][width_];
		for ( int row = 0 ; row < height_ ; row++ ) {
			for ( int col = 0 ; col < width_ ; col++ ) {
				grid_[row][col] = grid[row][col];
			}
		}
	}

	/**
	 * Create a new maze, loading it from the specified file. The file should
	 * contain two integers > 0 (the width and height of the maze), followed by
	 * two MazePos (start and goal), then width*height characters which are '.'
	 * (open cell) or 'x' (wall) arranged in row-major order.
	 * 
	 * @param filename
	 *          file to load from
	 * @exception ParseException
	 *              if the file is not in the right format
	 * @throws FileNotFoundException
	 */

	public Maze ( String filename ) throws ParseException, FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));

		int width, height;
		width = scanner.nextInt();
		if ( width <= 0 ) {
			throw new ParseException("illegal width: " + width);
		}
		height = scanner.nextInt();
		if ( height <= 0 ) {
			throw new ParseException("illegal height: " + height);
		}

		boolean[][] grid = new boolean[height][width];
		scanner.useDelimiter("[\\s(,)]+");
		int startrow = scanner.nextInt();
		int startcol = scanner.nextInt();
		MazePos start = new MazePos(startrow,startcol);

		int goalrow = scanner.nextInt();
		int goalcol = scanner.nextInt();
		MazePos goal = new MazePos(goalrow,goalcol);

		scanner.reset();
		scanner.nextLine();
		for ( int row = 0 ; row < height ; row++ ) {
			String line = scanner.nextLine();
			for ( int col = 0 ; col < width ; col++ ) {
				char ch = line.charAt(col);
				if ( ch == '.' ) {
					grid[row][col] = false;
				} else if ( ch == 'x' ) {
					grid[row][col] = true;
				} else {
					throw new ParseException("illegal symbol " + ch + " in maze");
				}
			}
		}
		scanner.close();

		grid_ = grid;
		width_ = width;
		height_ = height;
		start_ = start;
		goal_ = goal;
	}

	/**
	 * Get the goal position in the maze.
	 * 
	 * @return the goal
	 */
	public MazePos getGoal () {
		return goal_;
	}

	/**
	 * Get the height of the maze (in rows).
	 * 
	 * @return the height of the maze
	 */
	public int getHeight () {
		return height_;
	}

	/**
	 * Get the starting position of the maze.
	 * 
	 * @return the start
	 */
	public MazePos getStart () {
		return start_;
	}

	/**
	 * Get the width of the maze (in columns).
	 * 
	 * @return the width of the maze
	 */
	public int getWidth () {
		return width_;
	}

	/**
	 * Return true if (row,col) is a wall.
	 * 
	 * @param row
	 *          the row of the cell to check
	 * @param col
	 *          the column of the cell to check
	 * @return true if (row,col) is a wall, false otherwise
	 * @exception IllegalArgumentException
	 *              if row < 0 or row >= getHeight() or col < 0 or col >=
	 *              getWidth()
	 */
	public boolean isWall ( int row, int col ) {
		if ( row < 0 || row >= height_ ) {
			throw new IllegalArgumentException("invalid row: " + row);
		}
		if ( col < 0 || col >= width_ ) {
			throw new IllegalArgumentException("invalid column: " + col);
		}

		return grid_[row][col];
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString () {
		StringBuffer buffer = new StringBuffer();
		buffer.append(width_ + " " + height_ + " " + start_ + " " + goal_ + "\n");
		for ( int row = 0 ; row < height_ ; row++ ) {
			for ( int col = 0 ; col < width_ ; col++ ) {
				buffer.append((isWall(row,col) ? "x" : "."));
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/**
	 * Determine if a square is the starting position.
	 * 
	 * @param row
	 *          0 <= row < height of maze
	 * @param col
	 *          0 <= col < width of maze
	 * @return true if the specified position is the start position, false
	 *         otherwise
	 */
	public boolean isStart ( int row, int col ) {
		if ( row < 0 || row >= height_ ) {
			throw new IllegalArgumentException("invalid row: " + row);
		}
		if ( col < 0 || col >= width_ ) {
			throw new IllegalArgumentException("invalid column: " + col);
		}

		return start_.getRow() == row && start_.getCol() == col;
	}

	/**
	 * Determine if a square is the goal.
	 * 
	 * @param row
	 *          0 <= row < height of maze
	 * @param col
	 *          0 <= col < width of maze
	 * @return true if the specified position is the goal position, false
	 *         otherwise
	 */
	public boolean isGoal ( int row, int col ) {
		if ( row < 0 || row >= height_ ) {
			throw new IllegalArgumentException("invalid row: " + row);
		}
		if ( col < 0 || col >= width_ ) {
			throw new IllegalArgumentException("invalid column: " + col);
		}

		return goal_.getRow() == row && goal_.getCol() == col;
	}

}
