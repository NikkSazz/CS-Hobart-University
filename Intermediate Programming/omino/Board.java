import java.util.Arrays;
import javafx.scene.paint.Color;

/**
 * Main Playing are where the pieces land. it is a grid of squares with (0, 0)
 * on the lower left corner.
 * 
 * @author Nikolai Sazonov
 */
public class Board {
	private int width;
	private int height;
	private Color[][] grid;

	public Board ( int width, int height ) {
		this.width = width;
		this.height = height;
		this.grid = new Color[height][width];
	}

	/*
	 * clears all full rows
	 * @returns number of rows cleared *for score?
	 */
	public int clearRows () {
		int cleared = 0;
		for ( int r = height - 1 ; r >= 0 ; r-- ) {
			if ( isRowFull(r) ) {
				clearRow(r);
				cleared++;
				r++;
			}
		}
		return cleared;
	}

	private boolean isRowFull ( int row ) {
		for ( Color color : grid[row] ) {
			if ( color == null ) return false;
		}
		return true;
	}

	private void clearRow ( int row ) {
		for ( int r = row ; r > 0 ; r-- ) {
			System.arraycopy(grid[r - 1],0,grid[r],0,width);
		}
		Arrays.fill(grid[0],null);
	}

	public void addPiece ( Piece piece, int row, int col ) {
		for ( Block block : piece.getBlocks() ) {
			int r = row + block.getRow();
			int c = col + block.getCol();
			if ( r >= 0 && r < height && c >= 0 && c < width ) {
				grid[r][c] = piece.getColor();
			}
		}
	}

	public Color getColor ( int row, int col ) {
		return grid[row][col];
	}

	public boolean canPlace ( Piece piece, int row, int col ) {
		for ( Block block : piece.getBlocks() ) {
			int r = row + block.getRow();
			int c = col + block.getCol();
			if ( r < 0 || r >= height || c < 0 || c >= width || grid[r][c] != null ) {
				return false;
			}
		}
		return true;
	}

	public boolean isGameOver () {
		for ( int c = 0 ; c < width ; c++ ) {
			if ( grid[0][c] != null ) {
				return true;
			}
		}
		return false;
	}

	public void clear () {
		for ( int r = 0 ; r < height ; r++ ) {
			Arrays.fill(grid[r],null);
		}
	}

	public int getWidth () {
		return width;
	}

	public int getHeight () {
		return height;
	}
}
