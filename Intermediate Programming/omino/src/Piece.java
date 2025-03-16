package com.example.ominofinal;

import javafx.scene.paint.Color;

public class Piece {
    private final Polyomino polyomino;
    private final int orientation;
    private final int width;
    private final int height;

    public Piece(Polyomino polyomino, int orientation) {
        this.polyomino = polyomino;
        this.orientation = orientation;
        Block[] blocks = polyomino.getBlocks(orientation);
        this.width = computeWidth(blocks);
        this.height = computeHeight(blocks);
    }

    private int computeWidth(Block[] blocks) {
        int maxCol = 0;
        for (Block block : blocks) {
            if (block.getCol() > maxCol) {
                maxCol = block.getCol();
            }
        }
        return maxCol + 1;
    }

    private int computeHeight(Block[] blocks) {
        int maxRow = 0;
        for (Block block : blocks) {
            if (block.getRow() > maxRow) {
                maxRow = block.getRow();
            }
        }
        return maxRow + 1;
    }

    public Block[] getBlocks() {
        return polyomino.getBlocks(orientation);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return polyomino.getColor();
    }

    public Piece getNextRotation() {
        return new Piece(polyomino, polyomino.getNextRotation(orientation));
    }
}
