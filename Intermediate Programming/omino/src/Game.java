package com.example.ominofinal;

import javafx.scene.paint.Color;
import java.util.Random;

public class Game extends OminoSubject {
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;
    private static final int[] SCORE_TABLE = {0, 100, 300, 500, 800};

    private final Board board;
    private final Polyomino[] polyominoes;
    private Piece currentPiece;
    private int currentRow, currentCol;
    private int score;
    private int numPiecesPlayed;
    private int numRowsCleared;
    private boolean gameOver;

    public Game() {
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        polyominoes = new Polyomino[]{
                new Polyomino(new String[]{"0 0 1 0 0 1", "0 0 0 1 1 1"}, Color.RED),
                new Polyomino(new String[]{"0 0 1 0 1 1", "0 1 1 0 1 1"}, Color.BLUE)
        };
        resetGame();
    }

    public void resetGame() {
        score = 0;
        numPiecesPlayed = 0;
        numRowsCleared = 0;
        gameOver = false;
        board.clear();
        startNewPiece();
        firePropertyChange(BOARD_PROPERTY);
        firePropertyChange(SCORE_PROPERTY);
        firePropertyChange(NUMROWS_PROPERTY);
    }

    private void startNewPiece() {
        Random rand = new Random();
        Polyomino p = polyominoes[rand.nextInt(polyominoes.length)];
        currentPiece = new Piece(p, rand.nextInt(p.getNumRotations()));
        currentCol = (BOARD_WIDTH - currentPiece.getWidth()) / 2;
        currentRow = 0;
        numPiecesPlayed++;
        firePropertyChange(CURPIECE_PROPERTY);
        firePropertyChange(NUMPIECES_PROPERTY);
    }

    public void move(Action action) {
        if (gameOver) return;
        int newCol = currentCol;
        int newRow = currentRow;
        Piece newPiece = currentPiece;
        switch (action) {
            case LEFT:
                newCol--;
                break;
            case RIGHT:
                newCol++;
                break;
            case DOWN:
                newRow++;
                break;
            case ROTATE:
                newPiece = currentPiece.getNextRotation();
                newRow += (currentPiece.getHeight() - newPiece.getHeight()) / 2;
                newCol += (currentPiece.getWidth() - newPiece.getWidth()) / 2;
                break;
            case DROP:
                while (board.canPlace(newPiece, newRow + 1, newCol))
                    newRow++;
                break;
        }
        if (board.canPlace(newPiece, newRow, newCol)) {
            currentCol = newCol;
            currentRow = newRow;
            currentPiece = newPiece;
            firePropertyChange(CURPIECE_PROPERTY);
            if (action == Action.DROP)
                placePiece();
        } else {
            if (action == Action.DOWN)
                placePiece();
        }
    }

    private void placePiece() {
        board.addPiece(currentPiece, currentRow, currentCol);
        int cleared = board.clearRows();
        score += SCORE_TABLE[cleared];
        numRowsCleared += cleared;
        firePropertyChange(BOARD_PROPERTY);
        firePropertyChange(SCORE_PROPERTY);
        firePropertyChange(NUMROWS_PROPERTY);
        if (board.isGameOver()) {
            gameOver = true;
            firePropertyChange(BOARD_PROPERTY);
        } else {
            startNewPiece();
        }
    }

    public int getScore() {
        return score;
    }

    public int getNumPiecesPlayed() {
        return numPiecesPlayed;
    }

    public int getNumRowsCleared() {
        return numRowsCleared;
    }

    public Board getBoard() {
        return board;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
