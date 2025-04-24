import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main program for rabbit hunt. It allows the user to step the simulation one
 * step at a time or to let it run.
 */

public class RabbitHunt extends Application {

	private static final int NUMROWS = 20, NUMCOLS = 20; // grid size
	private static final int CELLSIZE = 25; // size of grid cells, in pixels
	private static final int DELAY = 15; // number of frames between sim steps
	                                     // (smaller value = faster)

	private RabbitHuntSim sim_; // the simulation
	private boolean anim_; // is the animation currently running?
	private int delay_; // number of frames between animation steps

	/**
	 * Draw one frame of an animation.
	 * 
	 * @param g
	 *          the graphics context
	 * @param w
	 *          width of the drawing area
	 * @param h
	 *          height of the drawing area
	 */

	public void drawFrame ( GraphicsContext g, double w, double h ) {
		g.clearRect(0,0,w,h); // clear window background

		// draw the frame
		sim_.draw(g,CELLSIZE);

		// is animation paused?
		if ( !anim_ ) {
			return;
		}

		// speed control - only do a simulation step every DELAY frames
		delay_--;
		if ( delay_ == 0 ) {
			delay_ = DELAY;
		} else {
			return;
		}

		// step or reset the animation for another round
		if ( sim_.isOver() ) {
			if ( sim_.isRabbitFree() ) {
				System.out.println("rabbit wins!");
			} else {
				System.out.println("fox wins!");
			}
			sim_.reset();
		} else {
			sim_.step();
		}
	}

	/**
	 * Handle a key press.
	 * 
	 * @param key
	 *          the key pressed
	 */

	public void handleKey ( KeyCode key ) {

		if ( key == KeyCode.R ) { // reset
			sim_.reset();

		} else if ( key == KeyCode.G ) { // start animation
			anim_ = true;

		} else if ( key == KeyCode.P ) { // pause animation
			anim_ = false;

		} else if ( key == KeyCode.S ) { // step animation
			anim_ = false;
			sim_.step();
		}
	}

	public static void main ( String[] args ) {
		launch(args);
	}

	public void start ( Stage stage ) {
		stage.setTitle("rabbit hunt");

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);

		Canvas canvas = new Canvas(NUMCOLS * CELLSIZE,NUMROWS * CELLSIZE);
		canvas.setFocusTraversable(true);
		root.setCenter(canvas);

		stage.show();

		sim_ = new RabbitHuntSim(NUMROWS,NUMCOLS);
		anim_ = false;
		delay_ = DELAY;

		AnimationTimer anim = new AnimationTimer() {
			private long start_ = -1, prev_;

			public void handle ( long now ) {
				if ( start_ < 0 ) {
					start_ = prev_ = now;
					drawFrame(canvas.getGraphicsContext2D(),canvas.getWidth(),
					          canvas.getHeight());
				} else if ( now - prev_ > 0.95e9 / 60 ) {
					drawFrame(canvas.getGraphicsContext2D(),canvas.getWidth(),
					          canvas.getHeight());
					prev_ = now;
				}
			}
		};
		anim.start();

		canvas.setOnKeyPressed(e -> {
			handleKey(e.getCode());
		});
	}
}