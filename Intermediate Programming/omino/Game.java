import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Game logic for Omino!
 * 
 * @author Nikolai Sazonov
 */
public class Game extends OminoSubject {

	/**
	 * Create a new Omino! game. The game must be started in order to play.
	 */
	
	public static final int board_width = 10;
	
	public static final int board_height = 20;
	
	private final int[] score_table = { 0, 100, 300, 500, 800 };
	
	private static final String[][] POLYOMINOESSTRING = {
	  {"0 0  1 0  2 0", "0 0  0 1  0 2"}, // I
	  {"0 0  1 0  0 1", "0 0  0 1  1 1", "1 0  0 1  1 1", "0 0  1 0  1 1"}, // L
	  {"0 0  1 0  1 1  2 1", "1 0  1 1  0 1  0 2"},             // S
	  {"1 0  2 0  0 1  1 1", "0 0  0 1  1 1  1 2"}              // Z
	};
	 
	private final Color[] colors = 
		{ Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW }; 
	// TODO: set final Colors colors
	
	
	
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
	
	/*
	firePropertyChange(BOARD_PROPERTY);     // if there's a change to board contents
  firePropertyChange(CURPIECE_PROPERTY);  // if there's a change to the current piece (piece and/or position)
  firePropertyChange(SCORE_PROPERTY);     // if there's a change to the score
  firePropertyChange(NUMPIECES_PROPERTY); // if there's a change to the number of pieces played
  firePropertyChange(NUMROWS_PROPERTY);   // if there's a change to the number of rows cleared
	 */
	
	public Game() {
		board = new Board(board_width, board_height);
		polyominoes = createPolyominoes(); // polyomino[]
		reset();
	}
	
	private Polyomino[] createPolyominoes() {
		Polyomino[] p = new Polyomino[POLYOMINOESSTRING.length];
    for (int i = 0; i < POLYOMINOESSTRING.length; i++) {
        p[i] = new Polyomino(POLYOMINOESSTRING[i], colors[i]);
    }
    return p;
	}	
	
	public void reset() {

		//board = new Board(width, height);
		board.clear();
		score = 0;
		numberOfPiecesPlayed = 0;
		rowsCleared = 0;
		
		currPiece = null;
		
		currPieceRow = -1;
		currPieceCol = -1;
		
		gameInProgress = false;
		gameOver = false;
		
		firePropertyChange(BOARD_PROPERTY);
    firePropertyChange(SCORE_PROPERTY);
    firePropertyChange(NUMPIECES_PROPERTY);
    firePropertyChange(NUMROWS_PROPERTY);
	
	}
	
	public void start() {
		gameInProgress = true;
		gameOver = false;
		firePropertyChange("GAMEINPROGRESS_PROPERTY");
		startNewPiece();
	}
	
	private void startNewPiece() {
		Random rand = new Random();
		
		Polyomino selectedPolyomino = polyominoes[rand.nextInt(polyominoes.length)];
		currPiece = new Piece(selectedPolyomino, rand.nextInt(selectedPolyomino.getNumRotations()));
		currPieceRow = 0;
    currPieceCol = (board_width - currPiece.getWidth()) / 2;
    numberOfPiecesPlayed++;
    firePropertyChange(CURPIECE_PROPERTY);
    firePropertyChange(NUMPIECES_PROPERTY);
    /*
    if (!board.canPlace(currPieceRow, currPieceCol)) {
        gameOver = true;
        firePropertyChange("GAMEOVER_PROPERTY");
    }
		*/
	}
	
	public void movePiece(Action action) {

		// doneTODO this
		if (gameOver) { return; }
		if(!gameInProgress) { return; }		
		
		//if(currPiece == null) { return; }
		
		int newRow = currPieceRow;
		int newCol = currPieceCol;
		Piece newP = currPiece;
		
		switch (action) {
		case LEFT:
			newCol--;
			break;
		case RIGHT:
			newCol++;
			break;
		case DOWN:
			newRow++;
			break;
		case DROP:
			while( board.canPlace(newP, newRow + 1,newCol)) {
				newRow++;
			}
			break;
		case ROTATE:
			newP = currPiece.getNextRotation();
			newRow += (currPiece.getHeight() - newP.getHeight())/2;
			newCol +=(currPiece.getWidth() - newP.getWidth())/2;
			break;
		} // switch
		
		if (board.canPlace(newP, newRow,newCol)) {
			currPiece = newP;
			currPieceRow = newRow;
      currPieceCol = newCol;
      firePropertyChange(CURPIECE_PROPERTY);
		}
		else if (action == Action.DOWN || action == Action.DROP) {
      placePiece();
		}
		
	}
	
	private void placePiece() { 
    board.addPiece(currPiece, currPieceRow, currPieceCol);
    
    int cleared = board.clearRows();
    if(cleared < 0 || cleared > score_table.length) {
    	System.out.println("cleared > score_table.length");
    	return;
    }
    score += score_table[cleared];
    
    rowsCleared += cleared;
    firePropertyChange(BOARD_PROPERTY);
    firePropertyChange(SCORE_PROPERTY);
    firePropertyChange(NUMROWS_PROPERTY);
    
    if (board.isGameOver()) {
        gameOver = true;
        firePropertyChange(BOARD_PROPERTY);
    } else {
        startNewPiece();
    }
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
	
	public Board getBoard() {
    return board;
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
		return board.getWidth();
	}

	public int getBoardHeight () {
		return board.getHeight();
	}

}
