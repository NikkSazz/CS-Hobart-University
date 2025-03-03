import java.awt.Color;

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
	
	public Piece(Polyomino p, int w, int h) {
		this.polyomino = p;
		this.width = w;
		this.height = h;
	}
	
	public Piece getNextRotation() {

		int thiz = polyomino.getNumRotations();
		int next = polyomino.getNextRotation(thiz);
		
		Block[] b = polyomino.getBlocks(next);
		
		int nW = computeWidth(b);
		int nH = computeHeight(b);
		
		return new Piece(polyomino, nW, nH);
		
	}
		
//	public String[] getBody() {
//		return polyomino.
//	}

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
