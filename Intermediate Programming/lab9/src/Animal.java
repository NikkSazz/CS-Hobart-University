import javafx.scene.paint.*;
import javafx.scene.canvas.*;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
abstract class Animal extends Creature {
	
	public Animal(int row, int col) {
		setPosition(row, col);
	}
	
	public Animal() {
		setRow(-1);
		setCol(-1);
	}
	
	public void setPosition(int row, int col) {
		setRow(row);
		setCol(col);
	}
	
	public abstract int getNextMove(Field field);
	
	public abstract void reset();
	
}
