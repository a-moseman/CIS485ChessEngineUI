package cis485.ui;

import com.github.bhlangonijr.chesslib.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    private static final String BOARD_IMAGE_PATH = "Images\\board.png";
    private static final String PIECE_IMAGE_DIRECTORY_PATH = "Images\\Pieces";
    private static final int PIXEL_WIDTH = 600;
    private static final int PIXEL_HEIGHT = 600;

    public static void main(String[] args) {
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        loadImages(boardView);

        NorthPanel northPanel = new NorthPanel();


        JFrame frame = new JFrame("Chess Engine UI");
        frame.setUndecorated(true);
        frame.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
        frame.setLayout(new BorderLayout());
        frame.add(boardView, BorderLayout.CENTER);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private static void loadImages(BoardView boardView) {
        try {
            BufferedImage boardImage = ImageIO.read(new File(BOARD_IMAGE_PATH));
            boardView.setBoardImage(boardImage);
            File pieceImageDirectory = new File(PIECE_IMAGE_DIRECTORY_PATH);
            File[] pieceImageFiles = pieceImageDirectory.listFiles();
            for (File pieceImageFile : pieceImageFiles) {
                BufferedImage pieceImage = ImageIO.read(pieceImageFile);
                boardView.addPieceImage(pieceImageFile.getName().substring(0, pieceImageFile.getName().length() - 4), pieceImage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}