import javafx.scene.paint.*;
import java.util.Random;
import javafx.scene.canvas.*;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class Sloth extends Animal {

	private int sleepCounter_;
	
	public Sloth() {
		Random r = new Random();
		sleepCounter_ = r.nextInt(20);
	}
	
	public Color getColor () {
		return Color.GRAY;
	}

	public void draw ( GraphicsContext g, int x, int y, int width, int height ) {
		
		g.setFill(getColor());
		g.fillOval(x,y,width,height);
		
		if(sleepCounter_ > 0) {
			g.fillText("Zz",width,height);
		}
		
	}

	public int getNextMove ( Field field ) {
		
		if(sleepCounter_ == 0) {
			sleepCounter_ = 20;
			return Direction.random();
		}
		
		sleepCounter_--;
		return Direction.NONE;
		
	}
	
	public void reset() {
		Random r = new Random();
		sleepCounter_ = r.nextInt(20);
	}
	
}
