package com.example.ominofinal;


public class OminoTester {
    public static void main(String[] args) {
        boolean assertsEnabled = false;
        assert assertsEnabled = true;
        if (!assertsEnabled) {
            System.out.println("Assertions are not enabled.");
            return;
        }

        testPolyominoGetBlocks();
        testPolyominoGetNextRotation();
        testPieceDimensions();
        testBoardPlacementAndClearing();

        System.out.println("All tests passed!");
    }

    private static void testPolyominoGetBlocks() {
        Polyomino p = new Polyomino(new String[]{"0 0 1 0 2 0"}, javafx.scene.paint.Color.RED);
        Block[] blocks = p.getBlocks(0);
        String result = blocksToString(blocks);
        assert result.equals("0 0 1 0 2 0") : "Polyomino getBlocks failed: " + result;
    }

    private static void testPolyominoGetNextRotation() {
        Polyomino p = new Polyomino(
                new String[]{"0 0 1 0 0 1", "0 0 0 1 1 1"},
                javafx.scene.paint.Color.BLUE
        );
        assert p.getNextRotation(0) == 1 : "getNextRotation(0) should be 1";
        assert p.getNextRotation(1) == 0 : "getNextRotation(1) should wrap around to 0";
    }

    private static void testPieceDimensions() {
        Polyomino p = new Polyomino(new String[]{"0 0 1 0 0 1"}, javafx.scene.paint.Color.GREEN);
        Piece piece = new Piece(p, 0);
        assert piece.getWidth() == 2 : "Expected width=2, got " + piece.getWidth();
        assert piece.getHeight() == 2 : "Expected height=2, got " + piece.getHeight();
    }

    private static void testBoardPlacementAndClearing() {
        Board board = new Board(5, 5);
        Polyomino p = new Polyomino(new String[]{"0 0 1 0 2 0"}, javafx.scene.paint.Color.RED);
        Piece piece = new Piece(p, 0);

        assert board.canPlace(piece, 2, 1) : "Should be able to place piece at (2,1)";
        board.addPiece(piece, 2, 1);

        // Force a row to fill
        Polyomino filler = new Polyomino(new String[]{"0 0"}, javafx.scene.paint.Color.BLUE);
        Piece fillerPiece = new Piece(filler, 0);
        for (int c = 0; c < 5; c++) {
            if (board.canPlace(fillerPiece, 4, c)) {
                board.addPiece(fillerPiece, 4, c);
            }
        }
        int cleared = board.clearRows();
        assert cleared == 1 : "One row should have been cleared, got " + cleared;
    }

    private static String blocksToString(Block[] blocks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < blocks.length; i++) {
            sb.append(blocks[i].getRow()).append(" ").append(blocks[i].getCol());
            if (i < blocks.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
