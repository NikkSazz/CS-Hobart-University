import javafx.scene.paint.*;
import javafx.scene.canvas.*;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class Bush implements Thing{


	public Color getColor () {
		return Color.GREEN;
	}

	public void draw ( GraphicsContext g, int x, int y, int width, int height ) {
		g.setFill(getColor());
		g.fillRect(x,y,width,height);
	}

}
