package com.example.ominofinal;

import javafx.scene.paint.Color;
import java.util.Arrays;

public class Board {
    private final int width;
    private final int height;
    private final Color[][] grid;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Color[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor(int row, int col) {
        return grid[row][col];
    }

    public boolean canPlace(Piece piece, int row, int col) {
        for (Block block : piece.getBlocks()) {
            int r = row + block.getRow();
            int c = col + block.getCol();
            if (r < 0 || r >= height || c < 0 || c >= width || grid[r][c] != null) {
                return false;
            }
        }
        return true;
    }

    public void addPiece(Piece piece, int row, int col) {
        for (Block block : piece.getBlocks()) {
            int r = row + block.getRow();
            int c = col + block.getCol();
            if (r >= 0 && r < height && c >= 0 && c < width) {
                grid[r][c] = piece.getColor();
            }
        }
    }

    public int clearRows() {
        int cleared = 0;
        for (int r = height - 1; r >= 0; r--) {
            if (isRowFull(r)) {
                clearRow(r);
                cleared++;
                r++;
            }
        }
        return cleared;
    }

    private boolean isRowFull(int row) {
        for (Color color : grid[row]) {
            if (color == null) return false;
        }
        return true;
    }

    private void clearRow(int row) {
        for (int r = row; r > 0; r--) {
            System.arraycopy(grid[r - 1], 0, grid[r], 0, width);
        }
        Arrays.fill(grid[0], null);
    }

    public boolean isGameOver() {
        for (int c = 0; c < width; c++) {
            if (grid[0][c] != null) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int r = 0; r < height; r++) {
            Arrays.fill(grid[r], null);
        }
    }
}
