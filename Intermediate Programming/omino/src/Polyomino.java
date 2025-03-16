package com.example.ominofinal;

import javafx.scene.paint.Color;

public class Polyomino {
    private final String[] orientations;
    private final Color color;

    public Polyomino(String[] orientations, Color color) {
        this.orientations = orientations;
        this.color = color;
    }

    public Block[] getBlocks(int orientation) {
        String[] coords = orientations[orientation].split("\\s+");
        Block[] blocks = new Block[coords.length / 2];
        for (int i = 0; i < coords.length; i += 2) {
            blocks[i / 2] = new Block(Integer.parseInt(coords[i]), Integer.parseInt(coords[i + 1]));
        }
        return blocks;
    }

    public int getNumRotations() {
        return orientations.length;
    }

    public int getNextRotation(int current) {
        return (current + 1) % orientations.length;
    }

    public Color getColor() {
        return color;
    }
}
