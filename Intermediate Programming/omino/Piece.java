import javafx.scene.paint.Color;

/**
 * orientation of a particular polyomino
 * immutable
 * rotating a piece results in a new piece 
 *  rather than changing the orientation of the current piece.
 *  
 * @author Nikolai Sazonov
 */
public class Piece {
	
	Polyomino polyomino;
	int width;
	int height;
	
	public Piece(Polyomino p, int rotation) {
		this.polyomino = p;
		
		Block[] blocks = p.getBlocks(rotation);
		
		this.width = computeWidth(blocks);
		this.height = computeHeight(blocks);
	}
	
	/*
	 * returns a new Piece representing the next rotation of this piece
	 */
	public Piece getNextRotation() {

		int currentRotation = polyomino.getNumRotations();
		int next = polyomino.getNextRotation(currentRotation);
		
		return new Piece(polyomino, next);
		
	}
		
	public Block[] getBody() {
		return polyomino.getBlocks(0);
	}

	public Color getColor() {
		return polyomino.getColor();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private int computeWidth(Block[] b) {
		return b.length;
	}
	
	private int computeHeight(Block[] b) {
		return b.length;
	}
	
}
