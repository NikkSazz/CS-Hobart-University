import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
public abstract class Creature implements Thing {

	private int row_, col_;
	
	public void setPosition(int row, int col) {
		row_ = row;
		col_ = col;
	}
	
	public abstract int getNextMove(Field field);
	
	public abstract void reset();
	
	/**
	 * @return the row
	 */
	public int getRow () {
		return row_;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow ( int row ) {
		row_ = row;
	}

	/**
	 * @return the col
	 */
	public int getCol () {
		return col_;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol ( int col ) {
		col_ = col;
	}
	
}
