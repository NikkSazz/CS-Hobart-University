import javafx.scene.paint.Color;
import java.util.Arrays;

/**
 * Tester for Omino methods.
 * 
 * @author Nikolai Sazonov
 */
@SuppressWarnings("unused")
public class OminoTester {

	public static void main ( String[] args ) {

		// the following is the "official" way to programmatically determine whether
		// or not assertion checking is turned on
		// it is included here because it is easy to accidentally omit setting the
		// proper switch in the run configuration and thus not realize that
		// assertions aren't being checked
		boolean assertsEnabled = false;
		assert assertsEnabled = true; // Intentional side-effect!!!
		System.out.println("assertions are on: " + assertsEnabled);
		System.out.println();

		// -- add test cases below --------------------------------------------
		testPolyomino();
		testPiece();
		testBoard();
		// need to test game also

		System.out.println("Tests have been conducted and passed!");
	}

	private static void testPolyomino() {
		String[] shapes = { "0 0  1 0", "0 0  0 1" };
		Polyomino p = new Polyomino(shapes, Color.BLUE);

		// Test getBlocks
		Block[] expected = { new Block(0, 0), new Block(1, 0) };
		assert p.getBlocks(0).equals(expected);

		// Test rotation
		assert p.getNextRotation(0) == 1;
		assert p.getNextRotation(1) == 0;
	}

	private static void testPiece() {
		String[] shapes = { "0 0  1 0", "0 0  0 1" };
		Polyomino poly = new Polyomino(shapes, Color.RED);
		Piece piece = new Piece(poly, 0);

		// Test width and height
		assert piece.getWidth() == 2;
		assert piece.getHeight() == 1;

		// Test rotation
		Piece rotated = piece.getNextRotation();
		assert rotated.getWidth() == 1;
		assert rotated.getHeight() == 2;
	}

	private static void testBoard() {
		Board board = new Board(5, 5);
		Polyomino poly = new Polyomino(new String[]{"0 0  1 0"}, Color.GREEN);
		Piece piece = new Piece(poly, 0);

		// Test empty board state
		assert board.canPlace(2, 2);

		board.addPiece(2, 2, piece);
		assert !board.canPlace(2, 2);

		assert board.getDropRow(4, 2, piece) == 0;

		for (int i = 0; i < 5; i++) {
			board.addPiece(0, i, piece);
		}
		assert board.clearRows() == 1;
	}

}