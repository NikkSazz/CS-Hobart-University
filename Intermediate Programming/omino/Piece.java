import javafx.scene.paint.Color;

/**
 * Piece class represents a specific orientation of a polyomino.
 * @author Nikolai Sazonov
 */
public class Piece {
	private Polyomino polyomino;
	private int width;
	private int height;
	private int rotation;

	public Piece(Polyomino polyomino, int rotation) {

		this.polyomino = polyomino;
		this.rotation = rotation;
		Block[] blocks = polyomino.getBlocks(rotation);
		this.width = computeWidth(blocks);
		this.height = computeHeight(blocks);
	}

	public Piece getNextRotation() {
		return new Piece(polyomino, polyomino.getNextRotation(rotation));
	}

	public Block[] getBody() {
		Block[] original = polyomino.getBlocks(rotation);
		return original.clone(); // Defensive copy to prevent external modification
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

	/*
	 private int computeWidth(Block[] b) {
		return b.length;
	}

	private int computeHeight(Block[] b) {
		return b.length;
	}
	 */
	private int computeWidth(Block[] blocks) {
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (Block b : blocks) {
			min = Math.min(min, b.getColumn());
			max = Math.max(max, b.getColumn());
		}
		return (max - min) + 1;
	}

	private int computeHeight(Block[] blocks) {
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (Block b : blocks) {
			min = Math.min(min, b.getRow());
			max = Math.max(max, b.getRow());
		}
		return (max - min) + 1;
	}
}