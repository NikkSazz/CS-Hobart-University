import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Omino! main program. <br>
 * Program controls: 'n' to start a new game, 'p' to pause play, 'q' to quit.
 * <br>
 * Game controls: 'j'/'l' to move left/right, 'k' to rotate', spacebar to drop
 * piece.
 * 
 * @author Nikolai Sazonov
 */
public class Omino extends Application implements PropertyChangeListener {

	// size of each block on the screen, in pixels
	public static final int BLOCK_SIZE = 25;

	// delay between piece movements, in ms (smaller values = faster speed of
	// game)
	public static final long DELAY = 200;

	// the game state
	private Game game_;

	/**
	 * Handle a user key press.
	 * 
	 * @param ch
	 *          character typed
	 */
	private void handleKey ( char ch ) {
		if ( ch == 'j' ) { // left
			// TODO move piece left
			game_.movePiece(Action.LEFT);

		} else if ( ch == 'l' ) { // right
			// TODO move piece right
			game_.movePiece(Action.RIGHT);

		} else if ( ch == ' ' ) { // drop
			// TODO move piece drop
			game_.movePiece(Action.DROP);

		} else if ( ch == 'k' ) { // rotate
			// TODO move piece rotate
			game_.movePiece(Action.ROTATE);

		} else if ( ch == 'p' ) { // pause
			if ( timer_ == null ) {
				startTimer();
			} else {
				stopTimer();
			}

		} else if ( ch == 'q' ) { // quit
			Platform.exit();

		} else if ( ch == 'n' ) { // new game
			// TODO reset and start game
			game_.reset();
			game_.start();
			startTimer();
		}
	}

	class MoveTask extends TimerTask {
		@Override
		public void run () {
			/* TODO move piece down, stopping the timer 
			 if moving the piece results in
			 the game being over
			 */

			game_.movePiece(Action.DOWN);

			if(game_.isGameOver()) {
				stopTimer();
			}

		}
	}

	/**
	 * Draw the contents of the game board.
	 * 
	 * @param g
	 *          the GraphicsContext to use for drawing
	 */
	private void drawBoard ( GraphicsContext g ) {
		// TODO draw the contents of the game board
		
		Board board = game_.board; // this may not work, code says to get board from game
			  for ( int row = board.getHeight() - 1, y = 0 ; row >= 0 ; row--, y += BLOCK_SIZE ) {
			    for ( int col = 0, x = 0 ; col < board.getWidth() ; col++, x += BLOCK_SIZE ) {
//			      if ( board.canPlace(row, col) ) {
			    	if(board.colorAt(row,col) != null) {
			        g.setFill(board.colorAt(row,col));
			        g.fillRect(x,y,BLOCK_SIZE,BLOCK_SIZE);
			        g.setStroke(Color.BLACK);
			        g.strokeRect(x,y,BLOCK_SIZE,BLOCK_SIZE);
			      }
			    }
			  }
		
	}

	/**
	 * Draw the current piece.
	 * 
	 * @param g
	 *          the GraphicsContext to use for drawing
	 */
	public void drawCurrentPiece ( GraphicsContext g ) {
		// TODO draw the current piece
		Piece piece = game_.getCurrPiece();
		if ( piece == null ) {
			return;
		}

		int row = game_.getCurrPieceRow();
		int col = game_.getCurrPieceCol();
		
		int boardHeight = game_.getBoardHeight();
		
		for ( Block b : piece.getBody() ) {

			int r = row + b.getRow(), c = col + b.getColumn();
			g.setFill(piece.getColor());
			g.fillRect(c * BLOCK_SIZE,
			           (boardHeight - r - 1) * BLOCK_SIZE,
			           BLOCK_SIZE,BLOCK_SIZE);
			g.setStroke(Color.BLACK);
			g.strokeRect(c * BLOCK_SIZE,
			             (boardHeight - r - 1) * BLOCK_SIZE,
			             BLOCK_SIZE,BLOCK_SIZE);
		}

	}

	/**
	 * Get the current score.
	 * 
	 * @return the current score
	 */
	private int getScore () {
		// TODO return the current score
		return game_.getScore();
	}

	/**
	 * Get the number of rows cleared.
	 * 
	 * @return the number of rows cleared
	 */
	private int getNumRowsCleared () {
		// TODO return the number of rows cleared
		return game_.getRowsCleared();
	}

	/**
	 * Get the number of pieces played.
	 * 
	 * @return the number of pieces played
	 */
	private int getNumPiecesPlayed () {
		// TODO return the number of pieces played
		return game_.getNumberOfPiecesPlayed();
	}

	// ---------- no changes needed beyond this point --------------------------

	// UI elements
	private Timer timer_ = null; // timer for piece movements
	private Canvas boardcanvas_, piececanvas_; // for board and current piece
	private Label score_, numrows_, numpieces_; // display of game stats

	public static void main ( String[] args ) {
		launch(args);
	}

	@Override
	public void start ( Stage stage ) throws Exception {
		stage.setTitle("Omino!");

		game_ = new Game();
		game_.addPropertyChangeListener(this);

		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: black");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		// keep the window from being resized by the user
		stage.setResizable(false);

		// controls
		boardcanvas_ = new Canvas(game_.getBoardWidth() * BLOCK_SIZE,
		                          game_.getBoardHeight() * BLOCK_SIZE);
		piececanvas_ = new Canvas(game_.getBoardWidth() * BLOCK_SIZE,
		                          game_.getBoardHeight() * BLOCK_SIZE);
		boardcanvas_.widthProperty().bind(piececanvas_.widthProperty());
		boardcanvas_.heightProperty().bind(piececanvas_.heightProperty());
		StackPane stack = new StackPane(boardcanvas_,piececanvas_);
		stack
		.setStyle("-fx-border-style: solid; -fx-border-color: white; -fx-border-width: 2");
		root.setCenter(stack);
		BorderPane.setMargin(stack,new Insets(3,3,3,3));

		GridPane panel = new GridPane();
		panel.add(setupLabel(new Label("score")),0,0);
		score_ = setupLabel(new Label("     0"));
		panel.add(score_,1,0);
		panel.add(setupLabel(new Label("rows")),0,1);
		numrows_ = setupLabel(new Label("     0"));
		panel.add(numrows_,1,1);
		panel.add(setupLabel(new Label("pieces")),0,2);
		numpieces_ = setupLabel(new Label("     0"));
		panel.add(numpieces_,1,2);
		root.setRight(panel);
		BorderPane.setMargin(panel,new Insets(20,20,40,20));

		// make the canvas be able to get the focus
		piececanvas_.setFocusTraversable(true);
		// set up key listener
		root.setOnKeyTyped(e -> handleKey(e.getCharacter().toLowerCase()
		                                  .charAt(0)));

		stage.show();
	}

	@Override
	public void stop () {
		stopTimer();
	}

	/**
	 * Start the game play timer.
	 */
	private void startTimer () {
		if ( timer_ == null ) {
			timer_ = new Timer();
			timer_.schedule(new MoveTask(),0,DELAY);
		}
	}

	/**
	 * Stop the game play timer.
	 */
	private void stopTimer () {
		if ( timer_ != null ) {
			timer_.cancel();
			timer_ = null;
		}
	}

	/**
	 * Configure labels in the game display.
	 * 
	 * @param label
	 *          label to configure
	 * @return the label
	 */
	private Label setupLabel ( Label label ) {
		label
		.setStyle("-fx-font: normal 20 sans-serif; -fx-padding: 5; -fx-text-fill: white");
		return label;
	}

	@Override
	public void propertyChange ( PropertyChangeEvent e ) {
		if ( e.getPropertyName().equals(OminoSubject.BOARD_PROPERTY) ) {
			GraphicsContext g = boardcanvas_.getGraphicsContext2D();
			g.clearRect(0,0,boardcanvas_.getWidth(),boardcanvas_.getHeight());
			drawBoard(g);
		} else if ( e.getPropertyName().equals(OminoSubject.CURPIECE_PROPERTY) ) {
			GraphicsContext g = piececanvas_.getGraphicsContext2D();
			g.clearRect(0,0,piececanvas_.getWidth(),piececanvas_.getHeight());
			drawCurrentPiece(g);
		} else if ( e.getPropertyName().equals(OminoSubject.SCORE_PROPERTY) ) {
			score_.setText(String.format("%5s","" + getScore()));
		} else if ( e.getPropertyName().equals(OminoSubject.NUMROWS_PROPERTY) ) {
			numrows_.setText(String.format("%5s","" + getNumRowsCleared()));
		} else if ( e.getPropertyName().equals(OminoSubject.NUMPIECES_PROPERTY) ) {
			numpieces_.setText(String.format("%5s","" + getNumPiecesPlayed()));
		}
	}
}