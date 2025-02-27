import javafx.scene.paint.Color;

/**
 * captures the notion of a particular configuration of 
 * blocks independent of orientation i.e. a shape.
 * 
 * a coordinate system is defined where (0,0) is the lower
 *  left corner of the rectangle surrounding the shape. There
 *  will always be at least one block in row 0 and in column 0
 *  though there is not necessarily a block at (0,0).
 * 
 * @author Nikolai Sazonov
 */
public class Polyomino {
	
	// the orientations for this polyomino 
	String[] orientation;
	
	// the polyomino's color
	Color color;
	
	public Polyomino(String[] o, Color c) {
		this.orientation = o;
		this.color = c;
	}
	
	/*
	 *  takes the index of the desired orientation
	 *  @returns array of the Blocks in that orientation of the polyomino
	 */
	public Block[] getBlocks(int index) {
		
		String[] coords = orientation[index].split(" +");
		Block[] b = new Block[coords.length / 2];
		
		for( int i = 0; i < coords.length ; i += 2 ) {
			b[i / 2] = new Block(Integer.parseInt(coords[i]),
			                    Integer.parseInt(coords[i + 1]));
		}
		
		return b;
	}
	
	// number of orientations of this polyomino
	public int getNumRotations() {
		return orientation.length;
	}
	// index of an orientation and returns the index of next orientation in the rotation order 
	public int getNextRotation(int i) {
		return (i + 1) % orientation.length;
	}
	
	public Color getColor() {
		return color;
	}
	
}
