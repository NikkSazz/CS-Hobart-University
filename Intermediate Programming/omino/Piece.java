import javafx.scene.paint.Color;

/**
 * @author Nikolai Sazonov
 */
public class Piece {
	String[] orientation;
	Color color;
	
	public Piece(String[] o, Color c) {
		this.orientation = o;
		this.color = c;
	}
	
	public Block[] getBlocks(String str) {
		String[] coords = str.split(" +");
		var b = new Block[coords.length];
		
		for( int i = 0; i < coords.length ; i += 2 ) {
			b[i / 2] = new Block(
			                    Integer.parseInt(coords[i]),
			                    Integer.parseInt(coords[i + 1]));
		}
		
		return b;
	}
	
	public int getNumRotations() {
		return orientation.length;
	}
	
	public int getNextRotation(int i) {
		return i + 1;
	}
	
	public Color getColor() {
		return color;
	}
	
}
