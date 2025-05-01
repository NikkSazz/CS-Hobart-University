import javafx.scene.paint.*;
import javafx.scene.canvas.*;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class Bush implements Thing {

	public Bush ( int row, int col ) {
		// TODO Auto-generated constructor stub
	}
	
	public Bush ( ) {
		// TODO Auto-generated constructor stub
	}

	public Color getColor () {
		return Color.GREEN;
	}

	public void draw ( GraphicsContext g, int x, int y, int width, int height ) {
		g.setFill(getColor());
		g.fillRect(x,y,width,height);
	}

	public int getNextMove ( Field field ) {
		return 0;
	}

}
