import javafx.scene.canvas.GraphicsContext;

/**
 * The simulation keeps track of the field and simulation-related things, like
 * the current step.
 */

public abstract class Sim {

	protected Field field_; // the field
	protected int step_; // the number of simulation steps taken

	/**
	 * Create a new simulation with a field with the specified dimensions.
	 * 
	 * @param rows
	 *          rows in the field
	 * @param cols
	 *          columns in the field
	 */

	protected Sim ( int rows, int cols ) {
		field_ = new Field(rows,cols);
		step_ = 0;
	}

	/**
	 * Reset the simulation.
	 */

	public void reset () {
		field_.clearField();
		step_ = 0;
	}

	/**
	 * Return the current simulation step number.
	 * 
	 * @return current simulation step number
	 */

	public int getStep () {
		return step_;
	}

	/**
	 * Advance the simulation one step. (Does not include repainting.)
	 */

	public void step () {
		step_++;
	}

	/**
	 * Is the simulation over?
	 * 
	 * @return true if the simulation is over, false otherwise
	 */

	public boolean isOver () {
		return false;
	}

	/**
	 * Draw the current state of the simulation.
	 * 
	 * @param g
	 *          graphics context
	 * @param cellsize
	 *          size (in pixels) for one grid cell
	 */

	public void draw ( GraphicsContext g, int cellsize ) {
		field_.draw(g,cellsize);
	}
}