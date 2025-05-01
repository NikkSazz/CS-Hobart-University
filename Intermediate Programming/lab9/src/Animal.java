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
	
	public void draw ( GraphicsContext g, int x, int y, int width, int height ) {
		g.setFill(getColor());
		g.fillOval(x,y,width,height);
	}
	
	public void setPosition(int row, int col) {
		setRow(row);
		setCol(col);
	}
	
	public abstract int getNextMove(Field field);
	
	public abstract void reset();
	
}
