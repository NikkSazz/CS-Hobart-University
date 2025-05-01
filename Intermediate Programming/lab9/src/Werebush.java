import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class Werebush extends Creature {

	Animal transformAnimal_;
	private int transformInterval_;
	private int transformDuration_;
	
	public Werebush() {
		transformInterval_ = 20;
		transformDuration_ = 5;
	}

	public Werebush (Animal animal) {
		transformAnimal_ = animal;
	}
	public Color getColor () {
		// TODO Auto-generated method stub
		return null;
	}

	public void draw ( GraphicsContext g, int x, int y, int width, int height ) {
		// TODO Auto-generated method stub
		
	}

	public int getNextMove ( Field field ) {
		if(transformInterval_ > 0) {
			transformInterval_--;
			return Direction.NONE;
		}
		
		if(transformDuration_ > 0) {
			transformDuration_--;
			return transformAnimal_.getNextMove(field);
		}
		
		reset();
		return Direction.NONE;
	}

	public void reset () {
		
		transformInterval_ = 20;
		transformDuration_ = 5;
		transformAnimal_.reset();
		
	}

}
