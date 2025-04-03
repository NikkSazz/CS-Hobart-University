import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Maze solver main program.
 */
public class MazeSolverMain extends Application {

	// pixel dimensions of a grid cell on the screen
	private static final int CELLSIZE = 15;

	// pause (ms) between steps of the maze solver - a smaller value means the
	// solver runs faster
	private static final long DELAY = 25;

	public static void main ( String[] args ) {
		// gets the maze filename from commandline arguments if there are any,
		// otherwise prompts the user for the filename
		if ( args.length > 1 ) {
			System.out.println("usage: java " + Class.class.getName());
			System.out
			    .println("       java " + Class.class.getName() + " <maze filename>");
			System.exit(0);

		} else if ( args.length == 1 ) {
			launch(args);

		} else {
			Scanner scanner = new Scanner(System.in);

			System.out.print("enter maze filename:  ");
			String filename = scanner.nextLine();

			launch(new String[] { filename });
		}
	}

	@Override
	public void start ( Stage stage ) throws Exception {
		stage.setTitle("maze solver");

		// maze filename is passed via commandline arguments mechanism
		Parameters params = getParameters();
		String filename = params.getUnnamed().get(0);

		try {
			// load the maze
			Maze maze = new Maze(filename);

			// create the maze solvers
			// TODO uncomment the SolverQueue and SolverPQ constructor calls as those
			// classes are implemented
			Solver[] solvers = { new SolverStack(maze)
					// , new SolverQueue(maze)
					// , new SolverPQ(maze)
			};

			// create windows
			SolverStage[] stages = new SolverStage[solvers.length];
			for ( int i = 0 ; i < solvers.length ; i++ ) {
				stages[i] = new SolverStage(solvers[i]);
			}
		} catch ( FileNotFoundException e ) {
			System.out.println("cannot open file: " + e.getMessage());
			e.printStackTrace();
		} catch ( ParseException e ) {
			System.out.println("error parsing file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Handles the GUI for one solver. (creates a new window)
	 */
	class SolverStage implements PropertyChangeListener {
		private Solver solver_; // the solver
		private Stage stage_; // the window
		private Timer timer_; // timer, for maze solver animation
		private Canvas canvas_; // drawing canvas for display

		SolverStage ( Solver solver ) {
			solver_ = solver;
			solver_.addPropertyChangeListener(this);

			stage_ = new Stage();
			stage_.setTitle(solver.getClass().getName());

			BorderPane root = new BorderPane();
			root.setStyle("-fx-background-color: black");
			Scene scene = new Scene(root);
			stage_.setScene(scene);
			// keep the window from being resized by the user
			stage_.setResizable(false);

			Maze maze = solver.getMaze();
			canvas_ =
			    new Canvas(maze.getWidth() * CELLSIZE,maze.getHeight() * CELLSIZE);
			root.setCenter(canvas_);

			timer_ = new Timer();

			// make the canvas be able to get the focus
			canvas_.setFocusTraversable(true);
			// set up mouse click listener
			root.setOnMouseClicked(e -> {
				timer_.schedule(new SolveTask(solver,timer_,canvas_),0,DELAY);
				// remove listener after one click - can only start solver once
				root.setOnMouseClicked(e2 -> {});
			});

			draw(solver,canvas_);
			stage_.show();
		}

		class SolveTask extends TimerTask {
			private Solver solver_;
			private Timer timer_;
			private Canvas canvas_;

			SolveTask ( Solver solver, Timer timer, Canvas canvas ) {
				solver_ = solver;
				timer_ = timer;
				canvas_ = canvas;
			}

			@Override
			public void run () {
				solver_.step();
				if ( solver_.isDone() ) {
					draw(solver_,canvas_);
					System.out.println("solver " + solver_.getClass().getName() + " -- ");
					System.out.println("  number of rooms discovered: "
					    + solver_.getNumDiscovered());
					System.out.println("  number of rooms explored:   "
					    + solver_.getNumExplored());
					System.out.println("  solution path length:       "
					    + solver_.getPath().size());
					timer_.cancel();
				}
			}
		}

		@Override
		public void propertyChange ( PropertyChangeEvent e ) {
			draw(solver_,canvas_);
		}
	}

	private void draw ( Solver solver, Canvas canvas ) {
		GraphicsContext g = canvas.getGraphicsContext2D();
		g.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

		// draw the maze
		Maze maze = solver.getMaze();
		List<MazePos> path = (solver.isDone() ? solver.getPath() : null);

		for ( int row = 0 ; row < maze.getHeight() ; row++ ) {
			for ( int col = 0 ; col < maze.getWidth() ; col++ ) {
				MazePos pos = new MazePos(row,col);
				if ( maze.isWall(row,col) ) {
					g.setFill(Color.LIGHTGRAY);
				} else if ( maze.isStart(row,col) ) {
					g.setFill(Color.BLUE);
				} else if ( maze.isGoal(row,col) ) {
					g.setFill(Color.GREEN);
				} else if ( path != null && path.contains(pos) ) {
					g.setFill(Color.RED);
				} else if ( solver.isExplored(pos) ) {
					g.setFill(Color.ORANGE);
				} else if ( solver.isDiscovered(pos) ) {
					g.setFill(Color.YELLOW);
				} else {
					g.setFill(Color.BLACK);
				}
				g.fillRect(col * CELLSIZE,row * CELLSIZE,CELLSIZE,CELLSIZE);
			}
			g.setStroke(Color.DARKGRAY);
			g.strokeLine(0,row * CELLSIZE,maze.getWidth() * CELLSIZE,row * CELLSIZE);
		}
		g.setStroke(Color.DARKGRAY);
		g.strokeLine(0,maze.getHeight() * CELLSIZE,maze.getWidth() * CELLSIZE,
		             maze.getHeight() * CELLSIZE);

		g.setStroke(Color.DARKGRAY);
		for ( int col = 0 ; col < maze.getWidth() ; col++ ) {
			g.strokeLine(col * CELLSIZE,0,col * CELLSIZE,maze.getHeight() * CELLSIZE);
		}
	}
}
