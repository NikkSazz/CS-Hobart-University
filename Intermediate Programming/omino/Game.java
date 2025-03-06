import java.awt.Color;

/**
 * Game logic for Omino!
 * 
 * @author Nikolai Sazonov
 */
public class Game extends OminoSubject {

	/**
	 * Create a new Omino! game. The game must be started in order to play.
	 */
	
	private final int width = 10;
	
	private final int height = 25;
	
	private final int[] pointsFromCleared = { 1, 3, 5, 8 };
	
	 private static final String[][] POLYOMINOES =
	    { { "0 0  1 0  2 0", "0 0  0 1  0 2" },
	      { "0 0  1 0  0 1", "0 0  0 1  1 1", "1 0  0 1  1 1", "0 0  1 0  1 1" } };
	 
	 
	private final Color[] colors = 
		{ Color.cyan, Color.orange, Color.blue, Color.yellow, Color.red, Color.green, Color.pink };
	
	
	
	
	
	Board board;
	Polyomino[] polyominoes;
	
	int score;
	int numberOfPiecesPlayed;
	int rowsCleared;
	
	Piece currPiece;
	int currPieceRow;
	int currPieceCol;
	
	boolean gameInProgress;
	boolean gameOver;
	
	
	public Game() {
		board = new Board(width, height);
		
		score = 0;
		numberOfPiecesPlayed = 0;
		rowsCleared = 0;
		
		currPiece = null;
		
		currPieceRow = -1;
		currPieceCol = -1;
		
		gameInProgress = false;
		gameOver = false;
	}
	
	public void movePiece(Action action) {

		// TODO this
		
	}
	 
	public void start() {
		gameInProgress = true;
		
		// TODO Start a new piece at the board
	
	}

	public void reset() {

		board = new Board(width, height);
		
		score = 0;
		numberOfPiecesPlayed = 0;
		rowsCleared = 0;
		
		currPiece = null;
		
		currPieceRow = -1;
		currPieceCol = -1;
		
		gameInProgress = false;
		gameOver = false;
	
	}
	
	// Getters
	
	public int getScore () {
		return score;
	}

	public int getNumberOfPiecesPlayed () {
		return numberOfPiecesPlayed;
	}

	public int getRowsCleared () {
		return rowsCleared;
	}

	public Piece getCurrPiece () {
		return currPiece;
	}

	public int getCurrPieceRow () {
		return currPieceRow;
	}

	public int getCurrPieceCol () {
		return currPieceCol;
	}
	
	public boolean isGameInProgress () {
		return gameInProgress;
	}

	public boolean isGameOver () {
		return gameOver;
	}

	public int getBoardWidth () {
		return width;
	}

	public int getBoardHeight () {
		return height;
		//not desired way
	}

}
