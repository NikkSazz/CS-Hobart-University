import javafx.scene.paint.*;
import javafx.scene.canvas.*;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class Rabbit extends Animal {

	public Rabbit ( int row, int col ) {
		super(row, col);
	}


	/**
	 * 
	 */
	public Rabbit () {
		// TODO Auto-generated constructor stub
		super();
	}


	public Color getColor () {
		return Color.rgb(165, 125, 0);
	}


	public void draw ( GraphicsContext g, int x, int y, int width, int height ) {
		g.setFill(getColor());
		g.fillOval(x,y,width,height);
	}


	public int getNextMove ( Field field ) {
		return Direction.random();
	}

	public void reset() {
		// Nothing to reset
	}

}
