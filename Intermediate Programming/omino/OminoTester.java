import javafx.scene.paint.Color;
import java.util.Arrays;

public class OminoTester {

    public static void main(String[] args) {

        boolean assertsEnabled = false;
        assert assertsEnabled = true; // Intentional side-effect!!!
        System.out.println("Assertions are on: " + assertsEnabled);
        System.out.println("Running tests...");

        testPolyomino();
        testPiece();
        testBoard();
        testGame();

        System.out.println("All tests passed!");
    }

    /**
     * Test cases for Polyomino class.
     */
    private static void testPolyomino() {
        String[] shapes = { "0 0  1 0", "0 0  0 1" };
        Polyomino poly = new Polyomino(shapes, Color.BLUE);

        // Test getBlocks
        Block[] expected = { new Block(0, 0), new Block(1, 0) };
        assert blocksToString(poly.getBlocks(0)).equals(blocksToString(expected));

        // Test rotation
        assert poly.getNextRotation(0) == 1;
        assert poly.getNextRotation(1) == 0;
    }

    /**
     * Test cases for Piece class.
     */
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

    /**
     * Test cases for Board class.
     */
    private static void testBoard() {
        Board board = new Board(5, 5);
        Polyomino poly = new Polyomino(new String[]{"0 0  1 0"}, Color.GREEN);
        Piece piece = new Piece(poly, 0);

        // Test empty board state
        assert board.canPlace(2, 2);

        // Test placing a piece
        board.addPiece(2, 2, piece);
        assert !board.canPlace(2, 2);

        // Test dropping a piece
        assert board.getDropRow(4, 2, piece) == 0;

        // Test clearing a row
        for (int i = 0; i < 5; i++) {
            board.addPiece(0, i, piece);
        }
        assert board.clearRows() == 1;
    }

    /**
     * Test cases for Game class.
     */
    private static void testGame() {
        Game game = new Game();
        game.start();

        int startRow = game.getCurrPieceRow();
        int startCol = game.getCurrPieceCol();

        // Test moving LEFT
        game.movePiece(Action.LEFT);
        assert game.getCurrPieceCol() == startCol - 1;

        // Test moving RIGHT
        game.movePiece(Action.RIGHT);
        assert game.getCurrPieceCol() == startCol;

        // Test moving DOWN
        game.movePiece(Action.DOWN);
        assert game.getCurrPieceRow() == startRow + 1;

        // Test dropping
        game.movePiece(Action.DROP);
        assert game.getCurrPieceRow() == game.getDropRow();

        // Test handling landing
        game.handleLanding();
        assert game.getScore() > 0;

        // Test reset
        game.reset();
        assert game.getScore() == 0;
        assert !game.isGameInProgress();
    }

    /**
     * Helper method to convert an array of Blocks to a readable format.
     */
    private static String blocksToString(Block[] blocks) {
        return Arrays.toString(Arrays.stream(blocks)
                .map(b -> b.getRow() + " " + b.getColumn())
                .toArray());
    }
}
