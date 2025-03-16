package com.example.ominofinal;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;

public class Omino extends Application implements PropertyChangeListener {
    public static final int BLOCK_SIZE = 25;
    public static final long DELAY = 200;
    private Game game;
    private Timer timer;
    private Canvas boardCanvas, pieceCanvas;
    private Label scoreLabel, numRowsLabel, numPiecesLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Omino!");
        game = new Game();
        game.addPropertyChangeListener(this);
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        boardCanvas = new Canvas(Game.BOARD_WIDTH * BLOCK_SIZE, Game.BOARD_HEIGHT * BLOCK_SIZE);
        pieceCanvas = new Canvas(Game.BOARD_WIDTH * BLOCK_SIZE, Game.BOARD_HEIGHT * BLOCK_SIZE);
        GraphicsContext boardGC = boardCanvas.getGraphicsContext2D();
        boardGC.setFill(Color.GRAY);
        boardGC.fillRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        StackPane stack = new StackPane(boardCanvas, pieceCanvas);
        stack.setStyle("-fx-border-style: solid; -fx-border-color: white; -fx-border-width: 2");
        root.setCenter(stack);
        GridPane panel = new GridPane();
        panel.add(setupLabel(new Label("score")), 0, 0);
        scoreLabel = setupLabel(new Label("     0"));
        panel.add(scoreLabel, 1, 0);
        panel.add(setupLabel(new Label("rows")), 0, 1);
        numRowsLabel = setupLabel(new Label("     0"));
        panel.add(numRowsLabel, 1, 1);
        panel.add(setupLabel(new Label("pieces")), 0, 2);
        numPiecesLabel = setupLabel(new Label("     0"));
        panel.add(numPiecesLabel, 1, 2);
        root.setRight(panel);
        pieceCanvas.setFocusTraversable(true);
        root.setOnKeyTyped(e -> handleKey(e.getCharacter().toLowerCase().charAt(0)));
        stage.show();
        startTimer();
    }

    @Override
    public void stop() {
        stopTimer();
    }

    private void handleKey(char ch) {
        if (ch == 'j') game.move(Action.LEFT);
        else if (ch == 'l') game.move(Action.RIGHT);
        else if (ch == ' ') game.move(Action.DROP);
        else if (ch == 'k') game.move(Action.ROTATE);
        else if (ch == 'p') toggleTimer();
        else if (ch == 'q') Platform.exit();
        else if (ch == 'n') resetGame();
    }

    private void toggleTimer() {
        if (timer == null) startTimer();
        else stopTimer();
    }

    private void resetGame() {
        game.removePropertyChangeListener(this);
        game = new Game();
        game.addPropertyChangeListener(this);
        scoreLabel.setText(String.format("%5d", 0));
        numRowsLabel.setText(String.format("%5d", 0));
        numPiecesLabel.setText(String.format("%5d", 0));
        startTimer();
    }

    private void startTimer() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> game.move(Action.DOWN));
                }
            }, 0, DELAY);
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private Label setupLabel(Label label) {
        label.setStyle("-fx-font: normal 20px sans-serif; -fx-padding: 5; -fx-text-fill: white");
        return label;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            switch (evt.getPropertyName()) {
                case OminoSubject.BOARD_PROPERTY:
                    redrawBoard();
                    break;
                case OminoSubject.CURPIECE_PROPERTY:
                    redrawCurrentPiece();
                    break;
                case OminoSubject.SCORE_PROPERTY:
                    scoreLabel.setText(String.format("%5d", game.getScore()));
                    break;
                case OminoSubject.NUMROWS_PROPERTY:
                    numRowsLabel.setText(String.format("%5d", game.getNumRowsCleared()));
                    break;
                case OminoSubject.NUMPIECES_PROPERTY:
                    numPiecesLabel.setText(String.format("%5d", game.getNumPiecesPlayed()));
                    break;
            }
        });
    }

    private void redrawBoard() {
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        Board board = game.getBoard();
        for (int r = 0; r < board.getHeight(); r++) {
            for (int c = 0; c < board.getWidth(); c++) {
                Color color = board.getColor(r, c);
                if (color != null) {
                    gc.setFill(color);
                    gc.fillRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE - 1, BLOCK_SIZE - 1);
                }
            }
        }
    }

    private void redrawCurrentPiece() {
        GraphicsContext gc = pieceCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, pieceCanvas.getWidth(), pieceCanvas.getHeight());
        Piece piece = game.getCurrentPiece();
        if (piece != null) {
            gc.setFill(piece.getColor());
            for (Block b : piece.getBlocks()) {
                int x = (game.getCurrentCol() + b.getCol()) * BLOCK_SIZE;
                int y = (game.getCurrentRow() + b.getRow()) * BLOCK_SIZE;
                gc.fillRect(x, y, BLOCK_SIZE - 1, BLOCK_SIZE - 1);
            }
        }
    }
}
