import javafx.scene.paint.*;
import javafx.scene.canvas.*;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
abstract class Animal implements Thing{
	private int row_, col_;
	
	public Animal(int row, int col) {
		setPosition(row, col);
	}
	
	public Animal() {
		row_ = -1;
		col_ = -1;
	}
	
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
