import javafx.scene.paint.*;
import javafx.scene.canvas.*;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
interface Thing {

	public Color getColor();
	
	public void draw(GraphicsContext g, int x, int y, int width, int height);

}
