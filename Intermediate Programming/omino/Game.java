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
	
	private final int width = 10;
	
	private final int height = 25;
	
	private final int[] pointsFromCleared = { 1, 3, 5, 8 };
	
	private static final String[][] POLYOMINOESSTRING = {
	  {"0 0  1 0  2 0", "0 0  0 1  0 2"}, // I
	  {"0 0  1 0  0 1", "0 0  0 1  1 1", "1 0  0 1  1 1", "0 0  1 0  1 1"}, // L
	  {"0 0  1 0  1 1  2 1"},             // S
	  {"1 0  2 0  0 1  1 1"}              // Z
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
		board = new Board(width, height);
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
	
	
	public void movePiece(Action action) {

		// TODO this
		if(!gameInProgress) return;
		
		if(currPiece == null) return;
		
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
			while( board.canPlace(newRow + 1,currPieceCol)) {
				newRow++;
			}
			break;
		case ROTATE:
			newP = currPiece.getNextRotation();
			newRow += (currPiece.getHeight() - newP.getHeight())/2;
			newCol +=(currPiece.getWidth() - newP.getWidth())/2;
			break;
		} // switch
		
		if (board.canPlace(newRow,newCol)) {
			currPiece = newP;
			currPieceRow = newRow;
      currPieceCol = newCol;
      firePropertyChange(CURPIECE_PROPERTY);
		}
		else if (action == Action.DOWN || action == Action.DROP) {
      handleLanding();
		}
		
	}
	
	private void handleLanding() {
		board.addPiece(currPieceRow,currPieceCol,currPiece);
		
		firePropertyChange(SCORE_PROPERTY);
    firePropertyChange(BOARD_PROPERTY);

    if (currPieceRow < 0) {
        gameOver = true;
        firePropertyChange("GAMEOVER_PROPERTY");
        return;
    } // game over, reached top
    
    int cleared = board.clearRows();
    rowsCleared += cleared;
    score += pointsFromCleared[Math.min(cleared - 1,pointsFromCleared.length)]; // in case cleared rows are greater than my allocated points values
    firePropertyChange(NUMROWS_PROPERTY);

    if (!gameOver) startNewPiece();
	}
	
	private void startNewPiece() {
		Random rand = new Random();
		
		Polyomino selectedPolyomino = polyominoes[rand.nextInt(polyominoes.length)];
		currPiece = new Piece(selectedPolyomino, 0);
		currPieceRow = -currPiece.getHeight();
    currPieceCol = (width - currPiece.getWidth()) / 2;
    numberOfPiecesPlayed++;
    firePropertyChange(CURPIECE_PROPERTY);
    firePropertyChange(NUMPIECES_PROPERTY);

    if (!board.canPlace(currPieceRow, currPieceCol)) {
        gameOver = true;
        firePropertyChange("GAMEOVER_PROPERTY");
    }
		
	}
	
	public void start() {
		gameInProgress = true;
		gameOver = false;
    firePropertyChange("GAMEINPROGRESS_PROPERTY");
    startNewPiece();
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
		return board.getWidth();
	}

	public int getBoardHeight () {
		return board.getHeight();
	}

}
