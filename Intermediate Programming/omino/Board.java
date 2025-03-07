import java.util.Arrays;
import javafx.scene.paint.Color;

/**
 * Main Playing are where the pieces land. it is a grid of squares 
 * with (0, 0) on the lower left corner.
 * @author Nikolai Sazonov
 */
public class Board {
	int width;
	int height;
	Piece[][] board;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.board = new Piece[height][width];
	}
	
	/*
	 * the highest row on the board containing a block
	 */
	private int highestRowWithBlock() {
		for(int i = 0; i < height; i++) {
			for(var b : board[i]) {
				if(b != null)
					return i;
			}
			
		}
		return -1;
	}
	
	/*
	 * clears all full rows
	 * @returns number of rows cleared *for score?
	 */
	public int clearRows() {
		int cleared = 0;
		for(int r = 0; r < height; r++) {
			if(rowFull(r)) {
				clearRow(r); // cleared row at r, moved all rows above one down
				cleared++;
				r--; // row deleted, must check that same index again
			}
		}
		return cleared;
	}
	
	/*
	 * clears at r and moves the rows above one down
	 * all rows after the method, should have filledBlocks < width
	 */
	private void clearRow(int r) {
		
		while(r > height) {
			for(int c = 0; c < width; c++) {
				board[r][c] = board[r + 1][c];
			}
			r++;
		}
		
		if(r == height) {
			for(int c = 0; c < width; c++) {
				board[r][c] = null;
			}
		}
		
	}
	
	private boolean rowFull(int row) {
		for(var b : board[row]) {
			if (b == null) {
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * the number of filled blocks in a particular row
	 */
	private int filledBlocksAtRow(int row) {
		int count = 0;
		for(var p : board[row]) {
			if(p != null) {
				count++;
			}
		}
		
		return count;
	}
	
	/*
	 * returns the row where the piece would land if it was dropped from its current position
	 */
	public int getDropRow(int row, int col, Piece p) {
		// why do we need row parameter?
		int h = row /* height - 1 ?why not this? */; 
		while(h > 0) {
			if(!canPlace(h, col)) {
				return h + 1;
			}
			h--;
		}
		return h;
		// idk if this works
	}
	
	public void addPiece(int row, int col, Piece p) {
		if(row > height) { // ignore if extends past top
			return;
		}
		board[row][col] = p;
	}
	
	public Color colorAt(int row, int col) {
		return board[row][col].getColor();
	}
	
	public boolean canPlace(int row, int col) {
		return board[row][col] != null;
	}
	
	public void clear() {
		for(int i = 0; i < board.length; i++) {
			Arrays.fill(board[i], null);
		}
	}
	
	/*
	public boolean containsBlockAt(int row, int col) {	
		return board[row][col] != null;
	}
	*/
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
