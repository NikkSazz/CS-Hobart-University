package com.example.ominofinal;

public class Block {
    private final int row;
    private final int col;

    public Block(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
