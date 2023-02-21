package cis485.ui;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    private static final String BOARD_IMAGE_PATH = ""; // todo
    private static final String PIECE_IMAGE_DIRECTORY_PATH = ""; // todo
    private static final int PIXEL_WIDTH = 600;
    private static final int PIXEL_HEIGHT = 600;

    public static void main(String[] args) {
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        // load images
        try {
            BufferedImage boardImage = ImageIO.read(new File(BOARD_IMAGE_PATH));
            boardView.setBoardImage(boardImage);
            File pieceImageDirectory = new File(PIECE_IMAGE_DIRECTORY_PATH);
            File[] pieceImageFiles = pieceImageDirectory.listFiles();
            for (File pieceImageFile : pieceImageFiles) {
                BufferedImage pieceImage = ImageIO.read(pieceImageFile);
                boardView.addPieceImage(pieceImageFile.getName(), pieceImage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame("Chess Engine UI");
        frame.setUndecorated(false); // todo: set to true once close application button is implemented
        frame.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
        frame.add(boardView);
        frame.setVisible(true);
    }
}